package org.raflab.studsluzba.controllers.impl;

import java.util.List;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.response.PredmetResponse;
import org.raflab.studsluzba.model.entities.StudijskiProgram;
import org.raflab.studsluzba.services.PredmetService;
import org.raflab.studsluzba.services.StudijskiProgramiService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping(path="/api/studprogram")
public class StudijskiProgramiController {

    final StudijskiProgramiService studijskiProgramiService;
	private final PredmetService predmetService;
	
	@GetMapping(path = "/all/sorted")
	public List<StudijskiProgram> getAllStudProgramiSortedDesc(){
		return studijskiProgramiService.getAllStudProgramiSortedDesc();
	}

	@GetMapping(path="/oznaka/all")
	public Iterable<String> getAllStudProgramOznaka() {
		return studijskiProgramiService.getAllStudProgramOznaka();
	}

	@GetMapping("/{id}/predmeti")
	public List<PredmetResponse> getPredmetiZaProgram(@PathVariable Long id) {
		return predmetService.getPredmetiNaStudijskomProgramu(id);
	}
}
