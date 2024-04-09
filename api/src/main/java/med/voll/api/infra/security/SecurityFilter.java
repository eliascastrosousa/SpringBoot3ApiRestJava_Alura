package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
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

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private TokenService tokenService ;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var tokenJWT = recuperarToken(request); //recupera o token


        if (tokenJWT != null){ // se estiver vindo
            var subject = tokenService.getSubject(tokenJWT); // recupera o subject no cabecalho
            var usuario = repository.findByLogin(subject); // valida se o token esta correto

            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()); // pega o email e a senha do usuario e valida

            SecurityContextHolder.getContext().setAuthentication(authentication); // carrega esse objeto usuario do bd em um DTO que representa o usuario e força autenticação
            System.out.println("logado na req");

        }
        filterChain.doFilter(request, response); // segue o fluxo da requisição
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null){
            return authorizationHeader.replace("Bearer ", "");
            //throw new RuntimeException("Token não enviado no cabeçado Authorization");
        }
        return null;
        //var token = tokenService.getSubject(token);

    }
}
