package Senet.Ramboot.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import Senet.Ramboot.entity.ZonaEntity;
import Senet.Ramboot.exception.ResourceNotFoundException;
import Senet.Ramboot.exception.UnauthorizedAccessException;
import Senet.Ramboot.repository.ZonaRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class ZonaService implements ServiceInterface<ZonaEntity>{
    
    HttpServletRequest oHttpServletRequest;

    @Autowired
    AuthService oAuthService;

    @Autowired
    RandomService oRandomService;

    @Autowired 
    ZonaRepository oZonaRepository;

public Long randomCreate(Long cantidad){
    if (!oAuthService.isAdmin()){
        this.create(new ZonaEntity("General", BigDecimal.valueOf(1.00)));
        this.create(new ZonaEntity("Bootcamp", BigDecimal.valueOf(3.50)));
        this.create(new ZonaEntity("Streamer Box", BigDecimal.valueOf(5.00)));
    }
    return oZonaRepository.count();
}

    public ZonaEntity create(ZonaEntity oZonaEntity) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para crear el tipo de usuario");
        }
        return oZonaRepository.save(oZonaEntity);
}

    public Page<ZonaEntity> getPage(Pageable oPageable, Optional<String> filter) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para ver los tipos de usuario");
        }
        if (filter.isPresent()) {
            return oZonaRepository.findByTituloContaining(filter.get(), oPageable);
        } else {
            return oZonaRepository.findAll(oPageable);
        }
    }

    public Page<ZonaEntity> getAll(Pageable oPageable) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para ver los tipos de usuario");
        }
        return oZonaRepository.findAll(oPageable);
    }

    public ZonaEntity get(Long id) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para ver el tipo de usuario");
        }
        return oZonaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de usuario no encontrado"));
    }

    public Long count() {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para contar los tipos de usuario");
        }
        return oZonaRepository.count();
    }

    public Long delete(Long id) {
        if (oAuthService.isAdmin()) {
            oZonaRepository.deleteById(id);
            return 1L;
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para borrar el tipo de usuario");
        }
    }

    public ZonaEntity update(ZonaEntity oZonaEntity) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para modificar el tipo de usuario");
        }
        ZonaEntity oZonaEntityFromDatabase = oZonaRepository.findById(oZonaEntity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de usuario no encontrado"));
        if (oZonaEntity.getTitulo() != null) {
            oZonaEntityFromDatabase.setTitulo(oZonaEntity.getTitulo());
        }
        return oZonaRepository.save(oZonaEntityFromDatabase);
    }

    public Long deleteAll() {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para borrar todos los tipos de usuario");
        }
        oZonaRepository.deleteAll();
        return this.count();
    }

    public ZonaEntity randomSelection() {
        return oZonaRepository.findAll()
                .get(oRandomService.getRandomInt(0, (int) (oZonaRepository.count() - 1)));
    }

}
