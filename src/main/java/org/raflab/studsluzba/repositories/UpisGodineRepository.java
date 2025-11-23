package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.entities.UpisGodine;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface UpisGodineRepository extends CrudRepository<UpisGodine, Long> {
    List<UpisGodine> findByIdIn(Set<Long> ids);
}
