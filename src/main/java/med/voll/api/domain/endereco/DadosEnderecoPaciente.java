package med.voll.api.domain.endereco;

public record DadosEnderecoPaciente(
        String logradouro,
        String bairro,
        String cep,
        String cidade,
        String uf,
        String numero,
        String complemento

) {
}
