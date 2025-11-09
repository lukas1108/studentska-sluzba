package org.raflab.studsluzba.utils;

import org.raflab.studsluzba.controllers.request.*;
import org.raflab.studsluzba.controllers.response.NastavnikResponse;
import org.raflab.studsluzba.controllers.response.StudentIndeksResponse;
import org.raflab.studsluzba.controllers.response.StudentPodaciResponse;
import org.raflab.studsluzba.controllers.response.StudijskiProgramResponse;
import org.raflab.studsluzba.model.entities.Nastavnik;
import org.raflab.studsluzba.model.entities.StudentIndeks;
import org.raflab.studsluzba.model.entities.StudentPodaci;
import org.raflab.studsluzba.model.entities.StudijskiProgram;

import java.util.ArrayList;
import java.util.List;

public class Converters {
    // konvertovanje izmedju entity, request i response objekata

    public static Nastavnik toNastavnik(NastavnikRequest nastavnikRequest) {
        Nastavnik nastavnik = new Nastavnik();
        nastavnik.setIme(nastavnikRequest.getIme());
        nastavnik.setPrezime(nastavnikRequest.getPrezime());
        nastavnik.setSrednjeIme(nastavnikRequest.getSrednjeIme());
        nastavnik.setEmail(nastavnikRequest.getEmail());
        nastavnik.setBrojTelefona(nastavnikRequest.getBrojTelefona());
        nastavnik.setAdresa(nastavnikRequest.getAdresa());
        nastavnik.setZvanja(nastavnikRequest.getZvanja());
        nastavnik.setDatumRodjenja(nastavnikRequest.getDatumRodjenja());
        nastavnik.setPol(nastavnikRequest.getPol());
        nastavnik.setJmbg(nastavnikRequest.getJmbg());
        return nastavnik;
    }

    public static NastavnikResponse toNastavnikResponse(Nastavnik nastavnik) {
        NastavnikResponse response = new NastavnikResponse();
        response.setId(nastavnik.getId());
        response.setIme(nastavnik.getIme());
        response.setPrezime(nastavnik.getPrezime());
        response.setSrednjeIme(nastavnik.getSrednjeIme());
        response.setEmail(nastavnik.getEmail());
        response.setBrojTelefona(nastavnik.getBrojTelefona());
        response.setAdresa(nastavnik.getAdresa());
        response.setZvanja(nastavnik.getZvanja());
        response.setDatumRodjenja(nastavnik.getDatumRodjenja());
        response.setPol(nastavnik.getPol());
        response.setJmbg(nastavnik.getJmbg());
        return response;
    }

    public static List<NastavnikResponse> toNastavnikResponseList(Iterable<Nastavnik> nastavnikIterable) {
        List<NastavnikResponse> nastavnikResponses = new ArrayList<>();

        nastavnikIterable.forEach((nastavnik) -> {
            nastavnikResponses.add(toNastavnikResponse(nastavnik));
        });
        return nastavnikResponses;
    }

    public static StudentPodaci toStudentPodaci(StudentPodaciRequest request) {
        StudentPodaci studentPodaci = new StudentPodaci();

        studentPodaci.setIme(request.getIme());
        studentPodaci.setPrezime(request.getPrezime());
        studentPodaci.setSrednjeIme(request.getSrednjeIme());

        studentPodaci.setJmbg(request.getJmbg());
        studentPodaci.setPol(request.getPol());

        studentPodaci.setDatumRodjenja(request.getDatumRodjenja());
        studentPodaci.setDrzavaRodjenja(request.getDrzavaRodjenja());
        studentPodaci.setMestoRodjenja(request.getMestoRodjenja());

        studentPodaci.setDrzavljanstvo(request.getDrzavljanstvo());
        studentPodaci.setNacionalnost(request.getNacionalnost());

        studentPodaci.setMestoPrebivalista(request.getMestoPrebivalista());
        studentPodaci.setAdresaPrebivalista(request.getAdresaPrebivalista());

        studentPodaci.setBrojTelefonaMobilni(request.getBrojTelefonaMobilni());
        studentPodaci.setBrojTelefonaFiksni(request.getBrojTelefonaFiksni());

        studentPodaci.setEmailFakultetski(request.getEmailFakultetski());
        studentPodaci.setEmailPrivatni(request.getEmailPrivatni());

        studentPodaci.setBrojLicneKarte(request.getBrojLicneKarte());
        studentPodaci.setLicnuKartuIzdao(request.getLicnuKartuIzdao());

        studentPodaci.setUspehSrednjaSkola(request.getUspehSrednjaSkola());
        studentPodaci.setUspehPrijemni(request.getUspehPrijemni());

        return studentPodaci;
    }


