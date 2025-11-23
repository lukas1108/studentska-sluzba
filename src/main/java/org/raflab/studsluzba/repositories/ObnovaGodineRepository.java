// repositories/ObnovaGodineRepository.java
package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.entities.ObnovaGodine;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface ObnovaGodineRepository extends CrudRepository<ObnovaGodine, Long> {
    List<ObnovaGodine> findByIdIn(Set<Long> ids);
}
