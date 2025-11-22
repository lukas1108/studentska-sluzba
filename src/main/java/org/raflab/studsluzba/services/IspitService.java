package org.raflab.studsluzba.services;

import lombok.RequiredArgsConstructor;
import org.raflab.studsluzba.controllers.request.IspitRequest;
import org.raflab.studsluzba.model.entities.*;
import org.raflab.studsluzba.repositories.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IspitService {

    private final IspitRepository ispitRepository;
    private final IspitniRokRepository ispitniRokRepository;
    private final NastavnikRepository nastavnikRepository;
    private final PredmetRepository predmetRepository;

    public Long add(IspitRequest req) {

        IspitniRok rok = ispitniRokRepository.findById(req.getIspitniRokId()).orElseThrow();
        Nastavnik nastavnik = nastavnikRepository.findById(req.getNastavnikId()).orElseThrow();
        Predmet predmet = predmetRepository.findById(req.getPredmetId()).orElseThrow();

        Ispit i = new Ispit();
        i.setDatumVremePocetka(req.getDatumVremePocetka());
        i.setZakljucen(req.isZakljucen());
        i.setIspitniRok(rok);
        i.setNastavnik(nastavnik);
        i.setPredmet(predmet);

        return ispitRepository.save(i).getId();
    }

    public Optional<Ispit> findById(Long id) {
        return ispitRepository.findById(id);
    }

    public List<Ispit> findAll() {
        return (List<Ispit>) ispitRepository.findAll();
    }

    public void deleteById(Long id) {
        ispitRepository.deleteById(id);
    }
}
