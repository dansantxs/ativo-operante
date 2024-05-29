package br.unoeste.fipp.ativooperante2024.services;

import br.unoeste.fipp.ativooperante2024.db.entities.Feedback;
import br.unoeste.fipp.ativooperante2024.db.repositories.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository repo;

    public Feedback save(Feedback feedback) {
        return repo.save(feedback);
    }

    public boolean delete(Long id) {
        try {
            repo.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Feedback getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Feedback> getAll() {
        return repo.findAll();
    }
}