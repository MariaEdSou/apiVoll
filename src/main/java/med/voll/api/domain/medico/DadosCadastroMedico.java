package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroMedico(
        @NotBlank(message = "Nome e obrigatorio!")
        String nome,
        @NotBlank(message = "Email e obrigatorio!")
        @Email(message = "Formato de email invalido")
        String email,
        @NotBlank(message = "Telefone e obrigatorio")
        String telefone,
        @NotBlank(message = "CRM e obrigatorio")
        @Pattern(regexp = "\\d{4,6}", message = "Formato de CRM invalido")
        String crm,
        @NotNull(message = "Especialidade e obrigatoria")
        Especialidade especialidade,
        @NotNull(message = "Dados do endereco sao obrigatorio")
        @Valid
        DadosEndereco endereco) {
}
