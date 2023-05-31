package med.voll.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//@component usada p spring carregar uma classe/componente generico
//OncePerRequestFilter classe do spring que implementa a a interface de filter, a classe garante que ela so vai ser executada uma vez por requisicao
//request pega coisas da requisicao,response p enviar coisas na resposta, filterChain representa a cadeia de filtros que existe na aplicacao
//filterChain.doFilter p chamar os px filtro e continuar o fluxo da requisicao
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository repository;

//tokenJWT = recuperarToken(request); recupera o token do cabecalho
//var tokenJWT = recuperarToken(request);recupera o token do cabecalho
//var subject = tokenService.getSubject(tokenJWT) validando se o token esta correto
//SecurityContextHolder.getContext().setAuthentication(); p spring fazer a autenticacao do usuario na requisicao
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("CHAMANDO FILTRO");
        var tokenJWT = recuperarToken(request);

        if (tokenJWT != null) {
            var subject = tokenService.getSubject(tokenJWT);
            var ususario = repository.findByLogin(subject);

            var autentication = new UsernamePasswordAuthenticationToken(ususario, null, ususario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(autentication);
            System.out.println("LOGADO NA REQUISICAO");

        }
        filterChain.doFilter(request, response);
    }

    //getHeader p pegar um cabecalho, passando o nome do cabecalho
    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}
