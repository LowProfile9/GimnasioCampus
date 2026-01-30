package gimnasiocampus.repository;

import gimnasiocampus.entity.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RutinaRepository extends JpaRepository<Rutina, Long> {

    boolean existsByNombre(String nombre);

    Optional<Rutina> findByNombre(String nombre);
}
