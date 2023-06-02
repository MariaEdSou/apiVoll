package med.voll.api.domain.consulta.validacoes;

import med.voll.api.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.paciente.PacienteRepository;

public class ValidadorPacienteSemOutraConsultaNoDia {

    private PacienteRepository repository;

    private void validar(DadosAgendamentoConsulta dados) {
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(),primeiroHorario,ultimoHorario);
        if (pacientePossuiOutraConsultaNoDia) {
            throw new ValidacaoException("Paciente ja possui uma consulta agendada nesse dia");
        }
    }
}
