package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.paciente.DadosListagemPaciente;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacienteRepository;
import med.voll.api.paciente.DadosCadastroPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    public void cadastroPaciente(@RequestBody @Valid DadosCadastroPaciente dadosPaciente) {
        repository.save(new Paciente(dadosPaciente));
    }
    @GetMapping
    public Page<DadosListagemPaciente> listar(Pageable pageable) {
        return repository.findAll(pageable).map(DadosListagemPaciente::new);
    }

}
