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
import Senet.Ramboot.entity.GcontrataproductoEntity;
import Senet.Ramboot.service.GcontrataService;
import Senet.Ramboot.service.GcontrataproductoService;
import Senet.Ramboot.service.UsuarioService;


@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/gcontrata_producto")
public class GcontrataProductoController {

    @Autowired
    GcontrataproductoService oGcontrataproductoService;

    @Autowired
    private UsuarioService oUsuarioService;



    @GetMapping("")
    public ResponseEntity<Page<GcontrataproductoEntity>> getPage(
            Pageable oPageable,
            @RequestParam Optional<String> filter) {
        return new ResponseEntity<Page<GcontrataproductoEntity>>(oGcontrataproductoService.getPage(oPageable, filter), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GcontrataproductoEntity> get(@PathVariable Long id) {
        return new ResponseEntity<GcontrataproductoEntity>(oGcontrataproductoService.get(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return new ResponseEntity<Long>(oGcontrataproductoService.delete(id), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<GcontrataproductoEntity> createGcontrata(@RequestBody GcontrataproductoEntity gcontrata_producto) {
        GcontrataproductoEntity savedGcontrata = oGcontrataproductoService.create(gcontrata_producto);
        return ResponseEntity.ok(savedGcontrata);
    }




    @PostMapping("/genTicketRandom")
    public ResponseEntity<String> generarTicketRandom() {
        String ticketRandom = GcontrataService.generarTicketRandom();
        return ResponseEntity.ok(ticketRandom);
    }

    @PutMapping("")
    public ResponseEntity<GcontrataproductoEntity> update(@RequestBody GcontrataproductoEntity oGcontrataEntity) {
        return new ResponseEntity<GcontrataproductoEntity>(oGcontrataproductoService.update(oGcontrataEntity), HttpStatus.OK);
    }


    @DeleteMapping("/all")
    public ResponseEntity<Long> deleteAll() {
        return new ResponseEntity<Long>(oGcontrataproductoService.deleteAll(), HttpStatus.OK);
    }
}

