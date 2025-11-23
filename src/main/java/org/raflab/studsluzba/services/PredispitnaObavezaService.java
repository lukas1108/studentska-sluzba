package org.raflab.studsluzba.services;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.PredispitnaObavezaRequest;
import org.raflab.studsluzba.model.entities.PredispitnaObaveza;
import org.raflab.studsluzba.model.entities.Predmet;
import org.raflab.studsluzba.repositories.PredispitnaObavezaRepository;
import org.raflab.studsluzba.repositories.PredmetRepository;
import org.raflab.studsluzba.utils.Converters;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PredispitnaObavezaService {

    private final PredispitnaObavezaRepository predispitnaObavezaRepository;
    private final PredmetRepository predmetRepository;

    public Long addPredispitnaObaveza(PredispitnaObavezaRequest req) {
        Predmet predmet = predmetRepository.findById(req.getPredmetId()).orElseThrow();
        PredispitnaObaveza p = Converters.toPredispitnaObaveza(req, predmet);
        return predispitnaObavezaRepository.save(p).getId();
    }

    public Optional<PredispitnaObaveza> findById(Long id) {
        return predispitnaObavezaRepository.findById(id);
    }

    public List<PredispitnaObaveza> findAll() {
        return (List<PredispitnaObaveza>) predispitnaObavezaRepository.findAll();
    }

    public void deleteById(Long id) {
        predispitnaObavezaRepository.deleteById(id);
    }
}
