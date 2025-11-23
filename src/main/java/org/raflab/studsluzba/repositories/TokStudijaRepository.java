package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.entities.TokStudija;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokStudijaRepository extends CrudRepository<TokStudija, Long> {
    List<TokStudija> findAllByStudentIndeksId(Long studentIndeksId);
}
