package med.voll.api.paciente;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.medico.Endereco;
import med.voll.api.paciente.DadosCadastroPaciente;

@Entity(name = "Paciente")
@Table(name = "paciente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private boolean pendente = true;
    @Embedded
    private Endereco endereco;

    public Paciente(DadosCadastroPaciente dados) {
        this.pendente = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());

    }

    public void atualizacaoEnderecoPac(DadosAtualizacaoPaciente dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.enderecoPaciente() != null) {
          this.endereco.atualizarInformacoesPac(dados.enderecoPaciente());
        }
    }

    public void excluir() {
        this.pendente = false;
    }
}
