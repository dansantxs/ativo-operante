package br.unoeste.fipp.ativooperante2024.restcontrollers;

import br.unoeste.fipp.ativooperante2024.db.entities.*;
import br.unoeste.fipp.ativooperante2024.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("apis/cidadao/")
public class CidadaoRestController {
    @Autowired
    DenunciaService denunciaService;

    @PostMapping("/add-denuncia")
    public ResponseEntity<Object> salvarDenuncia (@RequestBody Denuncia denuncia) {
        Denuncia novo;
        novo=denunciaService.save(denuncia);
        return new ResponseEntity<>(novo, HttpStatus.OK);
    }

    @Autowired
    FeedbackService feedbackService;

    @GetMapping("/get-feedback")
    public ResponseEntity<Object> buscarUmFeedback(@RequestParam(value="id") Long id) {
        Feedback feedback;
        feedback=feedbackService.getById(id);
        if(feedback==null)
            feedback=new Feedback();
        return new ResponseEntity<>(feedback,HttpStatus.OK);
    }

    @Autowired
    TipoService tipoService;

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
    OrgaoService orgaoService;

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
}