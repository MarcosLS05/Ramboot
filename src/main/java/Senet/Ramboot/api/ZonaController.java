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

import Senet.Ramboot.entity.ZonaEntity;
import Senet.Ramboot.service.ZonaService;


@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/zona")
public class ZonaController {
    
    @Autowired 
    ZonaService oZonaService;

    @GetMapping("")
    public ResponseEntity<Page<ZonaEntity>> getPage(
            Pageable oPageable,
            @RequestParam  Optional<String> filter) {
        return new ResponseEntity<Page<ZonaEntity>>(oZonaService.getPage(oPageable, filter), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZonaEntity> get(@PathVariable Long id) {
        return new ResponseEntity<ZonaEntity>(oZonaService.get(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ZonaEntity>> getAll(Pageable oPageable) {
        return new ResponseEntity<Page<ZonaEntity>>(oZonaService.getAll(oPageable), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return new ResponseEntity<Long>(oZonaService.delete(id), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<ZonaEntity> createZona(@RequestBody ZonaEntity zona) {
        ZonaEntity savedZona = oZonaService.create(zona);
        return ResponseEntity.ok(savedZona);
    }

    @PutMapping("")
    public ResponseEntity<ZonaEntity> update(@RequestBody ZonaEntity oZonaEntity) {
        return new ResponseEntity<ZonaEntity>(oZonaService.update(oZonaEntity), HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<Long> deleteAll() {
        return new ResponseEntity<Long>(oZonaService.deleteAll(), HttpStatus.OK);
    }
}
