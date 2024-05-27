package br.unoeste.fipp.ativooperante2024.restcontrollers;

import br.unoeste.fipp.ativooperante2024.db.entities.Usuario;
import br.unoeste.fipp.ativooperante2024.security.JWTTokenProvider;
import br.unoeste.fipp.ativooperante2024.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/get-user")
    public ResponseEntity<Object> getUser(@RequestParam(value="usu_id") Long usu_id) {
        return ResponseEntity.ok(usuarioService.getById(usu_id));
    }
}