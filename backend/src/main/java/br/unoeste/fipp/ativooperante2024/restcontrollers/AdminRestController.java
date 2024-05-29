package br.unoeste.fipp.ativooperante2024.restcontrollers;

import br.unoeste.fipp.ativooperante2024.db.entities.Denuncia;
import br.unoeste.fipp.ativooperante2024.services.DenunciaService;
import br.unoeste.fipp.ativooperante2024.db.entities.Orgao;
import br.unoeste.fipp.ativooperante2024.services.OrgaoService;
import br.unoeste.fipp.ativooperante2024.db.entities.Tipo;
import br.unoeste.fipp.ativooperante2024.services.TipoService;
import br.unoeste.fipp.ativooperante2024.db.entities.Usuario;
import br.unoeste.fipp.ativooperante2024.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://127.0.0.1:5500")
@RequestMapping("apis/adm/")
public class AdminRestController {
    @Autowired
    DenunciaService denunciaService;

    @PostMapping("/add-denuncia")
    public ResponseEntity<Object> salvarDenuncia (@RequestBody Denuncia denuncia) {
        Denuncia novo;
        novo=denunciaService.save(denuncia);
        return new ResponseEntity<>(novo, HttpStatus.OK);
    }

    @GetMapping("/delete-denuncia")
    public ResponseEntity<Object> excluirDenuncia(@RequestParam(value="id") Long id) {
        if(denunciaService.delete(id))
            return new ResponseEntity<>("",HttpStatus.OK);
        else
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get-denuncia")
    public ResponseEntity<Object> buscarUmaDenuncia(@RequestParam(value="id") Long id) {
        Denuncia denuncia;
        denuncia=denunciaService.getById(id);
        if(denuncia==null)
            denuncia=new Denuncia();
        return new ResponseEntity<>(denuncia,HttpStatus.OK);
    }

    @GetMapping("/get-all-denuncias")
    public ResponseEntity<Object> buscarTodasDenuncias() {
        return new ResponseEntity<>(denunciaService.getAll(),HttpStatus.OK);
    }

    @Autowired
    OrgaoService orgaoService;

    @PostMapping("/add-orgao")
    public ResponseEntity<Object> salvarOrgao (@RequestBody Orgao orgao) {
        Orgao novo;
        novo=orgaoService.save(orgao);
        return new ResponseEntity<>(novo, HttpStatus.OK);
    }

    @GetMapping("/delete-orgao")
    public ResponseEntity<Object> excluirOrgao(@RequestParam(value="id") Long id) {
        if(orgaoService.delete(id))
            return new ResponseEntity<>("",HttpStatus.OK);
        else
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get-orgao")
    public ResponseEntity<Object> buscarUmOrgao(@RequestParam(value="id") Long id) {
        Orgao orgao;
        orgao=orgaoService.getById(id);
        if(orgao==null)
            orgao=new Orgao();
        return new ResponseEntity<>(orgao,HttpStatus.OK);
    }

    @GetMapping("/get-all-orgaos")
    public ResponseEntity<Object> buscarTodosOrgaos() {
        return new ResponseEntity<>(orgaoService.getAll(),HttpStatus.OK);
    }

    @Autowired
    TipoService tipoService;

    @PostMapping("/add-tipo")
    public ResponseEntity<Object> salvarTipo (@RequestBody Tipo tipo) {
        Tipo novo;
        novo=tipoService.save(tipo);
        return new ResponseEntity<>(novo, HttpStatus.OK);
    }

    @GetMapping("/delete-tipo")
    public ResponseEntity<Object> excluirTipo(@RequestParam(value="id") Long id) {
        if(tipoService.delete(id))
            return new ResponseEntity<>("",HttpStatus.OK);
        else
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get-tipo")
    public ResponseEntity<Object> buscarUmTipo(@RequestParam(value="id") Long id) {
        Tipo tipo;
        tipo=tipoService.getById(id);
        if(tipo==null)
            tipo=new Tipo();
        return new ResponseEntity<>(tipo,HttpStatus.OK);
    }

    @GetMapping("/get-all-tipos")
    public ResponseEntity<Object> buscarTodosTipos() {
        return new ResponseEntity<>(tipoService.getAll(),HttpStatus.OK);
    }

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/add-usuario")
    public ResponseEntity<Object> salvarUsuario (@RequestBody Usuario usuario) {
        if (!usuario.isValidEmail(usuario.getEmail())) {
            return ResponseEntity.badRequest().body("Email inválido");
        }
        try {
            return new ResponseEntity<>(usuarioService.save(usuario), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/delete-usuario")
    public ResponseEntity<Object> excluirUsuario(@RequestParam(value="id") Long id) {
        if(usuarioService.delete(id))
            return new ResponseEntity<>("",HttpStatus.OK);
        else
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get-usuario")
    public ResponseEntity<Object> buscarUmUsuario(@RequestParam(value="id") Long id) {
        Usuario usuario;
        usuario=usuarioService.getById(id);
        if(usuario==null)
            usuario=new Usuario();
        return new ResponseEntity<>(usuario,HttpStatus.OK);
    }

    @GetMapping("/get-all-usuarios")
    public ResponseEntity<Object> buscarTodosUsuarios() {
        return new ResponseEntity<>(usuarioService.getAll(),HttpStatus.OK);
    }
}