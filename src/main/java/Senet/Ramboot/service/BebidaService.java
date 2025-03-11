package Senet.Ramboot.service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import Senet.Ramboot.entity.BebidaEntity;
import Senet.Ramboot.exception.ResourceNotFoundException;
import Senet.Ramboot.exception.UnauthorizedAccessException;
import Senet.Ramboot.repository.BebidaRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class BebidaService {
        HttpServletRequest oHttpServletRequest;

    @Autowired
    AuthService oAuthService;
      
    @Autowired
    BebidaRepository oBebidaRepository;

    @Autowired
    RandomService oRandomService;


public Long randomCreate(Long cantidad) {
    for (int i = 0; i < cantidad; i++) {
        BebidaEntity Bebida = new BebidaEntity();
        Bebida.setNombre(generarNombreAleatorio());
        Bebida.setPrecio(generarPrecioAleatorio());
        Bebida.setStock(generarStockAleatorio());
        oBebidaRepository.save(Bebida);
    }
    return oBebidaRepository.count();
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

    public BebidaEntity create(BebidaEntity oBebidaEntity) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para crear el Producto");
        }
        return oBebidaRepository.save(oBebidaEntity);
}

    public Page<BebidaEntity> getPage(Pageable oPageable, Optional<String> filter) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para ver los Productos");
        }
        if (filter.isPresent()) {
            return oBebidaRepository.findByNombreContaining(filter.get(), oPageable);
        } else {
            return oBebidaRepository.findAll(oPageable);
        }
    }

    public BebidaEntity get(Long id) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para ver el Producto");
        }
        return oBebidaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
    }

    public Long count() {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para contar los Productos");
        }
        return oBebidaRepository.count();
    }

    public Long delete(Long id) {
        if (oAuthService.isAdmin()) {
            oBebidaRepository.deleteById(id);
            return 1L;
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para borrar el Producto");
        }
    }

    public BebidaEntity update(BebidaEntity oBebidaEntity) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para modificar el Producto");
        }
        BebidaEntity oBebidaEntityFromDatabase = oBebidaRepository.findById(oBebidaEntity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
        if (oBebidaEntity.getNombre() != null) {
            oBebidaEntityFromDatabase.setNombre(oBebidaEntity.getNombre());
        }
        if (oBebidaEntity.getPrecio() != null){
            oBebidaEntityFromDatabase.setPrecio(oBebidaEntity.getPrecio());
        }
        if (oBebidaEntity.getStock() != 0){
            oBebidaEntityFromDatabase.setStock(oBebidaEntity.getStock());
        }
        return oBebidaRepository.save(oBebidaEntityFromDatabase);
    }

    public Long deleteAll() {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para borrar todos los Productos");
        }
        oBebidaRepository.deleteAll();
        return this.count();
    }

    public BebidaEntity randomSelection() {
        return oBebidaRepository.findAll()
                .get(oRandomService.getRandomInt(0, (int) (oBebidaRepository.count() - 1)));
    }
}
