package med.voll.api.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.medico.Endereco;

public record DadosListagemPaciente(

        @NotNull
        Long id,
        String nome,
        String email,
        String telefone,
        Endereco endereco
) {
    public DadosListagemPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getEndereco());
    }
}
