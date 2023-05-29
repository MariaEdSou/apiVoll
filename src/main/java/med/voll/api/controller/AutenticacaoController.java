package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.DadosAutenticacao;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.infra.security.DadosTokenJWT;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
    //private AuthenticationManager manager;por baixo dos panos chama a class que faz o processo de autenticaco
    //authenticate devolve um objwto que representa o usuario autenticado
    //UsernamePasswordAuthenticationTokenfaz a conversao do meu tdo p "dto do spring"
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    // authentication.getPrincipalp pegar o usuario logado
    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var autenticationtoken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(autenticationtoken);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        System.out.println(tokenJWT);

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));

    }
}
