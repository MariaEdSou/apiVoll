package med.voll.api.controller;

import java.time.LocalDate;

public record DadosDetalhamentoConsulta(

        long id,
        long idMedico,
        long idPaciente,
        LocalDate date
) {
}
