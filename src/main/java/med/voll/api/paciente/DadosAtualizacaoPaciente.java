package med.voll.api.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.DadosEnderecoPaciente;

public record DadosAtualizacaoPaciente(

        @NotNull
        Long id,
        String nome,
        String email,
        String telefone,
        DadosEnderecoPaciente enderecoPaciente


) {
}
