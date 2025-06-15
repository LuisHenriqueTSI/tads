package br.ifsul.edu.cstsi.luishenrique_tads.api.authentication;

import br.ifsul.edu.cstsi.luishenrique_tads.api.infra.security.TokenService;
import br.ifsul.edu.cstsi.luishenrique_tads.api.user.User;
import br.ifsul.edu.cstsi.luishenrique_tads.api.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

    private final AuthenticationManager manager;
    private final TokenService tokenService;
    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthenticationController(AuthenticationManager manager, TokenService tokenService,
                                    UserRepository repository, BCryptPasswordEncoder passwordEncoder) {
        this.manager = manager;
        this.tokenService = tokenService;
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    // Endpoint de login (mantido igual)
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody UserAuthenticationDTO data){
        var authenticationDTO = new UsernamePasswordAuthenticationToken(data.user(), data.password());
        Authentication authentication = manager.authenticate(authenticationDTO);
        UserDetails user = (UserDetails) authentication.getPrincipal();
        String token = tokenService.generateToken(user);
        return ResponseEntity.ok(new TokenDTO(token));
    }

    // DTO para registro (pode criar este arquivo separado, mas vamos deixar aqui por enquanto)
    public record RegisterDTO(String username, String password, String role) {}

    // Novo endpoint para criar usuários
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {
        if (repository.findByUsername(registerDTO.username()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        User user = new User();
        user.setUsername(registerDTO.username());
        user.setPassword(passwordEncoder.encode(registerDTO.password()));
        user.setRole(registerDTO.role());
        repository.save(user);

        return ResponseEntity.ok("User registered successfully");
    }
}
