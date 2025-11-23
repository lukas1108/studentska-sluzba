package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.entities.DrziPredmet;
import org.raflab.studsluzba.model.entities.PolozenPredmet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface PolozenPredmetRepository extends CrudRepository<PolozenPredmet, Long> {

    Page<PolozenPredmet> findByStudentIndeksIdAndOcenaIsNotNull(Long studentIndeksId, Pageable pageable);

    Page<PolozenPredmet> findByStudentIndeksIdAndOcenaIsNull(Long studentIndeksId, Pageable pageable);
}
