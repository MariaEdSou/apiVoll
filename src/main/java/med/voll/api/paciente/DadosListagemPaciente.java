package med.voll.api.paciente;

import med.voll.api.medico.Endereco;

public record DadosListagemPaciente(

        String nome,
        String email,
        String telefone,
        Endereco endereco
) {
    public DadosListagemPaciente(Paciente paciente){
        this(paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getEndereco());
    }
}
