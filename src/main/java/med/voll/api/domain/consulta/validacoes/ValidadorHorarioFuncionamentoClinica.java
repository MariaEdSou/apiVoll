package med.voll.api.domain.consulta.validacoes;

import med.voll.api.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
@Component

public class ValidadorHorarioFuncionamentoClinica implements ValidadaorAgendamentoDeConsulta {

    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var anstesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18;
        if (domingo || anstesDaAberturaDaClinica || depoisDoEncerramentoDaClinica) {
            throw new ValidacaoException("Consulta fora do horario de fucinnamento da clinica");
        }
    }
}
