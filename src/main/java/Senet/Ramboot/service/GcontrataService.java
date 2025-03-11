package Senet.Ramboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import Senet.Ramboot.entity.GcontrataEntity;
import Senet.Ramboot.entity.GcontrataEntity;
import Senet.Ramboot.exception.ResourceNotFoundException;
import Senet.Ramboot.exception.UnauthorizedAccessException;
import Senet.Ramboot.repository.GcontrataRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class GcontrataService implements ServiceInterface<GcontrataEntity>{

    HttpServletRequest oHttpServletRequest;

    @Autowired
    AuthService oAuthService;

    @Autowired
    RandomService oRandomService;

    @Autowired
    GcontrataRepository oGcontrataRepository;

        public GcontrataEntity create(GcontrataEntity oGcontrataEntity) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para crear el tipo de usuario");
        }
        return oGcontrataRepository.save(oGcontrataEntity);
}

    public Page<GcontrataEntity> getPage(Pageable oPageable, Optional<String> filter) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para ver los tipos de usuario");
        }
        if (filter.isPresent()) {
            return oGcontrataRepository.findByTicketContaining(filter.get(), oPageable);
        } else {
            return oGcontrataRepository.findAll(oPageable);
        }
    }

    public GcontrataEntity get(Long id) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para ver el tipo de usuario");
        }
        return oGcontrataRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de usuario no encontrado"));
    }

    public Long count() {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para contar los tipos de usuario");
        }
        return oGcontrataRepository.count();
    }

    public Long delete(Long id) {
        if (oAuthService.isAdmin()) {
            oGcontrataRepository.deleteById(id);
            return 1L;
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para borrar el tipo de usuario");
        }
    }

    public GcontrataEntity update(GcontrataEntity oGcontrataEntity) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para modificar el tipo de usuario");
        }
        GcontrataEntity oGcontrataEntityFromDatabase = oGcontrataRepository.findById(oGcontrataEntity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de usuario no encontrado"));
        if (oGcontrataEntity.getFecha_creacion() != null) {
            oGcontrataEntityFromDatabase.setFecha_creacion(oGcontrataEntity.getFecha_creacion());
        }
        if (oGcontrataEntity.getTicket()!= null){
            oGcontrataEntityFromDatabase.setTicket(oGcontrataEntity.getTicket());
        }
        if (oGcontrataEntity.isMetodoPago()) {
            oGcontrataEntityFromDatabase.setMetodoPago(oGcontrataEntity.isMetodoPago());
        }
        return oGcontrataRepository.save(oGcontrataEntityFromDatabase);
    }

    public Long deleteAll() {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para borrar todos los tipos de usuario");
        }
        oGcontrataRepository.deleteAll();
        return this.count();
    }

    public GcontrataEntity randomSelection() {
        return oGcontrataRepository.findAll()
                .get(oRandomService.getRandomInt(0, (int) (oGcontrataRepository.count() - 1)));
    }

    @Override
    public Long randomCreate(Long cantidad) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'randomCreate'");
    }
    
}
