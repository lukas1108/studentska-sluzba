package org.raflab.studsluzba.services;

import lombok.RequiredArgsConstructor;
import org.raflab.studsluzba.controllers.request.VisokoskolskaUstanovaRequest;
import org.raflab.studsluzba.model.entities.VisokoskolskaUstanova;
import org.raflab.studsluzba.repositories.VisokoskolskaUstanovaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class VisokoskolskaUstanovaService {

    private final VisokoskolskaUstanovaRepository repo;

    public Long add(VisokoskolskaUstanovaRequest req) {
        VisokoskolskaUstanova v = new VisokoskolskaUstanova();
        v.setNaziv(req.getNaziv());
        v.setMesto(req.getMesto());
        v.setVrsta(req.getVrsta());
        return repo.save(v).getId();
    }

    public Optional<VisokoskolskaUstanova> findById(Long id) {
        return repo.findById(id);
    }

    public List<VisokoskolskaUstanova> findAll() {
        Iterable<VisokoskolskaUstanova> it = repo.findAll();
        return StreamSupport.stream(it.spliterator(), false).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
