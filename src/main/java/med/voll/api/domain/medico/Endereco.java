package med.voll.api.domain.medico;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.domain.endereco.DadosEndereco;
import med.voll.api.domain.endereco.DadosEnderecoPaciente;

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

    public void atualizarInformacoes(DadosEndereco dados) {
        if (logradouro != null) {
            this.logradouro = dados.logradouro();
        }
        if (bairro != null) {
            this.bairro = dados.bairro();
        }
        if (cep != null) {
            this.cep = dados.cep();
        }
        if (uf != null) {
            this.uf = dados.uf();
        }
        if (cidade != null) {
            this.cidade = dados.cidade();
        }
        if (numero != null) {
            this.numero = dados.numero();
        }
        if (complemento != null) {
            this.complemento = dados.complemento();
        }

    }

    public void atualizarInformacoesPac(DadosEnderecoPaciente enderecoPaciente) {
        if (logradouro != null) {
            this.logradouro = enderecoPaciente.logradouro();
        }
        if (bairro != null) {
            this.bairro = enderecoPaciente.bairro();
        }
        if (cep != null) {
            this.cep = enderecoPaciente.cep();
        }
        if (uf != null) {
            this.uf = enderecoPaciente.uf();
        }
        if (cidade != null) {
            this.cidade = enderecoPaciente.cidade();
        }
        if (numero != null) {
            this.numero = enderecoPaciente.numero();
        }
        if (complemento != null) {
            this.complemento = enderecoPaciente.complemento();
        }

    }
}
