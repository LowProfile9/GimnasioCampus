package gimnasiocampus.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gimnasiocampus.entity.Cliente;
import gimnasiocampus.entity.Rutina;
import gimnasiocampus.service.ClienteService;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Cliente> crear(@Valid @RequestBody Cliente cliente) {
        Cliente clienteCreado = clienteService.crearCliente(cliente);
        return ResponseEntity.created(URI.create("/api/clientes/" + clienteCreado.getId())).body(clienteCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
        Cliente clienteActualizado = clienteService.actualizarCliente(id, cliente);
        return ResponseEntity.ok(clienteActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{clienteId}/rutinas/{rutinaId}")
    public ResponseEntity<Void> asignarRutina(@PathVariable Long clienteId, @PathVariable Long rutinaId) {
        clienteService.asignarRutina(clienteId, rutinaId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{clienteId}/rutinas")
    public ResponseEntity<List<Rutina>> listarRutinas(@PathVariable Long clienteId) {
        return ResponseEntity.ok(clienteService.listarRutinasDeCliente(clienteId));
    }

    @DeleteMapping("/{clienteId}/rutinas/{rutinaId}")
    public ResponseEntity<Void> quitarRutina(@PathVariable Long clienteId, @PathVariable Long rutinaId) {
        clienteService.quitarRutina(clienteId, rutinaId);
        return ResponseEntity.noContent().build();
    }
}
