package org.raflab.studsluzba.services;

import lombok.RequiredArgsConstructor;
import org.raflab.studsluzba.controllers.request.UpisGodineRequest;
import org.raflab.studsluzba.controllers.response.UpisGodineResponse;
import org.raflab.studsluzba.model.entities.*;
import org.raflab.studsluzba.repositories.*;
import org.raflab.studsluzba.utils.Converters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UpisGodineService {

    private final UpisGodineRepository upisGodineRepository;
    private final StudentIndeksRepository studentIndeksRepository;
    private final SkolskaGodinaRepository skolskaGodinaRepository;
    private final SlusaPredmetRepository slusaPredmetRepository;

    // CREATE
    @Transactional
    public UpisGodineResponse create(UpisGodineRequest req) {
        // Minimalna ručna validacija za create
        require(req.getGodinaStudija() != null, "godinaStudija je obavezna");
        require(req.getDatum() != null, "datum je obavezan");
        require(req.getStudentIndeksId() != null, "studentIndeksId je obavezan");
        require(req.getSkolskaGodinaId() != null, "skolskaGodinaId je obavezan");

        StudentIndeks si = studentIndeksRepository.findById(req.getStudentIndeksId())
                .orElseThrow(() -> new NoSuchElementException("StudentIndeks ne postoji: id=" + req.getStudentIndeksId()));
        SkolskaGodina sg = skolskaGodinaRepository.findById(req.getSkolskaGodinaId())
                .orElseThrow(() -> new NoSuchElementException("SkolskaGodina ne postoji: id=" + req.getSkolskaGodinaId()));

        // Anti-duplikat (jedan upis po studentu za istu šk. godinu)
        boolean exists = StreamSupport.stream(upisGodineRepository.findAll().spliterator(), false)
                .anyMatch(u -> u.getStudentIndeks() != null && u.getSkolskaGodina() != null
                        && Objects.equals(u.getStudentIndeks().getId(), si.getId())
                        && Objects.equals(u.getSkolskaGodina().getId(), sg.getId()));
        if (exists) throw new IllegalStateException("Upis za datu školsku godinu već postoji za ovog studenta.");

        UpisGodine u = new UpisGodine();
        u.setGodinaStudija(req.getGodinaStudija());
        u.setDatum(req.getDatum());
        u.setNapomena(req.getNapomena());
        u.setStudentIndeks(si);
        u.setSkolskaGodina(sg);

        if (req.getPredmetiKojePrenosiIds() != null) {
            List<SlusaPredmet> sp = slsaByIds(req.getPredmetiKojePrenosiIds());
            u.setPredmetiKojePrenosi(new HashSet<>(sp));
        }

        return Converters.toUpisGodineResponse(upisGodineRepository.save(u));
    }

    // READ: list sa opcionalnim filterima
    @Transactional(readOnly = true)
    public List<UpisGodineResponse> list(Long studentIndeksId, Long skolskaGodinaId) {
        List<UpisGodine> all = StreamSupport.stream(upisGodineRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        if (studentIndeksId != null) {
            all = all.stream()
                    .filter(u -> u.getStudentIndeks() != null && Objects.equals(u.getStudentIndeks().getId(), studentIndeksId))
                    .collect(Collectors.toList());
        }
        if (skolskaGodinaId != null) {
            all = all.stream()
                    .filter(u -> u.getSkolskaGodina() != null && Objects.equals(u.getSkolskaGodina().getId(), skolskaGodinaId))
                    .collect(Collectors.toList());
        }
        return Converters.toUpisGodineResponseList(all);
    }

    // READ: single
    @Transactional(readOnly = true)
    public UpisGodineResponse get(Long id) {
        UpisGodine u = upisGodineRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("UpisGodine ne postoji: id=" + id));
        return Converters.toUpisGodineResponse(u);
    }

    // UPDATE (koristi isti request DTO; null = ne menjaj)
    @Transactional
    public UpisGodineResponse update(Long id, UpisGodineRequest req) {
        UpisGodine u = upisGodineRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("UpisGodine ne postoji: id=" + id));

        if (req.getGodinaStudija() != null) u.setGodinaStudija(req.getGodinaStudija());
        if (req.getDatum() != null) u.setDatum(req.getDatum());
        if (req.getNapomena() != null) u.setNapomena(req.getNapomena());

        if (req.getStudentIndeksId() != null) {
            StudentIndeks si = studentIndeksRepository.findById(req.getStudentIndeksId())
                    .orElseThrow(() -> new NoSuchElementException("StudentIndeks ne postoji: id=" + req.getStudentIndeksId()));
            u.setStudentIndeks(si);
        }
        if (req.getSkolskaGodinaId() != null) {
            SkolskaGodina sg = skolskaGodinaRepository.findById(req.getSkolskaGodinaId())
                    .orElseThrow(() -> new NoSuchElementException("SkolskaGodina ne postoji: id=" + req.getSkolskaGodinaId()));
            u.setSkolskaGodina(sg);
        }

        // Anti-duplikat posle eventualne promene studenta/godine
        if (u.getStudentIndeks() != null && u.getSkolskaGodina() != null) {
            boolean conflict = StreamSupport.stream(upisGodineRepository.findAll().spliterator(), false)
                    .anyMatch(other -> !Objects.equals(other.getId(), u.getId())
                            && other.getStudentIndeks() != null
                            && other.getSkolskaGodina() != null
                            && Objects.equals(other.getStudentIndeks().getId(), u.getStudentIndeks().getId())
                            && Objects.equals(other.getSkolskaGodina().getId(), u.getSkolskaGodina().getId()));
            if (conflict) throw new IllegalStateException("Upis za datu školsku godinu već postoji za ovog studenta.");
        }

        if (req.getPredmetiKojePrenosiIds() != null) {
            if (req.getPredmetiKojePrenosiIds().isEmpty()) {
                u.setPredmetiKojePrenosi(Collections.emptySet());
            } else {
                List<SlusaPredmet> sp = slsaByIds(req.getPredmetiKojePrenosiIds());
                u.setPredmetiKojePrenosi(new HashSet<>(sp));
            }
        }

        return Converters.toUpisGodineResponse(upisGodineRepository.save(u));
    }

    // DELETE
    @Transactional
    public void delete(Long id) {
        if (!upisGodineRepository.existsById(id)) {
            throw new NoSuchElementException("UpisGodine ne postoji: id=" + id);
        }
        upisGodineRepository.deleteById(id);
    }

    private static void require(boolean cond, String msg) {
        if (!cond) throw new IllegalArgumentException(msg);
    }

    private List<SlusaPredmet> slsaByIds(Set<Long> ids) {
        if (ids == null || ids.isEmpty()) return Collections.emptyList();
        return slusaPredmetRepository.findByIdIn(ids);
    }
}
