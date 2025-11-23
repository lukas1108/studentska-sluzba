package org.raflab.studsluzba.model.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(
		name = "drzi_predmet",
		uniqueConstraints = @UniqueConstraint(
				name = "uk_drzi_predmet_nastavnik_predmet_skgod",
				columnNames = {"nastavnik_id", "predmet_id", "skolska_godina_id"}
		),
		indexes = {
				@Index(name = "ix_drzi_predmet_skgod", columnList = "skolska_godina_id"),
				@Index(name = "ix_drzi_predmet_nastavnik", columnList = "nastavnik_id"),
				@Index(name = "ix_drzi_predmet_predmet", columnList = "predmet_id")
		}
)
public class DrziPredmet {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "nastavnik_id", nullable = false)
	private Nastavnik nastavnik;

	@ManyToOne(optional = false)
	@JoinColumn(name = "predmet_id", nullable = false)
	private Predmet predmet;

	@ManyToOne(optional = false)
	@JoinColumn(name = "skolska_godina_id", nullable = false)
	private SkolskaGodina skolskaGodina;
}
