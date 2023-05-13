package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedicos dados) {
        repository.save(new Medico(dados));
    }

    //usando list o findAll()devolve uma list de medicos, por isso precisa fazer a conversao chamando o .stream(java8) fazendo .map mapeamento de medico p dadosListagem
//DadosListagemMedicos::new chama o construtor da classe DaddosListagem
    //.toList() converte tudo p lista
    //com page nao precisa do stream, pq o findAll devolve um page e o page ja tem o metodo map diretamente, tbm nao precisa de tolist pq o map ja faz a convercao
    @GetMapping
    public Page<DadosListagemMedicos> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedicos::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    //parametro dinamico/ p passar o numero do id// PathVariable pega o parameto dinamico
    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
    var medico = repository.getReferenceById(id);
    medico.excluir();
    }


}
