package Senet.Ramboot.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import Senet.Ramboot.entity.BonoEntity;
import Senet.Ramboot.exception.ResourceNotFoundException;
import Senet.Ramboot.exception.UnauthorizedAccessException;
import Senet.Ramboot.repository.BonoRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class BonoService implements ServiceInterface <BonoEntity>{
    

    HttpServletRequest oHttpServletRequest;

    @Autowired
    BonoEntity oBonoEntity;

    @Autowired
    AuthService oAuthService;

    @Autowired
    RandomService oRandomService;

    @Autowired
    BonoRepository oBonoRepository;

    @Autowired
    ZonaService oZonaService;


public Long randomCreate(Long cantidad){
    if (!oAuthService.isAdmin()){
        
        this.create(new BonoEntity("DayPass", BigDecimal.valueOf(20.00)));
        this.create(new BonoEntity("DayPass", BigDecimal.valueOf(30.00)));

        
        
    }
    return oBonoRepository.count();
}

    public BonoEntity create(BonoEntity oBonoEntity) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para crear el bono");
        }
        return oBonoRepository.save(oBonoEntity);
}

    public Page<BonoEntity> getPage(Pageable oPageable, Optional<String> filter) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para ver los bonos");
        }
        if (filter.isPresent()) {
            return oBonoRepository.findByNombreContaining(filter.get(), oPageable);
        } else {
            return oBonoRepository.findAll(oPageable);
        }
    }

    public BonoEntity get(Long id) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para ver el bono");
        }
        return oBonoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("bono no encontrado"));
    }

    public Long count() {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para contar los bonos");
        }
        return oBonoRepository.count();
    }

    public Long delete(Long id) {
        if (oAuthService.isAdmin()) {
            oBonoRepository.deleteById(id);
            return 1L;
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para borrar el bono");
        }
    }

    public BonoEntity update(BonoEntity oBonoEntity) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para modificar el bono");
        }
        BonoEntity oBonoEntityFromDatabase = oBonoRepository.findById(oBonoEntity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("bonos no encontrado"));
        if (oBonoEntity.getNombre() != null) {
            oBonoEntityFromDatabase.setNombre(oBonoEntity.getNombre());
        }
        return oBonoRepository.save(oBonoEntityFromDatabase);
    }

    public Long deleteAll() {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para borrar todos los bonos");
        }
        oBonoRepository.deleteAll();
        return this.count();
    }

    public BonoEntity randomSelection() {
        return oBonoRepository.findAll()
                .get(oRandomService.getRandomInt(0, (int) (oBonoRepository.count() - 1)));
    }
}
