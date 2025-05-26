package Senet.Ramboot.service;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import Senet.Ramboot.entity.GcontrataproductoEntity;
import Senet.Ramboot.exception.ResourceNotFoundException;
import Senet.Ramboot.exception.UnauthorizedAccessException;
import Senet.Ramboot.repository.GcontrataproductoRepository;

@Service
public class GcontrataproductoService implements ServiceInterface<GcontrataproductoEntity> {


    @Autowired
    GcontrataproductoRepository oGcontrataproductoRepository;

    @Autowired
    RandomService oRandomService;

    @Autowired
    AuthService oAuthService; 

    
    public Page<GcontrataproductoEntity> getPage(Pageable oPageable, Optional<String> filter) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para ver los Contrato de productos");
        }
        if (filter.isPresent()) {
            return oGcontrataproductoRepository.findByGcontrataContaining(filter.get(), oPageable);
        } else {
            return oGcontrataproductoRepository.findAll(oPageable);
        }
    }

    public GcontrataproductoEntity get(Long id) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para ver el Contrato de productos");
        }
        return oGcontrataproductoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrato de producto no encontrado"));
    }

    public Long count() {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para contar los Contrato de productos");
        }
        return oGcontrataproductoRepository.count();
    }

    public Long delete(Long id) {
        if (oAuthService.isAdmin()) {
            oGcontrataproductoRepository.deleteById(id);
            return 1L;
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para borrar el Contrato de productos");
        }
    }

    public GcontrataproductoEntity create(GcontrataproductoEntity oGcontrataproductoEntity) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para crear el Contrato de productos");
        }
        return oGcontrataproductoRepository.save(oGcontrataproductoEntity);
    }

    public GcontrataproductoEntity update(GcontrataproductoEntity oGcontrataproductoEntity) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para modificar el Contrato de productos");
        }
        GcontrataproductoEntity oGcontrataproductoEntityFromDatabase = oGcontrataproductoRepository.findById(oGcontrataproductoEntity.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Contrato de productos no encontrado"));
        if (oGcontrataproductoEntity.getCantidad() != 0) {
            oGcontrataproductoEntityFromDatabase.setCantidad(oGcontrataproductoEntity.getCantidad());
        }

        if (oGcontrataproductoEntity.getGcontrata() != null) {
            oGcontrataproductoEntityFromDatabase.setGcontrata(oGcontrataproductoEntity.getGcontrata());
        }
        if (oGcontrataproductoEntity.getProducto() != null) {
            oGcontrataproductoEntityFromDatabase.setProducto(oGcontrataproductoEntity.getProducto());
        }

        
        return oGcontrataproductoRepository.save(oGcontrataproductoEntityFromDatabase);
    }

    public Long deleteAll() {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para borrar todos los Contrato de productos");
        }
        oGcontrataproductoRepository.deleteAll();
        return this.count();
    }

    public GcontrataproductoEntity randomSelection() {
        return oGcontrataproductoRepository.findAll()
                .get(oRandomService.getRandomInt(0, (int) (oGcontrataproductoRepository.count() - 1)));
    }

    @Override
    public Long randomCreate(Long cantidad) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'randomCreate'");
    }
}
