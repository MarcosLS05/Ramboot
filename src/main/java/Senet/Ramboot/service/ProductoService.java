package Senet.Ramboot.service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import Senet.Ramboot.entity.ProductoEntity;
import Senet.Ramboot.exception.ResourceNotFoundException;
import Senet.Ramboot.exception.UnauthorizedAccessException;
import Senet.Ramboot.repository.ProductoRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class ProductoService implements ServiceInterface<ProductoEntity> {
        
    HttpServletRequest oHttpServletRequest;

    @Autowired
    AuthService oAuthService;
      
    @Autowired
    ProductoRepository oProductoRepository;

    @Autowired
    RandomService oRandomService;


public Long randomCreate(Long cantidad) {
    for (int i = 0; i < cantidad; i++) {
        ProductoEntity Producto = new ProductoEntity();
        Producto.setNombre(generarNombreAleatorio());
        Producto.setPrecio(generarPrecioAleatorio());
        Producto.setStock(generarStockAleatorio());
        oProductoRepository.save(Producto);
    }
    return oProductoRepository.count();
}

private String generarNombreAleatorio() {
    String[] nombres = {"Coca-Cola", "Fanta", "Sprite", "Agua Mineral", "Jugo de Naranja", "Jugo de Manzana", "Té Frío", "Café", "Leche", "Zumo de Tomate"};
    return nombres[new Random().nextInt(nombres.length)];
}

private BigDecimal generarPrecioAleatorio() {
    return BigDecimal.valueOf(new Random().nextDouble() * 2 + 1);
}

private int generarStockAleatorio() {
    return new Random().nextInt(50) + 1;
}

    public ProductoEntity create(ProductoEntity oProductoEntity) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para crear el producto");
        }
        return oProductoRepository.save(oProductoEntity);
}

    public Page<ProductoEntity> getPage(Pageable oPageable, Optional<String> filter) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para ver los productos");
        }
        if (filter.isPresent()) {
            return oProductoRepository.findByNombreContaining(filter.get(), oPageable);
        } else {
            return oProductoRepository.findAll(oPageable);
        }
    }

    public ProductoEntity get(Long id) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para ver el Producto");
        }
        return oProductoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
    }

    public Long count() {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para contar los Productos");
        }
        return oProductoRepository.count();
    }

    public Long delete(Long id) {
        if (oAuthService.isAdmin()) {
            oProductoRepository.deleteById(id);
            return 1L;
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para borrar el Producto");
        }
    }

    public ProductoEntity update(ProductoEntity oProductoEntity) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para modificar el Producto");
        }
        ProductoEntity oProductoEntityFromDatabase = oProductoRepository.findById(oProductoEntity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
        if (oProductoEntity.getNombre() != null) {
            oProductoEntityFromDatabase.setNombre(oProductoEntity.getNombre());
        }
        if (oProductoEntity.getPrecio() != null){
            oProductoEntityFromDatabase.setPrecio(oProductoEntity.getPrecio());
        }
        if (oProductoEntity.getStock() != 0){
            oProductoEntityFromDatabase.setStock(oProductoEntity.getStock());
        }
        return oProductoRepository.save(oProductoEntityFromDatabase);
    }

    public Long deleteAll() {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para borrar todos los Productos");
        }
        oProductoRepository.deleteAll();
        return this.count();
    }

    public ProductoEntity randomSelection() {
        return oProductoRepository.findAll()
                .get(oRandomService.getRandomInt(0, (int) (oProductoRepository.count() - 1)));
    }
}
