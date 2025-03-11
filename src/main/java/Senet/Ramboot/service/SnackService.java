package Senet.Ramboot.service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import Senet.Ramboot.entity.SnackEntity;
import Senet.Ramboot.exception.ResourceNotFoundException;
import Senet.Ramboot.exception.UnauthorizedAccessException;
import Senet.Ramboot.repository.SnackRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class SnackService implements ServiceInterface<SnackEntity>{
    
    HttpServletRequest oHttpServletRequest;

    @Autowired
    AuthService oAuthService;
      
    @Autowired
    SnackRepository oSnackRepository;

    @Autowired
    RandomService oRandomService;


public Long randomCreate(Long cantidad) {
    for (int i = 0; i < cantidad; i++) {
        SnackEntity snack = new SnackEntity();
        snack.setNombre(generarNombreAleatorio());
        snack.setPrecio(generarPrecioAleatorio());
        snack.setStock(generarStockAleatorio());
        oSnackRepository.save(snack);
    }
    return oSnackRepository.count();
}

private String generarNombreAleatorio() {
    String[] nombres = {"Chips", "Galletas", "Cerezas", "Frutas", "Nueces", "Caramelos", "Chocolates", "Gominolas"};
    return nombres[new Random().nextInt(nombres.length)];
}

private BigDecimal generarPrecioAleatorio() {
    return BigDecimal.valueOf(new Random().nextDouble() * 2 + 1);
}

private int generarStockAleatorio() {
    return new Random().nextInt(50) + 1;
}

    public SnackEntity create(SnackEntity oSnackEntity) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para crear el Producto");
        }
        return oSnackRepository.save(oSnackEntity);
}

    public Page<SnackEntity> getPage(Pageable oPageable, Optional<String> filter) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para ver los Productos");
        }
        if (filter.isPresent()) {
            return oSnackRepository.findByNombreContaining(filter.get(), oPageable);
        } else {
            return oSnackRepository.findAll(oPageable);
        }
    }

    public SnackEntity get(Long id) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para ver el Producto");
        }
        return oSnackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
    }

    public Long count() {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para contar los Productos");
        }
        return oSnackRepository.count();
    }

    public Long delete(Long id) {
        if (oAuthService.isAdmin()) {
            oSnackRepository.deleteById(id);
            return 1L;
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para borrar el Producto");
        }
    }

    public SnackEntity update(SnackEntity oSnackEntity) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para modificar el Producto");
        }
        SnackEntity oSnackEntityFromDatabase = oSnackRepository.findById(oSnackEntity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
        if (oSnackEntity.getNombre() != null) {
            oSnackEntityFromDatabase.setNombre(oSnackEntity.getNombre());
        }
        if (oSnackEntity.getPrecio() != null){
            oSnackEntityFromDatabase.setPrecio(oSnackEntity.getPrecio());
        }
        if (oSnackEntity.getStock() != 0){
            oSnackEntityFromDatabase.setStock(oSnackEntity.getStock());
        }
        return oSnackRepository.save(oSnackEntityFromDatabase);
    }

    public Long deleteAll() {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para borrar todos los Productos");
        }
        oSnackRepository.deleteAll();
        return this.count();
    }

    public SnackEntity randomSelection() {
        return oSnackRepository.findAll()
                .get(oRandomService.getRandomInt(0, (int) (oSnackRepository.count() - 1)));
    }

}
