package gimnasiocampus.service;

import java.util.List;
import org.springframework.stereotype.Service;
import gimnasiocampus.entity.Cliente;
import gimnasiocampus.entity.Rutina;
import gimnasiocampus.exception.ConflictDbException;
import gimnasiocampus.exception.RegistroNoEncontradoException;
import gimnasiocampus.repository.ClienteRepository; 
import gimnasiocampus.repository.RutinaRepository;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final RutinaRepository rutinaRepository;

    public ClienteService(ClienteRepository clienteRepository, RutinaRepository rutinaRepository) {
        this.clienteRepository = clienteRepository;
        this.rutinaRepository = rutinaRepository;
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RegistroNoEncontradoException("Cliente no encontrado"));
    }

    public Cliente crearCliente(Cliente cliente) {
        if (clienteRepository.existsByDocumento(cliente.getDocumento())) {
            throw new ConflictDbException("Documento ya registrado");
        }
        return clienteRepository.save(cliente);
    }

    public Cliente actualizarCliente(Long id, Cliente clienteActualizado) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RegistroNoEncontradoException("Cliente no encontrado"));
        if (!cliente.getDocumento().equals(clienteActualizado.getDocumento()) &&
            clienteRepository.existsByDocumento(clienteActualizado.getDocumento())) {
            throw new ConflictDbException("Documento ya registrado");
        }
        cliente.setNombre(clienteActualizado.getNombre());
        cliente.setDocumento(clienteActualizado.getDocumento());
        cliente.setActivo(clienteActualizado.isActivo());
        return clienteRepository.save(cliente);
    }

    public void eliminarCliente(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
        } else {
            throw new RegistroNoEncontradoException("Cliente no encontrado");
        }
    }

    public void asignarRutina(Long clienteId, Long rutinaId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RegistroNoEncontradoException("Cliente no encontrado"));
        Rutina rutina = rutinaRepository.findById(rutinaId)
                .orElseThrow(() -> new RegistroNoEncontradoException("Rutina no encontrada"));
        if (!cliente.getRutinas().contains(rutina)) {
            cliente.getRutinas().add(rutina);
            clienteRepository.save(cliente);
        } else {
            throw new ConflictDbException("La rutina ya está asignada al cliente");
        }
    }

    public void quitarRutina(Long clienteId, Long rutinaId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RegistroNoEncontradoException("Cliente no encontrado"));
        Rutina rutina = rutinaRepository.findById(rutinaId)
                .orElseThrow(() -> new RegistroNoEncontradoException("Rutina no encontrada"));
        if (cliente.getRutinas().contains(rutina)) {
            cliente.getRutinas().remove(rutina);
            clienteRepository.save(cliente);
        } else {
            throw new RegistroNoEncontradoException("La rutina no está asignada al cliente");
        }
    }

    public List<Rutina> listarRutinasDeCliente(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .map(Cliente::getRutinas)
                .orElseThrow(() -> new RegistroNoEncontradoException("Cliente no encontrado"));
    }
}
