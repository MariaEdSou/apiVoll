package med.voll.api.domain.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findAllByPendenteTrue(Pageable pageable);

    @Query("select p.pendente from Paciente p where p.id = :id")
    Boolean findAtivoById(Long id);

}
