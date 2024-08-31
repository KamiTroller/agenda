package com.sistema.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.agenda.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long>{

}
