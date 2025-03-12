package Senet.Ramboot.api;

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

import Senet.Ramboot.entity.BebidaEntity;
import Senet.Ramboot.service.BebidaService;


@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/bebida")
public class BebidaController {

    @Autowired
    BebidaService oBebidaService;;
    
       @GetMapping("")
    public ResponseEntity<Page<BebidaEntity>> getPage(
            Pageable oPageable,
            @RequestParam  Optional<String> filter) {
        return new ResponseEntity<Page<BebidaEntity>>(oBebidaService.getPage(oPageable, filter), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BebidaEntity> get(@PathVariable Long id) {
        return new ResponseEntity<BebidaEntity>(oBebidaService.get(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return new ResponseEntity<Long>(oBebidaService.delete(id), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<BebidaEntity> createBebida(@RequestBody BebidaEntity Bebida) {
        BebidaEntity savedBebida = oBebidaService.create(Bebida);
        return ResponseEntity.ok(savedBebida);
    }

    @PutMapping("")
    public ResponseEntity<BebidaEntity> update(@RequestBody BebidaEntity oBebidaEntity) {
        return new ResponseEntity<BebidaEntity>(oBebidaService.update(oBebidaEntity), HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<Long> deleteAll() {
        return new ResponseEntity<Long>(oBebidaService.deleteAll(), HttpStatus.OK);
    }
}
