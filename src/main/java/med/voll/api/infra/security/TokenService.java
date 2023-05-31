package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import med.voll.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public static final String ISSUER = "API Vol.med";

    // fala p spring ler essa variavel la do application.properties
    @Value("${api.security.token.secret}")
    private String secret;

    //HMAC256 algoritimo p fazer assinatura digital do TOKEN
//withIssuer fala no cod. quem esta gerando o token
//withSubject pra falar quem e o dono do token
    public String gerarToken(Usuario usuario) {
        try {
            var algoritimo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritimo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("erro ao gerar token jwt", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritimo = Algorithm.HMAC256(secret);
            return JWT.require(algoritimo)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Token JWT invalido ou expirado");
        }

    }

    //Instant DO JAVA 8 DA API DE DATAS
//ZoneOffset.of p passar o fuso
//toInstant converter p um objeto instant
    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
