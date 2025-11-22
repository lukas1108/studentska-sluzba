package org.raflab.studsluzba.services;

import lombok.RequiredArgsConstructor;
import org.raflab.studsluzba.controllers.request.NastavnikZvanjeRequest;
import org.raflab.studsluzba.model.entities.Nastavnik;
import org.raflab.studsluzba.model.entities.NastavnikZvanje;
import org.raflab.studsluzba.repositories.NastavnikRepository;
import org.raflab.studsluzba.repositories.NastavnikZvanjeRepository;
import org.raflab.studsluzba.utils.Converters;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NastavnikZvanjeService {

    private final NastavnikZvanjeRepository nastavnikZvanjeRepository;
    private final NastavnikRepository nastavnikRepository;

    public Long add(NastavnikZvanjeRequest req) {
        Nastavnik n = nastavnikRepository.findById(req.getNastavnikId()).orElseThrow();

        NastavnikZvanje nz = Converters.toNastavnikZvanje(req, n);
        return nastavnikZvanjeRepository.save(nz).getId();
    }

    public Optional<NastavnikZvanje> findById(Long id) {
        return nastavnikZvanjeRepository.findById(id);
    }

    public List<NastavnikZvanje> findAll() {
        return nastavnikZvanjeRepository.findAll();
    }

    public void deleteById(Long id) {
        nastavnikZvanjeRepository.deleteById(id);
    }
}