    public static StudentIndeks toStudentIndeks(StudentIndeksRequest studentIndeksRequest) {
        StudentIndeks studentIndeks = new StudentIndeks();
        studentIndeks.setGodina(studentIndeksRequest.getGodina());
        studentIndeks.setStudProgramOznaka(studentIndeksRequest.getStudProgramOznaka());
        studentIndeks.setNacinFinansiranja(studentIndeksRequest.getNacinFinansiranja());
        studentIndeks.setAktivan(studentIndeksRequest.isAktivan());
        studentIndeks.setVaziOd(studentIndeksRequest.getVaziOd());
        return studentIndeks;
    }

    public static StudentPodaciResponse toStudentPodaciResponse(StudentPodaci student) {
        if (student == null) return null;
        StudentPodaciResponse response = new StudentPodaciResponse();
        response.setId(student.getId());
        response.setIme(student.getIme());
        response.setPrezime(student.getPrezime());
        response.setSrednjeIme(student.getSrednjeIme());
        response.setJmbg(student.getJmbg());
        response.setPol(student.getPol());
        response.setDatumRodjenja(student.getDatumRodjenja());
        response.setDrzavaRodjenja(student.getDrzavaRodjenja());
        response.setMestoRodjenja(student.getMestoRodjenja());
        response.setDrzavljanstvo(student.getDrzavljanstvo());
        response.setNacionalnost(student.getNacionalnost());
        response.setMestoPrebivalista(student.getMestoPrebivalista());
        response.setAdresaPrebivalista(student.getAdresaPrebivalista());
        response.setBrojTelefonaMobilni(student.getBrojTelefonaMobilni());
        response.setBrojTelefonaFiksni(student.getBrojTelefonaFiksni());
        response.setEmailFakultetski(student.getEmailFakultetski());
        response.setEmailPrivatni(student.getEmailPrivatni());
        response.setBrojLicneKarte(student.getBrojLicneKarte());
        response.setLicnuKartuIzdao(student.getLicnuKartuIzdao());
        response.setUspehSrednjaSkola(student.getUspehSrednjaSkola());
        response.setUspehPrijemni(student.getUspehPrijemni());
        return response;
    }

    public static StudijskiProgramResponse toStudijskiProgramResponse(StudijskiProgram sp) {
        if (sp == null) return null;
        StudijskiProgramResponse response = new StudijskiProgramResponse();
        response.setId(sp.getId());
        response.setOznaka(sp.getOznaka());
        response.setNaziv(sp.getNaziv());
        response.setGodinaAkreditacije(sp.getGodinaAkreditacije());
        response.setZvanje(sp.getZvanje());
        response.setTrajanjeGodina(sp.getTrajanjeGodina());
        response.setTrajanjeSemestara(sp.getTrajanjeSemestara());
        response.setVrstaStudija(sp.getVrstaStudija());
        response.setUkupnoEspb(sp.getUkupnoEspb());
        return response;
    }

    public static StudentIndeksResponse toStudentIndeksResponse(StudentIndeks si) {
        if (si == null) return null;
        StudentIndeksResponse response = new StudentIndeksResponse();
        response.setId(si.getId());
        response.setBroj(si.getBroj());
        response.setGodina(si.getGodina());
        response.setStudProgramOznaka(si.getStudProgramOznaka());
        response.setNacinFinansiranja(si.getNacinFinansiranja());
        response.setAktivan(si.isAktivan());
        response.setVaziOd(si.getVaziOd());
        response.setOstvarenoEspb(si.getOstvarenoEspb());
        response.setStudent(toStudentPodaciResponse(si.getStudent()));
        response.setStudijskiProgram(toStudijskiProgramResponse(si.getStudijskiProgram()));
        return response;
    }
}
