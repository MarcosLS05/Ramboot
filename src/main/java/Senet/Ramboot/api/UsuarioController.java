package Senet.Ramboot.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Senet.Ramboot.entity.UsuarioEntity;
import Senet.Ramboot.repository.UsuarioRepository;
import Senet.Ramboot.service.UsuarioService;


@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService oUsuarioService;
    
    @Autowired
    UsuarioRepository oUsuarioRepository;



    @GetMapping("")
    public ResponseEntity<Page<UsuarioEntity>> getPage(
            Pageable oPageable,
            @RequestParam  Optional<String> filter) {
        return new ResponseEntity<Page<UsuarioEntity>>(oUsuarioService.getPage(oPageable, filter), HttpStatus.OK);
    }

    @GetMapping("/byemail/{email}")
    public ResponseEntity<UsuarioEntity> getUsuarioByEmail(@PathVariable(value = "email") String email) {
        return ResponseEntity.ok(oUsuarioService.getByEmail(email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> get(@PathVariable Long id) {
        return new ResponseEntity<UsuarioEntity>(oUsuarioService.get(id), HttpStatus.OK);
    }

    @GetMapping("xtipousuario/{id}")
    public ResponseEntity<Page<UsuarioEntity>> getPageXTipousuario(
            Pageable oPageable,
            @RequestParam Optional<String> filter,
            @PathVariable Optional<Long> id) {
        return new ResponseEntity<Page<UsuarioEntity>>(oUsuarioService.getPageXTipoUsuario(oPageable, filter, id),
                HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UsuarioEntity>> searchByUsername(@RequestParam String username) {
        List<UsuarioEntity> usuarios = oUsuarioRepository.findByUsernameContaining(username);
        return ResponseEntity.ok(usuarios);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return new ResponseEntity<Long>(oUsuarioService.delete(id), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<UsuarioEntity> createUsuario(@RequestBody UsuarioEntity usuario) {
        if (usuario.getTipousuario() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        UsuarioEntity savedUsuario = oUsuarioService.create(usuario);
        return ResponseEntity.ok(savedUsuario);
    }

    @PutMapping("")
    public ResponseEntity<UsuarioEntity> update(@RequestBody UsuarioEntity oUsuarioEntity) {
        return new ResponseEntity<UsuarioEntity>(oUsuarioService.update(oUsuarioEntity), HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<Long> deleteAll() {
        return new ResponseEntity<Long>(oUsuarioService.deleteAll(), HttpStatus.OK);
    }

    @PutMapping("/settipousuario/{id}/{idtipousuario}")
    public ResponseEntity<UsuarioEntity> setTipousuario(@PathVariable Long id, @PathVariable Long idtipousuario) {
        return new ResponseEntity<UsuarioEntity>(oUsuarioService.setTipoUsuario(id, idtipousuario), HttpStatus.OK);
    }
}   

