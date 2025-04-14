package Senet.Ramboot.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import Senet.Ramboot.entity.ProductoEntity;
import Senet.Ramboot.entity.GcontrataEntity;
import Senet.Ramboot.entity.GcontrataproductoEntity;
import Senet.Ramboot.entity.UsuarioEntity;
import Senet.Ramboot.entity.ZonaEntity;
import Senet.Ramboot.exception.ResourceNotFoundException;
import Senet.Ramboot.exception.UnauthorizedAccessException;
import Senet.Ramboot.repository.GcontrataRepository;
import Senet.Ramboot.repository.ProductoRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class GcontrataService implements ServiceInterface<GcontrataEntity> {

    HttpServletRequest oHttpServletRequest;

    @Autowired
    AuthService oAuthService;

    @Autowired
    ZonaService oZonaService;

    @Autowired
    RandomService oRandomService;

    @Autowired
    GcontrataRepository oGcontrataRepository;

    @Autowired
    UsuarioService oUsuarioService;

    GcontrataEntity oGcontrataEntity;

    @Autowired
    ProductoRepository oProductoRepository;

    private String[] arrMetodoPago = { "Tarjeta Bancaria", "Efectivo", "Paypal", "Bizum" };

    public GcontrataEntity create(GcontrataEntity oGcontrataEntity) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para crear el contrato");
        }
        return oGcontrataRepository.save(oGcontrataEntity);
    }

    public Page<GcontrataEntity> getPage(Pageable oPageable, Optional<String> filter) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para ver los contratos");
        }
        if (filter.isPresent()) {
            return oGcontrataRepository.findByTicketContaining(filter.get(), oPageable);
        } else {
            return oGcontrataRepository.findAll(oPageable);
        }
    }

    public GcontrataEntity get(Long id) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para ver el contrato");
        }
        return oGcontrataRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("contrato no encontrado"));
    }

    public Long count() {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para contar los contratos");
        }
        return oGcontrataRepository.count();
    }

    public Long delete(Long id) {
        if (oAuthService.isAdmin()) {
            oGcontrataRepository.deleteById(id);
            return 1L;
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para borrar el contrato");
        }
    }

    public GcontrataEntity addImporte(
        GcontrataEntity oGcontrataEntity,
        UsuarioEntity oUsuarioEntity,
        List<GcontrataproductoEntity> productosComprados,
        BigDecimal montoParaSaldo) {

    if (!oAuthService.isAdmin()) {
        throw new UnauthorizedAccessException("No tienes permisos para realizar la operación");
    }

    // Crear el contrato
    GcontrataEntity nuevoContrato = new GcontrataEntity();
    nuevoContrato.setFecha_creacion(oGcontrataEntity.getFecha_creacion());
    nuevoContrato.setTicket(generarTicketRandom());
    nuevoContrato.setMetodoPago(oGcontrataEntity.getMetodoPago());
    nuevoContrato.setUsuario(oUsuarioEntity);

    // Validar que el monto total de la operación sea suficiente
    BigDecimal montoTotalOperacion = oGcontrataEntity.getImporte();
    if (montoTotalOperacion == null || montoTotalOperacion.compareTo(BigDecimal.ZERO) <= 0) {
        throw new IllegalArgumentException("El monto total de la operación debe ser mayor a cero");
    }

    // Calcular el costo total de los productos (si hay productos)
    BigDecimal costoTotalProductos = BigDecimal.ZERO;
    if (productosComprados != null && !productosComprados.isEmpty()) {
        for (GcontrataproductoEntity producto : productosComprados) {
            costoTotalProductos = costoTotalProductos.add(producto.getImporte());
        }
    }

    // Validar que el monto para saldo y el costo de los productos no excedan el monto total
    if (montoParaSaldo.add(costoTotalProductos).compareTo(montoTotalOperacion) > 0) {
        throw new IllegalArgumentException("El monto para saldo y el costo de los productos exceden el monto total de la operación");
    }

    // 1. Añadir el monto para saldo al usuario (si aplica)
    if (montoParaSaldo.compareTo(BigDecimal.ZERO) > 0) {
        BigDecimal saldoActual = oUsuarioEntity.getSaldo() != null ? oUsuarioEntity.getSaldo() : BigDecimal.ZERO;
        BigDecimal nuevoSaldo = saldoActual.add(montoParaSaldo);
        oUsuarioEntity.setSaldo(nuevoSaldo);
        oUsuarioService.update(oUsuarioEntity);
    }

    // 2. Validar que el saldo del usuario sea suficiente para cubrir el costo de los productos (si hay productos)
    if (costoTotalProductos.compareTo(BigDecimal.ZERO) > 0) {
        BigDecimal saldoActual = oUsuarioEntity.getSaldo() != null ? oUsuarioEntity.getSaldo() : BigDecimal.ZERO;
        if (saldoActual.compareTo(costoTotalProductos) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar la compra de los productos");
        }

        // 3. Validar stock antes de asociar los productos al contrato
        if (!validarStock(productosComprados)) {
            throw new IllegalArgumentException("No hay suficiente stock para uno o más productos");
        }

        // 4. Actualizar el stock de los productos comprados
        actualizarStock(productosComprados);

        // 5. Asociar los productos al contrato
        for (GcontrataproductoEntity producto : productosComprados) {
            producto.setGcontrata(nuevoContrato); // Asociar el producto al contrato
        }
        nuevoContrato.setGcontrataproductos(productosComprados);
    }

    // Registrar el monto total de la operación en el contrato
    nuevoContrato.setImporte(montoTotalOperacion);

    // Guardar el contrato y los productos
    return oGcontrataRepository.save(nuevoContrato);
}


