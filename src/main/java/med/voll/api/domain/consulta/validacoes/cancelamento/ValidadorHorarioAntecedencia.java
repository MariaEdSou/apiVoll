package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioDeAntecedenciaCancelamento")
public class ValidadorHorarioAntecedencia implements ValidadorCancelamentoDeConsulta {
    @Autowired
    private ConsultaRepository repository;


    @Override
    public void validar(DadosCancelamentoConsulta dados) {
        var consulta = repository.getReferenceById(dados.idConsulta());
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();

        if (diferencaEmHoras < 24) {
            throw new ValidacaoException("Consulta somente pode ser cancelada com antecedencia minima de 24h!");
        }


    }
}
