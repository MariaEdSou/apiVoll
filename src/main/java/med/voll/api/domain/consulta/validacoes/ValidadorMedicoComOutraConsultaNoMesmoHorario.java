package med.voll.api.domain.consulta.validacoes;

import med.voll.api.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadaorAgendamentoDeConsulta {
    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        var medicoPossuiOutraConsultaNoMesmoHorario = repository.existsByIdAndData(dados.idMedico(), dados.data());
        if (medicoPossuiOutraConsultaNoMesmoHorario) {
            throw new ValidacaoException("Medico ja possui outra consulta agendada nesse mesmo horario");
        }
    }


}
