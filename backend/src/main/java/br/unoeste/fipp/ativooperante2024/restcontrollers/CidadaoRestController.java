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

@RestController
@RequestMapping("apis/cidadao/")
public class CidadaoRestController {

}