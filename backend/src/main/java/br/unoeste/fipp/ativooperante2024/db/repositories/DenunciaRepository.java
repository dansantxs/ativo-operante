package br.unoeste.fipp.ativooperante2024.db.repositories;

import br.unoeste.fipp.ativooperante2024.db.entities.Denuncia;
import br.unoeste.fipp.ativooperante2024.db.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DenunciaRepository extends JpaRepository<Denuncia,Long> {
    public List<Denuncia> findAllByUsuario(Usuario usuario);
}

