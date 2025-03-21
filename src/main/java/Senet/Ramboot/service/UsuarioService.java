package Senet.Ramboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import Senet.Ramboot.exception.ResourceNotFoundException;
import Senet.Ramboot.exception.UnauthorizedAccessException;
import Senet.Ramboot.repository.TipousuarioRepository;
import Senet.Ramboot.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import Senet.Ramboot.entity.TipousuarioEntity;
import Senet.Ramboot.entity.UsuarioEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

@Service
public class UsuarioService implements ServiceInterface<UsuarioEntity> {

    HttpServletRequest oHttpServletRequest;

    @Autowired
    AuthService oAuthService;

    @Autowired
    TipousuarioRepository oTipousuarioRepository;

    @Autowired
    private TipousuarioService oTipousuarioService;

    @Autowired
    private UsuarioRepository oUsuarioRepository;

    @Autowired
    RandomService oRandomService;

    private String[] arrFeedback = { "Encontré la tienda a través de una búsqueda en Google",
            "Un amigo me recomendó la tienda",
            "Vi un anuncio en Facebook",
            "Me enteré de la tienda a través de Instagram",
            "Encontré la tienda en un directorio en línea",
            "Un familiar me habló de la tienda",
            "Vi un anuncio en YouTube",
            "Encontré la tienda en un foro o comunidad en línea" };

    private String[] arrUsernames = { "Pepito23", "LauLau90", "NachoKing", "MeryMery", "LoloGamer", "Carmenita12",
            "RositaRocks", "PacoPwnz", "LuisLuis23",
            "AnitaSparkles", "RafaRafa99", "ManoloMaster", "LuciLuv", "MartaMiau", "SaraSass", "RocioRocksOn" };

    private String[] arrNombres = { "Pepe", "Laura", "Ignacio", "Maria", "Lorenzo", "Carmen", "Rosa", "Paco", "Luis",
            "Ana", "Rafa", "Manolo", "Lucia", "Marta", "Sara", "Rocio" };

    private String[] arrApellidos = { "Sancho", "Gomez", "Pérez", "Rodriguez", "Garcia", "Fernandez", "Lopez",
            "Martinez", "Sanchez", "Gonzalez", "Gimenez", "Feliu", "Gonzalez", "Hermoso", "Vidal", "Escriche",
            "Moreno" };

    private String[] arrLetras = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

    public Long randomCreate(Long cantidad) {
        for (int i = 0; i < cantidad; i++) {
            UsuarioEntity oUsuarioEntity = new UsuarioEntity();
            oUsuarioEntity.setUsername(arrUsernames[oRandomService.getRandomInt(0, arrUsernames.length - 1)]
                    + oRandomService.getRandomInt(999, 9999));
            oUsuarioEntity.setNombre(arrNombres[oRandomService.getRandomInt(0, arrNombres.length - 1)]);
            oUsuarioEntity.setApellido1(arrApellidos[oRandomService.getRandomInt(0, arrApellidos.length - 1)]);
            oUsuarioEntity.setApellido2(arrApellidos[oRandomService.getRandomInt(0, arrApellidos.length - 1)]);
            oUsuarioEntity.setDNI(String.valueOf(oRandomService.getRandomInt(10000000, 99999999))
                    + arrLetras[oRandomService.getRandomInt(0, arrLetras.length - 1)]);
            oUsuarioEntity.setFeedback(arrFeedback[oRandomService.getRandomInt(0, arrFeedback.length - 1)]);
            oUsuarioEntity.setFeedback(arrFeedback[oRandomService.getRandomInt(0, arrFeedback.length - 1)]);
            oUsuarioEntity.setEmail(
                    "email" + oUsuarioEntity.getNombre() + oRandomService.getRandomInt(999, 9999) + "@gmail.com");
            oUsuarioEntity.setCP(String.format("%05d", oRandomService.getRandomInt(10001, 52080)));
            oUsuarioEntity.setSaldo(oRandomService.getRandomDouble(0.00, 50.00));
            oUsuarioEntity.setActive(false);
            oUsuarioEntity.setTipousuario(oTipousuarioService.randomSelection());
            oUsuarioRepository.save(oUsuarioEntity);
        }
        return oUsuarioRepository.count();
    }

    public UsuarioEntity getByEmail(String email) {
        UsuarioEntity oUsuarioEntity = oUsuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario con email " + email + " no existe"));
        if (oAuthService.isEmpleadoWithItsOwnData(oUsuarioEntity.getId()) || oAuthService.isAdmin()
                || oAuthService.isClienteWithItsOwnData(oUsuarioEntity.getId())) {
            return oUsuarioEntity;
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para ver el usuario");
        }
    }

    public UsuarioEntity get(Long id) {
        if (oAuthService.isEmpleadoWithItsOwnData(id) || oAuthService.isAdmin()
                || oAuthService.isClienteWithItsOwnData(id)) {
            Optional<UsuarioEntity> usuario = oUsuarioRepository.findById(id);
            if (usuario.isPresent()) {
                return usuario.get();
            } else {
                throw new EntityNotFoundException("Usuario no encontrado con ID: " + id);
            }
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para ver el usuario");
        }
    }

