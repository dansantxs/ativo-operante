
package br.unoeste.fipp.ativooperante2024.restcontrollers;

import br.unoeste.fipp.ativooperante2024.db.entities.Usuario;
/*
import br.unoeste.fipp.ativooperante2024.security.JWTTokenProvider;
*/

import br.unoeste.fipp.ativooperante2024.services.DenunciaService;
import br.unoeste.fipp.ativooperante2024.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://127.0.0.1:5500")
@RequestMapping(value="apis/security/")
public class AccessRestController {
        @Autowired
        private UsuarioService userService;
/*
        @PostMapping(value="/login")
        public ResponseEntity<Object> login(@RequestBody Usuario user){
                try {
                        Usuario existingUser = userService.getByEmail(user.getEmail());
                        if(existingUser == null) {
                                return ResponseEntity.badRequest().body("Usuário não cadastrado");
                        }

                        if(existingUser.getSenha() == (user.getSenha())) {
                                // Gera o token JWT
                                String token = JWTTokenProvider.getToken(existingUser.getEmail(), existingUser.getNivel());
                                return ResponseEntity.ok(token);
                        } else {
                                return ResponseEntity.badRequest().body("Senha incorreta");
                        }
                } catch (Exception e) {
                        return ResponseEntity.badRequest().body("Erro ao autenticar usuário: " + e.getMessage());
                }
        }

 */

        @GetMapping("/get-user")
        public ResponseEntity<Object> getUser(@RequestParam(value="usu_id") Long usu_id)
        {
                return ResponseEntity.ok(userService.getById(usu_id));
        }
}
