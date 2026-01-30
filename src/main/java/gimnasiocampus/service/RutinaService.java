package gimnasiocampus.service;

import org.springframework.stereotype.Service;

import gimnasiocampus.entity.Cliente;
import gimnasiocampus.entity.Rutina;
import gimnasiocampus.exception.ConflictDbException;
import gimnasiocampus.exception.RegistroNoEncontradoException;
import gimnasiocampus.repository.RutinaRepository;

import java.util.List;




@Service
public class RutinaService {

    private final RutinaRepository rutinaRepository;

    public RutinaService(RutinaRepository rutinaRepository) {
        this.rutinaRepository = rutinaRepository;
    }

    public List<Rutina> listarTodas() {
        return rutinaRepository.findAll();
    }

    public Rutina buscarPorId(Long id) {
        return rutinaRepository.findById(id)
                .orElseThrow(() -> new RegistroNoEncontradoException("Rutina no encontrada"));
    }

    public Rutina crearRutina(Rutina rutina) {
        if (rutinaRepository.existsByNombre(rutina.getNombre())) {
            throw new ConflictDbException("Nombre de rutina ya registrado");
        }
        return rutinaRepository.save(rutina);
    }

    public Rutina actualizarRutina(Long id, Rutina rutinaActualizada) {
        Rutina rutina = rutinaRepository.findById(id).orElseThrow(() -> new RegistroNoEncontradoException("Rutina no encontrada"));
        if (!rutina.getNombre().equals(rutinaActualizada.getNombre()) &&
            rutinaRepository.existsByNombre(rutinaActualizada.getNombre())) {
            throw new ConflictDbException("Nombre de rutina ya registrado");
        }
        rutina.setNombre(rutinaActualizada.getNombre());
        rutina.setNivel(rutinaActualizada.getNivel());
        return rutinaRepository.save(rutina);
    }

    public void eliminarRutina(Long id) {
        if (rutinaRepository.existsById(id)) {
            rutinaRepository.deleteById(id);
        } else {
            throw new RegistroNoEncontradoException("Rutina no encontrada");
        }
    }

    public List<Cliente> listarClientesDeRutina(Long rutinaId) {
        return rutinaRepository.findById(rutinaId)
                .map(Rutina::getClientes)
                .orElseThrow(() -> new RegistroNoEncontradoException("Rutina no encontrada"));
    }
}
