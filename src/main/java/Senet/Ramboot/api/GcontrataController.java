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

import Senet.Ramboot.entity.GcontrataEntity;
import Senet.Ramboot.service.GcontrataService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/gcontrata")
public class GcontrataController {

    @Autowired
    GcontrataService oGcontrataService;

    @GetMapping("")
    public ResponseEntity<Page<GcontrataEntity>> getPage(
            Pageable oPageable,
            @RequestParam Optional<String> filter) {
        return new ResponseEntity<Page<GcontrataEntity>>(oGcontrataService.getPage(oPageable, filter), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GcontrataEntity> get(@PathVariable Long id) {
        return new ResponseEntity<GcontrataEntity>(oGcontrataService.get(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return new ResponseEntity<Long>(oGcontrataService.delete(id), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<GcontrataEntity> createGcontrata(@RequestBody GcontrataEntity gcontrata) {
        GcontrataEntity savedGcontrata = oGcontrataService.create(gcontrata);
        return ResponseEntity.ok(savedGcontrata);
    }

    @PostMapping("/genTicketRandom")
    public ResponseEntity<String> generarTicketRandom() {
        String ticketRandom = GcontrataService.generarTicketRandom();
        return ResponseEntity.ok(ticketRandom);
    }

    @PutMapping("")
    public ResponseEntity<GcontrataEntity> update(@RequestBody GcontrataEntity oGcontrataEntity) {
        return new ResponseEntity<GcontrataEntity>(oGcontrataService.update(oGcontrataEntity), HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<Long> deleteAll() {
        return new ResponseEntity<Long>(oGcontrataService.deleteAll(), HttpStatus.OK);
    }
}
