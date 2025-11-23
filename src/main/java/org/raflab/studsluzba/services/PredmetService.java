package org.raflab.studsluzba.services;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.PredmetRequest;
import org.raflab.studsluzba.controllers.response.PredmetResponse;
import org.raflab.studsluzba.model.entities.Predmet;
import org.raflab.studsluzba.model.entities.StudijskiProgram;
import org.raflab.studsluzba.repositories.PredmetRepository;
import org.raflab.studsluzba.repositories.StudijskiProgramRepository;
import org.raflab.studsluzba.utils.Converters;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PredmetService {

    private final PredmetRepository predmetRepository;
    private final StudijskiProgramRepository studijskiProgramRepository;

    public Long addPredmet(PredmetRequest req) {
        Predmet p = Converters.toPredmet(req, studijskiProgramRepository);
        return predmetRepository.save(p).getId();
    }

    public Optional<Predmet> getPredmetById(Long id) {
        return predmetRepository.findById(id);
    }

    public List<PredmetResponse> getAllPredmeti() {
        return Converters.toPredmetResponseList(predmetRepository.findAll());
    }

    public void deletePredmet(Long id) {
        predmetRepository.deleteById(id);
    }

    public List<PredmetResponse> getPredmetiForGodinaAkreditacije(Integer godinaAkreditacije) {
        return Converters.toPredmetResponseList(predmetRepository.getPredmetForGodinaAkreditacije(godinaAkreditacije));
    }
}
