package med.voll.api.domain.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEnderecoPaciente;

public record DadosAtualizacaoPaciente(

        @NotNull
        Long id,
        String nome,
        String email,
        String telefone,
        DadosEnderecoPaciente enderecoPaciente


) {
}
