package med.voll.api.domain.consulta;

import med.voll.api.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    public void agendar(DadosAgendamentoConsulta dados) {
        if (!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id paciente informado nao existe!");
        }
            var paciente = pacienteRepository.findById(dados.idPaciente()).get();
        var medico = medicoRepository.findById(dados.idMedico()).get();
        var consulta = new Consulta(null, medico, paciente, dados.data());
        consultaRepository.save(consulta);

    }

}
