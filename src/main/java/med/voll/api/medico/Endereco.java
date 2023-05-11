package med.voll.api.medico;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.endereco.DadosEndereco;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @NotBlank
    private String logradouro;
    @NotBlank
    private String bairro;
    @NotBlank
    @Pattern(regexp = "\\d{8}")
    private String cep;
    private String numero;
    private String complemento;
    @NotBlank
    private String cidade;
    @NotBlank
    private String uf;

    public Endereco(DadosEndereco dados) {
        this.logradouro = dados.logradouro();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
        this.cidade = dados.cidade();
        this.uf = dados.uf();
    }
}
