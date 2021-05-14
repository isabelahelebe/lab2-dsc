package com.dsclab2.lab2.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsclab2.lab2.entidades.Nota;

@Repository
public interface NotaDAO extends JpaRepository<Nota, Long> {
	
}
