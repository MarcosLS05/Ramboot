package Senet.Ramboot.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
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
    ZonaService oZonaService;

    @Autowired
    RandomService oRandomService;

    @Autowired
    GcontrataRepository oGcontrataRepository;

    @Autowired
    UsuarioService oUsuarioService;

    
    GcontrataEntity oGcontrataEntity;


    private String[] arrMetodoPago = { "Tarjeta Bancaria", "Efectivo",  "Paypal", "Bizum"}; 

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

    public GcontrataEntity update(GcontrataEntity oGcontrataEntity) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para modificar el contrato");
        }
        GcontrataEntity oGcontrataEntityFromDatabase = oGcontrataRepository.findById(oGcontrataEntity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("contrato no encontrado"));
        if (oGcontrataEntity.getFecha_creacion() != null) {
            oGcontrataEntityFromDatabase.setFecha_creacion(oGcontrataEntity.getFecha_creacion());
        }
        if (oGcontrataEntity.getTicket()!= null){
            oGcontrataEntityFromDatabase.setTicket(oGcontrataEntity.getTicket());
        }
        if (oGcontrataEntity.getMetodoPago() != null) {
            oGcontrataEntityFromDatabase.setMetodoPago(oGcontrataEntity.getMetodoPago());
        }
        return oGcontrataRepository.save(oGcontrataEntityFromDatabase);
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
        for(int i = 0; i < cantidad; i++){
            GcontrataEntity oGcontrataEntity = new GcontrataEntity();
            oGcontrataEntity.setMetodoPago(arrMetodoPago[oRandomService.getRandomInt(0, arrMetodoPago.length - 1)]);
            oGcontrataEntity.setTicket(generarTicketRandom());
            oGcontrataEntity.setZona(oZonaService.randomSelection());
            oGcontrataEntity.setUsuario(oUsuarioService.randomSelection());
            oGcontrataRepository.save(oGcontrataEntity); // Agregar esta línea para guardar el objeto en la base de datos
        }
                    
        return oGcontrataRepository.count();
    }

}
