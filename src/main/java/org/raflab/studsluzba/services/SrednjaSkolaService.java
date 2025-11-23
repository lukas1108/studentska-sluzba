package org.raflab.studsluzba.services;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.model.entities.SrednjaSkola;
import org.raflab.studsluzba.repositories.SrednjaSkolaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SrednjaSkolaService {

    private final SrednjaSkolaRepository srednjaSkolaRepository;

    public SrednjaSkola addSrednjaSkola(SrednjaSkola ss){
        return srednjaSkolaRepository.save(ss);
    }

    public Optional<SrednjaSkola> findById(Long id){
        return srednjaSkolaRepository.findById(id);
    }

    public List<SrednjaSkola> findAll(){
        return srednjaSkolaRepository.findAll();
    }

    public void deleteById(Long id){
        srednjaSkolaRepository.deleteById(id);
    }
}
