package br.unoeste.fipp.ativooperante2024.restcontrollers;

import br.unoeste.fipp.ativooperante2024.db.entities.Usuario;
import br.unoeste.fipp.ativooperante2024.security.JWTTokenProvider;
import br.unoeste.fipp.ativooperante2024.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="apis/security/")
public class AccessRestController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(value="/login")
    public ResponseEntity<Object> login(@RequestBody Usuario usuario){
        try {
            Usuario usuarioExistente = usuarioService.getByEmail(usuario.getEmail());
            if(usuarioExistente == null) {
                return ResponseEntity.badRequest().body("Usuário não cadastrado");
            }

            if(usuarioExistente.getSenha().equals(usuario.getSenha())) {
                String token = JWTTokenProvider.getToken(usuarioExistente.getEmail(), String.valueOf(usuarioExistente.getNivel()));
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.badRequest().body("Senha incorreta");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao autenticar usuário: " + e.getMessage());
        }
    }

    @PostMapping("/add-usuario")
    public ResponseEntity<Object> salvarUsuario(@RequestBody Usuario usuario) {
        if (!Usuario.isValidCpf(usuario.getCpf())) {
            return ResponseEntity.badRequest().body("CPF inválido");
        }
        if (!Usuario.isValidEmail(usuario.getEmail())) {
            return ResponseEntity.badRequest().body("E-mail inválido");
        }
        if (!Usuario.isValidSenha(usuario.getSenha())) {
            return ResponseEntity.badRequest().body("Senha inválida");
        }

        Usuario novo = usuarioService.save(usuario);
        return new ResponseEntity<>(novo, HttpStatus.OK);
    }
}