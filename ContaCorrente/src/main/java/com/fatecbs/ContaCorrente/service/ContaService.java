package com.fatecbs.ContaCorrente.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatecbs.ContaCorrente.model.Conta;
import com.fatecbs.ContaCorrente.repository.ContaRepository;

@Service
public class ContaService implements 
                         ServiceInterface<Conta> {
   @Autowired
   private ContaRepository repository;

   public ContaService() {}

   @Override
   public Conta create(Conta obj) {
      repository.save(obj);
      return obj;
   }

   @Override
   public Conta findById(Long id) {
      Optional<Conta> obj = repository.findById(id);
      return obj.orElse(null);
   }
   
   @Override
   public List<Conta> findAll() { 
      return repository.findAll(); 
   }

   @Override
   public boolean update(Conta obj) {
      if (repository.existsById(obj.getId())) {
         repository.save(obj);
         return true;
      }
      return false;
   }

   @Override
   public boolean delete(Long id) {
      if (repository.existsById(id)) {
         repository.deleteById(id);
         return true;
      }
      return false;
   }
}