public boolean validarStock(List<GcontrataproductoEntity> productos) {
    for (GcontrataproductoEntity p : productos) {
        ProductoEntity producto = oProductoRepository.findById(p.getProducto().getId()).orElseThrow();
        if (producto.getStock() < p.getCantidad()) {
            return false;
        }
    }
    return true;
}


public void actualizarStock(List<GcontrataproductoEntity> productosComprados) {
    for (GcontrataproductoEntity producto : productosComprados) {
        ProductoEntity oProductoEntity = oProductoRepository.findById(producto.getProducto().getId())
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado: " + producto.getProducto().getId()));

        int nuevoStock = oProductoEntity.getStock() - producto.getCantidad();

        if (nuevoStock < 0) {
            throw new IllegalArgumentException("El stock del producto con ID " + oProductoEntity.getId() + " no puede ser negativo");
        }

        oProductoEntity.setStock(nuevoStock);

        oProductoRepository.save(oProductoEntity);
    }
}



    public GcontrataEntity update(GcontrataEntity oGcontrataEntity) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para modificar el contrato");
        }
        GcontrataEntity oGcontrataEntityFromDatabase = oGcontrataRepository.findById(oGcontrataEntity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("contrato no encontrado"));
        if (oGcontrataEntity.getFecha_creacion() != null) {
            oGcontrataEntityFromDatabase.setFecha_creacion(oGcontrataEntity.getFecha_creacion());
        }
        if(oGcontrataEntity.getImporte() != null) {
            oGcontrataEntityFromDatabase.setImporte(oGcontrataEntity.getImporte());
        }
        if (oGcontrataEntity.getTicket() != null) {
            oGcontrataEntityFromDatabase.setTicket(oGcontrataEntity.getTicket());
        }
        if (oGcontrataEntity.getMetodoPago() != null) {
            oGcontrataEntityFromDatabase.setMetodoPago(oGcontrataEntity.getMetodoPago());
        }
        if (oGcontrataEntity.getZona() != null) {
            oGcontrataEntityFromDatabase.setZona(oGcontrataEntity.getZona());
        }
        if (oGcontrataEntity.getUsuario() != null) {
            oGcontrataEntityFromDatabase.setUsuario(oGcontrataEntity.getUsuario());
        }
        
        return oGcontrataRepository.save(oGcontrataEntityFromDatabase);
    }

    public Page<GcontrataEntity> getPageXUsuario(Pageable oPageable, Optional<String> filter, Optional<Long> id) {
        if (id.isEmpty()) {
            throw new IllegalArgumentException("El ID del usuario no puede estar vacío.");
        }
    
        String filterValue = filter.orElse(null);
        return oGcontrataRepository.findByUsuarioIdAndFilter(id.get(), filterValue, oPageable);
    }


    public Long deleteAll() {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para borrar todos los contratos");
        }
        oGcontrataRepository.deleteAll();
        return this.count();
    }

    public GcontrataEntity randomSelection() {
        return oGcontrataRepository.findAll()
                .get(oRandomService.getRandomInt(0, (int) (oGcontrataRepository.count() - 1)));
    }

    public static String generarTicketRandom() {
        Random random = new Random();

        char letra1 = (char) ('A' + random.nextInt(26));
        char letra2 = (char) ('A' + random.nextInt(26));
        char letra3 = (char) ('A' + random.nextInt(26));
        String letras = "" + letra1 + letra2 + letra3;

        int num1 = random.nextInt(10);
        int num2 = random.nextInt(10);
        int num3 = random.nextInt(10);

        return letras + num1 + num2 + num3;
    }

    @Override
    public Long randomCreate(Long cantidad) {
        for (int i = 0; i < cantidad; i++) {
            GcontrataEntity oGcontrataEntity = new GcontrataEntity();
            oGcontrataEntity.setMetodoPago(arrMetodoPago[oRandomService.getRandomInt(0, arrMetodoPago.length - 1)]);
            oGcontrataEntity.setTicket(generarTicketRandom());
            oGcontrataEntity.setZona(oZonaService.randomSelection());
            oGcontrataEntity.setUsuario(oUsuarioService.randomSelection());
            oGcontrataRepository.save(oGcontrataEntity);
        }

        return oGcontrataRepository.count();
    }

}