    public Page<UsuarioEntity> getPage(Pageable oPageable, Optional<String> filter) {
        if (oAuthService.isAdmin()) {
            if (filter.isPresent()) {
                return oUsuarioRepository
                        .findByNombreContainingOrApellido1ContainingOrApellido2ContainingOrEmailContaining(
                                filter.get(), filter.get(), filter.get(), filter.get(),
                                oPageable);
            } else {
                return oUsuarioRepository.findAll(oPageable);
            }
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para ver los usuarios");
        }

    }

    public Page<UsuarioEntity> getPageXTipoUsuario(Pageable oPageable, Optional<String> filter,
            Optional<Long> id_tipousuario) {
        if (filter.isPresent()) {
            if (id_tipousuario.isPresent()) {
                return oUsuarioRepository
                        .findByTipousuarioIdAndTituloContaining(
                                id_tipousuario.get(), filter.get(), oPageable);
            } else {
                throw new ResourceNotFoundException("Tipousuario no encontrado");
            }
        } else {
            if (id_tipousuario.isPresent()) {
                return oUsuarioRepository.findByTipousuarioId(id_tipousuario.get(), oPageable);
            } else {
                throw new ResourceNotFoundException("Tipousuario no encontrado");
            }
        }
    }

    public Long count() {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para contar los usuarios");
        } else {
            return oUsuarioRepository.count();
        }
    }

    public Long delete(Long id) {
        if (oAuthService.isAdmin()) {
            oUsuarioRepository.deleteById(id);
            return 1L;
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para borrar el usuario");
        }
    }

    public UsuarioEntity setTipoUsuario(Long id, Long idtipousuario) {
        UsuarioEntity oUsuarioEntity = oUsuarioRepository.findById(id).get();
        TipousuarioEntity oTipousuarioEntity = oTipousuarioService.get(idtipousuario);
        oUsuarioEntity.setTipousuario(oTipousuarioEntity);
        return oUsuarioRepository.save(oUsuarioEntity);
    }

    public UsuarioEntity create(UsuarioEntity oUsuarioEntity) {
        if (oAuthService.isAdmin()) {
            // Verificar si el TipoUsuario existe antes de asignarlo
            oUsuarioEntity.setTipousuario(
                    oTipousuarioRepository.findById(oUsuarioEntity.getTipousuario().getId())
                            .orElseThrow(() -> new ResourceNotFoundException("TipoUsuario no encontrado")));

            return oUsuarioRepository.save(oUsuarioEntity);
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para crear el usuario");
        }
    }

    public UsuarioEntity update(UsuarioEntity oUsuarioEntity) {
        if (oAuthService.isEmpleadoWithItsOwnData(oUsuarioEntity.getId()) || oAuthService.isAdmin()
                || oAuthService.isClienteWithItsOwnData(oUsuarioEntity.getId())) {
            UsuarioEntity oUsuarioEntityFromDatabase = oUsuarioRepository.findById(oUsuarioEntity.getId()).get();

            if (oUsuarioEntity.getUsername() != null) {
                oUsuarioEntityFromDatabase.setUsername(oUsuarioEntity.getUsername());
            }
            if (oUsuarioEntity.getNombre() != null) {
                oUsuarioEntityFromDatabase.setNombre(oUsuarioEntity.getNombre());
            }
            if (oUsuarioEntity.getApellido1() != null) {
                oUsuarioEntityFromDatabase.setApellido1(oUsuarioEntity.getApellido1());
            }
            if (oUsuarioEntity.getApellido2() != null) {
                oUsuarioEntityFromDatabase.setApellido2(oUsuarioEntity.getApellido2());
            }
            if (oUsuarioEntity.getEmail() != null) {
                oUsuarioEntityFromDatabase.setEmail(oUsuarioEntity.getEmail());
            }
            if (oUsuarioEntity.getDNI() != null) {
                oUsuarioEntityFromDatabase.setDNI(oUsuarioEntity.getDNI());
            }
            if (oUsuarioEntity.getCP() != null) {
                oUsuarioEntityFromDatabase.setCP(oUsuarioEntity.getCP());
            }
            if (oUsuarioEntity.getFeedback() != null) {
                oUsuarioEntityFromDatabase.setFeedback(oUsuarioEntity.getCP());
            }
            if (oUsuarioEntity.getTelefono() != null) {
                oUsuarioEntityFromDatabase.setTelefono(oUsuarioEntity.getTelefono());
            }
            if (oUsuarioEntity.getSaldo() != 0) {
                oUsuarioEntityFromDatabase.setSaldo(oUsuarioEntity.getSaldo());
            }
            if (oUsuarioEntity.getFeedback() != null) {
                oUsuarioEntityFromDatabase.setFeedback(oUsuarioEntity.getFeedback());
            }
            if (oUsuarioEntity.getTipousuario() != null) {
                oUsuarioEntityFromDatabase.setTipousuario(oUsuarioEntity.getTipousuario());
            }
            return oUsuarioRepository.save(oUsuarioEntityFromDatabase);
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para modificar el usuario");
        }
    }

    public Long deleteAll() {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para borrar todos los usuarios");
        } else {
            oUsuarioRepository.deleteAll();
            return this.count();
        }
    }

    public UsuarioEntity randomSelection() {
        return oUsuarioRepository.findById((long) oRandomService.getRandomInt(1, (int) (long) this.count())).get();
    }

}