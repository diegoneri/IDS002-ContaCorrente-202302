package com.fatecbs.ContaCorrente.controller;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatecbs.ContaCorrente.model.Conta;
import com.fatecbs.ContaCorrente.service.ContaService;


@RestController
@RequestMapping("/contas")
public class ContaController {
	@Autowired
    private ContaService service;

    @GetMapping
    public ResponseEntity<List<Conta>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Conta> get(@PathVariable("id") Long id) {
        Conta _conta = service.find(id);
        if (_conta != null)
            return ResponseEntity.ok(_conta);
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Conta> post(@RequestBody Conta conta) {
        service.create(conta);
        URI location=ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(conta.getId()).toUri();
        return ResponseEntity.created(location).body(conta);
    }
    @PutMapping
    public ResponseEntity<Conta> put(@RequestBody Conta conta) {
        if (service.update(conta)) {
            return ResponseEntity.ok(conta);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (service.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
