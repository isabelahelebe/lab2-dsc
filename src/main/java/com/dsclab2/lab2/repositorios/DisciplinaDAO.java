package com.dsclab2.lab2.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsclab2.lab2.entidades.Disciplina;

@Repository
public interface DisciplinaDAO extends JpaRepository<Disciplina, Long> {
	List<Disciplina> findByOrderByLikesDesc();
	List<Disciplina> findByOrderByNotaDesc();
}
