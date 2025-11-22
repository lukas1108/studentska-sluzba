package org.raflab.studsluzba.controllers.impl;

import lombok.RequiredArgsConstructor;
import org.raflab.studsluzba.controllers.request.IspitRequest;
import org.raflab.studsluzba.controllers.response.IspitResponse;
import org.raflab.studsluzba.model.entities.Ispit;
import org.raflab.studsluzba.services.IspitService;
import org.raflab.studsluzba.utils.Converters;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ispit")
public class IspitController {

    private final IspitService service;

    @PostMapping("/add")
    public Long add(@RequestBody IspitRequest req) {
        return service.add(req);
    }

    @GetMapping("/{id}")
    public IspitResponse get(@PathVariable Long id) {
        Optional<Ispit> i = service.findById(id);
        return i.map(Converters::toIspitResponse).orElse(null);
    }

    @GetMapping("/all")
    public List<IspitResponse> all() {
        return Converters.toIspitResponseList(service.findAll());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
