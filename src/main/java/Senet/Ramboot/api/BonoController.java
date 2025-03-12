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

import Senet.Ramboot.entity.BonoEntity;
import Senet.Ramboot.service.BonoService;
 


@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/bono")
public class BonoController {
    
    @Autowired 
    BonoService oBonoService;

    @GetMapping("")
    public ResponseEntity<Page<BonoEntity>> getPage(
            Pageable oPageable,
            @RequestParam  Optional<String> filter) {
        return new ResponseEntity<Page<BonoEntity>>(oBonoService.getPage(oPageable, filter), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BonoEntity> get(@PathVariable Long id) {
        return new ResponseEntity<BonoEntity>(oBonoService.get(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return new ResponseEntity<Long>(oBonoService.delete(id), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<BonoEntity> createBono(@RequestBody BonoEntity bono) {
        BonoEntity savedBono = oBonoService.create(bono);
        return ResponseEntity.ok(savedBono);
    }

    @PutMapping("")
    public ResponseEntity<BonoEntity> update(@RequestBody BonoEntity oBonoEntity) {
        return new ResponseEntity<BonoEntity>(oBonoService.update(oBonoEntity), HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<Long> deleteAll() {
        return new ResponseEntity<Long>(oBonoService.deleteAll(), HttpStatus.OK);
    }
}
