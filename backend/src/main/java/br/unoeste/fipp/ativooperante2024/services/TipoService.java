package br.unoeste.fipp.ativooperante2024.services;

import br.unoeste.fipp.ativooperante2024.db.entities.Tipo;
import br.unoeste.fipp.ativooperante2024.db.repositories.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoService {
    @Autowired
    private TipoRepository repo;

    public Tipo save(Tipo tipo) {
        return repo.save(tipo);
    }

    public boolean delete(Long id) {
        try {
            repo.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Tipo getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Tipo> getAll() {
        return repo.findAll();
    }


}