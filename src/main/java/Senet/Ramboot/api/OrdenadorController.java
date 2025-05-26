package Senet.Ramboot.api;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;
import Senet.Ramboot.entity.OrdenadorEntity;
import Senet.Ramboot.repository.OrdenadorRepository;
import Senet.Ramboot.service.OrdenadorService;


@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/ordenador")
public class OrdenadorController {

    @Autowired
    private OrdenadorService ordenadorService;

    @Autowired
    private OrdenadorRepository ordenadorRepository;

    @PostMapping("/{id}/encender")
    public ResponseEntity<?> encenderOrdenador(@PathVariable Long id) {
        Optional<OrdenadorEntity> ordenadorOpt = ordenadorRepository.findById(id);
        if (ordenadorOpt.isEmpty()) return ResponseEntity.notFound().build();

        try {
            ordenadorService.enviarMagicPacket(ordenadorOpt.get().getMacAddress());
            return ResponseEntity.ok("Magic Packet enviado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al enviar Magic Packet");
        }
    }

    @GetMapping
    public List<OrdenadorEntity> listarOrdenadores() {
        return ordenadorRepository.findAll();
    }

    @PostMapping
    public OrdenadorEntity crearOrdenador(@RequestBody OrdenadorEntity ordenador) {
        return ordenadorRepository.save(ordenador);
    }

    @PutMapping("/{id}")
    public OrdenadorEntity actualizarOrdenador(@PathVariable Long id, @RequestBody OrdenadorEntity ordenador) {
        ordenador.setId(id);
        return ordenadorRepository.save(ordenador);
    }

    @DeleteMapping("/{id}")
    public void eliminarOrdenador(@PathVariable Long id) {
        ordenadorRepository.deleteById(id);
    }
}

