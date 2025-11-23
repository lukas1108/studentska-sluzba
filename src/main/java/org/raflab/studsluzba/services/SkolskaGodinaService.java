package org.raflab.studsluzba.services;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.model.entities.SkolskaGodina;
import org.raflab.studsluzba.repositories.SkolskaGodinaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SkolskaGodinaService {

    private final SkolskaGodinaRepository skolskaGodinaRepository;

    public SkolskaGodina addSkolskaGodina(SkolskaGodina sg){
        return skolskaGodinaRepository.save(sg);
    }

    public Optional<SkolskaGodina> findById(Long id){
        return skolskaGodinaRepository.findById(id);
    }

    public List<SkolskaGodina> findAll(){
        return skolskaGodinaRepository.findAll();
    }

    public void deleteById(Long id){
        skolskaGodinaRepository.deleteById(id);
    }

}
