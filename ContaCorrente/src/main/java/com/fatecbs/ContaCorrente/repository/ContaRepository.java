package com.fatecbs.ContaCorrente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatecbs.ContaCorrente.model.Conta;

public interface ContaRepository 
			extends JpaRepository<Conta,Long>{

}
