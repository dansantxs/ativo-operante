package br.unoeste.fipp.ativooperante2024.restcontrollers;

import br.unoeste.fipp.ativooperante2024.db.entities.*;
import br.unoeste.fipp.ativooperante2024.services.*;
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
    FeedbackService feedbackService;

    @PostMapping("/add-feedback")
    public ResponseEntity<Object> salvarFeedback (@RequestBody Feedback feedback) {
        if (feedback.getDenuncia() != null && feedback.getDenuncia().getId() != null) {
            Denuncia denuncia = denunciaService.getById(feedback.getDenuncia().getId());
            feedback.setDenuncia(denuncia);
        }
        Feedback novo = feedbackService.save(feedback);
        return new ResponseEntity<>(novo, HttpStatus.OK);
    }

    @PutMapping("/update-feedback")
    public ResponseEntity<Object> atualizarFeedback(@RequestBody Feedback feedback) {
        if (feedback.getDenuncia() != null && feedback.getDenuncia().getId() != null) {
            Denuncia denuncia = denunciaService.getById(feedback.getDenuncia().getId());
            if (denuncia == null) {
                return new ResponseEntity<>("Denuncia não encontrada", HttpStatus.BAD_REQUEST);
            }
            feedback.setDenuncia(denuncia);
        }
        Feedback atualizado = feedbackService.save(feedback);
        return new ResponseEntity<>(atualizado, HttpStatus.OK);
    }

    @DeleteMapping("/delete-feedback")
    public ResponseEntity<Object> excluirFeedback(@RequestParam(value="id") Long id) {
        if(feedbackService.delete(id))
            return new ResponseEntity<>("",HttpStatus.OK);
        else
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get-feedback")
    public ResponseEntity<Object> buscarUmFeedback(@RequestParam(value="id") Long id) {
        Feedback feedback;
        feedback=feedbackService.getById(id);
        if(feedback==null)
            feedback=new Feedback();
        return new ResponseEntity<>(feedback,HttpStatus.OK);
    }

    @GetMapping("/get-all-feedbacks")
    public ResponseEntity<Object> buscarTodosFeedbacks() {
        try {
            List<Feedback> feedbacks = feedbackService.getAll();
            return new ResponseEntity<>(feedbacks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    TipoService tipoService;

    @PostMapping("/add-tipo")
    public ResponseEntity<Object> salvarTipo(@RequestBody Tipo tipo) {
        Tipo novo = tipoService.save(tipo);
        return new ResponseEntity<>(novo, HttpStatus.OK);
    }

    @PutMapping("/update-tipo")
    public ResponseEntity<Object> atualizarTipo(@RequestBody Tipo tipo) {
        Tipo atualizado = tipoService.save(tipo);
        return new ResponseEntity<>(atualizado, HttpStatus.OK);
    }

    @DeleteMapping("/delete-tipo")
    public ResponseEntity<Object> excluirTipo(@RequestParam(value="id") Long id) {
        if(tipoService.delete(id)) {
            return new ResponseEntity<>("", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-tipo")
    public ResponseEntity<Object> buscarUmTipo(@RequestParam(value="id") Long id) {
        Tipo tipo = tipoService.getById(id);
        return new ResponseEntity<>(tipo != null ? tipo : new Tipo(), HttpStatus.OK);
    }

    @GetMapping("/get-all-tipos")
    public ResponseEntity<Object> buscarTodosTipos() {
        return new ResponseEntity<>(tipoService.getAll(), HttpStatus.OK);
    }

    @Autowired
    OrgaoService orgaoService;



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

    @PostMapping("/add-orgao")
    public ResponseEntity<Object> salvarOrgao(@RequestBody Orgao orgao) {
        Orgao novo = orgaoService.save(orgao);
        return new ResponseEntity<>(novo, HttpStatus.OK);

    }

    @PutMapping("/update-orgao")
    public ResponseEntity<Object> atualizarOrgao(@RequestBody Orgao orgao) {
        Orgao atualizado = orgaoService.save(orgao);
        return new ResponseEntity<>(atualizado, HttpStatus.OK);
    }

    @DeleteMapping("/delete-orgao")
    public ResponseEntity<Object> excluirOrgao(@RequestParam(value="id") Long id) {
        if(orgaoService.delete(id)) {
            return new ResponseEntity<>("", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-orgao")
    public ResponseEntity<Object> buscarUmOrgao(@RequestParam(value="id") Long id) {
        Orgao orgao = orgaoService.getById(id);
        return new ResponseEntity<>(orgao != null ? orgao : new Orgao(), HttpStatus.OK);
    }

    @GetMapping("/get-all-orgaos")
    public ResponseEntity<Object> buscarTodosOrgaos() {
        return new ResponseEntity<>(orgaoService.getAll(), HttpStatus.OK);
    }
}