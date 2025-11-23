package org.raflab.studsluzba.repositories;

import java.util.List;
import java.util.Optional;

import org.raflab.studsluzba.model.entities.DrziPredmet;
import org.raflab.studsluzba.model.entities.Predmet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DrziPredmetRepository extends JpaRepository<DrziPredmet, Long> {

	// TVOJA metoda — ali sada zaista aktivna sk. godina:
	@Query("select dp.predmet from DrziPredmet dp " +
			"where dp.nastavnik.id = :idNastavnika " +
			"and dp.skolskaGodina.aktivna = true")
	List<Predmet> getPredmetiZaNastavnikaUAktivnojSkolskojGodini(Long idNastavnika);

	// TVOJA metoda — ostavi, ali često će nam trebati i godina:
	@Query("select dp from DrziPredmet dp where dp.nastavnik.id = :idNastavnik and dp.predmet.id = :idPredmet")
	DrziPredmet getDrziPredmetNastavnikPredmet(Long idPredmet, Long idNastavnik);

	// → Korisno za anti-duplikat (nastavnik+predmet+godina)
	boolean existsByNastavnikIdAndPredmetIdAndSkolskaGodinaId(Long nastavnikId, Long predmetId, Long skolskaGodinaId);
	Optional<DrziPredmet> findByNastavnikIdAndPredmetIdAndSkolskaGodinaId(Long nastavnikId, Long predmetId, Long skolskaGodinaId);

	// → Korisni filteri za READ:
	List<DrziPredmet> findAllBySkolskaGodinaId(Long skolskaGodinaId);
	List<DrziPredmet> findAllByNastavnikId(Long nastavnikId);
	List<DrziPredmet> findAllByPredmetId(Long predmetId);
	List<DrziPredmet> findAllBySkolskaGodinaIdAndNastavnikId(Long skolskaGodinaId, Long nastavnikId);
	List<DrziPredmet> findAllBySkolskaGodinaIdAndPredmetId(Long skolskaGodinaId, Long predmetId);
}
