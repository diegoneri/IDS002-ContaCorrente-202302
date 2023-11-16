package com.fatecbs.ContaCorrente.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatecbs.ContaCorrente.model.Conta;
import com.fatecbs.ContaCorrente.service.ContaService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/contas")
public class ContaController {
	@Autowired
    private ContaService service;

    @GetMapping(produces = "application/json")
    @ApiResponses(value = {
    	    @ApiResponse(responseCode = "200", 
    	        description = "Retorna a lista de categorias"),
    	    @ApiResponse(responseCode = "500", 
    	        description = "Erro interno do sistema"),
    	})    
    public ResponseEntity<List<Conta>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ApiResponses(value = {
    	    @ApiResponse(responseCode = "200", 
    	        description = "Categoria pelo id"),
    	    @ApiResponse(responseCode = "500", 
    	        description = "Erro interno do sistema"),
    	})    
    public ResponseEntity<Conta> get(@PathVariable("id") Long id) {
        Conta _conta = service.find(id);
        if (_conta != null)
            return ResponseEntity.ok(_conta);
        return ResponseEntity.notFound().build();
    }

    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
    	    @ApiResponse(responseCode = "201", 
    	        description = "Conta criada"),
    	    @ApiResponse(responseCode = "500", 
    	        description = "Erro interno do sistema"),
    	})        
    public ResponseEntity<Conta> post(@RequestBody Conta conta) {
        service.create(conta);
        URI location=ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(conta.getId()).toUri();
        return ResponseEntity.created(location).body(conta);
    }
    
    @PutMapping(produces = "application/json")
    @ApiResponses(value = {
    	    @ApiResponse(responseCode = "200", 
    	        description = "Conta atualizada"),
    	    @ApiResponse(responseCode = "500", 
    	        description = "Erro interno do sistema"),
    	})      
    public ResponseEntity<Conta> put(@RequestBody Conta conta) {
        if (service.update(conta)) {
            return ResponseEntity.ok(conta);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    @ApiResponses(value = {
    	    @ApiResponse(responseCode = "200", 
    	        description = "Conta excluída"),
    	    @ApiResponse(responseCode = "500", 
    	        description = "Erro interno do sistema"),
    	})       
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (service.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
