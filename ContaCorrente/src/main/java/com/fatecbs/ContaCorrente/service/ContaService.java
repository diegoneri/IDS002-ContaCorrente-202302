package com.fatecbs.ContaCorrente.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatecbs.ContaCorrente.model.Conta;
import com.fatecbs.ContaCorrente.repository.ContaRepository;

@Service
public class ContaService {
	@Autowired
	private ContaRepository repository;

	public ContaService() {
	}

	public void create(Conta conta) {
		repository.save(conta);
	}

	public List<Conta> findAll() {
		return repository.findAll();
	}

	public Conta find(Conta conta) {
		Optional<Conta> _conta = 
				repository.findById(conta.getId());
		return _conta.orElse(null);
	}

	public Conta find(Long id) {
		Optional<Conta> _conta = 
				repository.findById(id);
		return _conta.orElse(null);		
	}

	public boolean update(Conta conta) {
		boolean existe = repository.existsById(conta.getId());
		
		if (existe) {
			repository.save(conta);
		}
		
		return existe;
	}

	public boolean delete(Long id) {
		boolean existe = repository.existsById(id);
		
		if (existe) {
			repository.deleteById(id);
		}
		
		return existe;
	}
}
