package br.unoeste.fipp.ativooperante2024.restcontrollers;

import br.unoeste.fipp.ativooperante2024.db.entities.Denuncia;
import br.unoeste.fipp.ativooperante2024.db.entities.Orgao;
import br.unoeste.fipp.ativooperante2024.db.entities.Tipo;
import br.unoeste.fipp.ativooperante2024.db.entities.Usuario;
import br.unoeste.fipp.ativooperante2024.services.DenunciaService;
import br.unoeste.fipp.ativooperante2024.services.OrgaoService;
import br.unoeste.fipp.ativooperante2024.services.TipoService;
import br.unoeste.fipp.ativooperante2024.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("http://127.0.0.1:5500")
@RequestMapping(value = "apis/cidadao/")
public class CidadaoRestController {
    @Autowired
    DenunciaService denunciaService;
    @Autowired
    UsuarioService usuService;

    @PostMapping("/add-denuncia")
    public ResponseEntity<Object> salvarDenuncia (@RequestParam("den_titulo")String den_titulo,
                                                  @RequestParam("den_texto")String den_texto,
                                                  @RequestParam("den_urgencia")int den_urgencia,
                                                  @RequestParam("den_data") LocalDate data,
                                                  @RequestParam("org_id")Long org_id,
                                                  @RequestParam("tip_id")Long tip_id,
                                                  @RequestParam("usu_id")Long usu_id) {



        try{
            denunciaService.save(new Denuncia(0L,den_titulo,den_texto,den_urgencia,data,orgaoService.getById(org_id),tipoService.getById(tip_id),usuService.getById(usu_id)));


        }catch (Exception e){
            return ResponseEntity.badRequest().body("Erro ao cadastrar produtos"+e.getMessage());
        }
        return null;
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

    @GetMapping("/denuncias-do-usuario/{usuarioId}")
    public ResponseEntity<List<Denuncia>> getDenunciasDoUsuario(@PathVariable Long usuarioId) {
        List<Denuncia> denuncias = denunciaService.getDenunciasDoUsuario(usuarioId);
        return new ResponseEntity<>(denuncias, HttpStatus.OK);
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


}