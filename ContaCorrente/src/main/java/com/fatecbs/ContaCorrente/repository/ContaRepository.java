package com.fatecbs.ContaCorrente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatecbs.ContaCorrente.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

}
