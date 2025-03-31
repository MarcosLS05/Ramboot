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

import Senet.Ramboot.entity.SnackEntity;
import Senet.Ramboot.service.SnackService;



@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/snacks")
public class SnackController {

    @Autowired
    SnackService oSnackService;
    
    @GetMapping("")
    public ResponseEntity<Page<SnackEntity>> getPage(
            Pageable oPageable,
            @RequestParam  Optional<String> filter) {
        return new ResponseEntity<Page<SnackEntity>>(oSnackService.getPage(oPageable, filter), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SnackEntity> get(@PathVariable Long id) {
        return new ResponseEntity<SnackEntity>(oSnackService.get(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return new ResponseEntity<Long>(oSnackService.delete(id), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<SnackEntity> createSnack(@RequestBody SnackEntity snack) {
        SnackEntity savedSnack = oSnackService.create(snack);
        return ResponseEntity.ok(savedSnack);
    }

    @PutMapping("")
    public ResponseEntity<SnackEntity> update(@RequestBody SnackEntity oSnackEntity) {
        return new ResponseEntity<SnackEntity>(oSnackService.update(oSnackEntity), HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<Long> deleteAll() {
        return new ResponseEntity<Long>(oSnackService.deleteAll(), HttpStatus.OK);
    }
}
