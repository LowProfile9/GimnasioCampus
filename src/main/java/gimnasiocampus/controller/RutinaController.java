package gimnasiocampus.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gimnasiocampus.entity.Cliente;
import gimnasiocampus.entity.Rutina;
import gimnasiocampus.service.RutinaService;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/rutinas")
public class RutinaController {

    private final RutinaService rutinaService;

    public RutinaController(RutinaService rutinaService) {
        this.rutinaService = rutinaService;
    }

    @GetMapping
    public ResponseEntity<List<Rutina>> listarTodas() {
        return ResponseEntity.ok(rutinaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rutina> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(rutinaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Rutina> crear(@Valid @RequestBody Rutina rutina) {
        Rutina rutinaCreada = rutinaService.crearRutina(rutina);
        return ResponseEntity.created(URI.create("/api/rutinas/" + rutinaCreada.getId())).body(rutinaCreada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rutina> actualizar(@PathVariable Long id, @Valid @RequestBody Rutina rutina) {
        Rutina rutinaActualizada = rutinaService.actualizarRutina(id, rutina);
        return ResponseEntity.ok(rutinaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        rutinaService.eliminarRutina(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{rutinaId}/clientes")
    public ResponseEntity<List<Cliente>> listarClientes(@PathVariable Long rutinaId) {
        return ResponseEntity.ok(rutinaService.listarClientesDeRutina(rutinaId));
    }
}

