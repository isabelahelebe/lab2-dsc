package com.dsclab2.lab2.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.dsclab2.lab2.dtos.ComentarioDTO;
import com.dsclab2.lab2.dtos.DisciplinaDTO;
import com.dsclab2.lab2.dtos.NotaDTO;
import com.dsclab2.lab2.entidades.Disciplina;
import com.dsclab2.lab2.servicos.DisciplinaServico;

@RestController
public class DisciplinaControlador {

	@Autowired
	private DisciplinaServico service;

	public DisciplinaControlador(DisciplinaServico service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/disciplinas/adicionar")
	public ResponseEntity<Disciplina> adicionarDisciplina(@RequestBody DisciplinaDTO disciplinaDto){
		return new ResponseEntity<Disciplina>(service.adicionarDisciplina(disciplinaDto), HttpStatus.CREATED);
	}
	
	@PostMapping("/disciplinas/adicionartodas")
	public ResponseEntity<List<Disciplina>> adicionarDisciplina(@RequestBody List<Disciplina> disciplinas){
		return new ResponseEntity<List<Disciplina>>(service.adicionarDisciplina(disciplinas), HttpStatus.CREATED);
	}
	
	@GetMapping("/disciplinas")
	public ResponseEntity<List<Disciplina>> verDisciplinas(){
		return new ResponseEntity<List<Disciplina>>(service.verDisciplinas(), HttpStatus.OK);
	}
	
	@GetMapping("/disciplinas/{id}")
	public ResponseEntity<Disciplina> verDisciplina(@PathVariable Long id){
		try {
			return new ResponseEntity<Disciplina>(service.verDisciplina(id), HttpStatus.OK);
		}catch(HttpClientErrorException e) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND); 
		}
	}
	
	@GetMapping("/disciplinas/likes/{id}")
	public ResponseEntity<Disciplina> curtirDisciplina(@PathVariable Long id){
		try {
			return new ResponseEntity<Disciplina>(service.curtirDisciplina(id), HttpStatus.OK);
		}catch(HttpClientErrorException e) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND); 
		}
	}
	
	@PostMapping("/disciplinas/nota/{id}")
	public ResponseEntity<Disciplina> adicionarNota(@PathVariable Long id, @RequestBody NotaDTO notaDto){
		try {
			return new ResponseEntity<Disciplina>(service.adicionarNota(id, notaDto), HttpStatus.OK);
		}catch(HttpClientErrorException e) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND); 
		}
	}
	
	@PostMapping("/disciplinas/comentario/{id}")
	public ResponseEntity<Disciplina> adicionarComentario(@PathVariable Long id, @RequestBody ComentarioDTO comentarioDto){
		try {
			return new ResponseEntity<Disciplina>(service.adicionarComentario(id, comentarioDto), HttpStatus.OK);
		}catch(HttpClientErrorException e) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND); 
		}
	}
	
	@GetMapping("/disciplinas/ranking/notas")
	public ResponseEntity<List<Disciplina>> notaDecrescente(){
		return new ResponseEntity<List<Disciplina>>(service.notaDecrescente(), HttpStatus.OK);
	}
	
	@GetMapping("/disciplinas/ranking/likes")
	public ResponseEntity<List<Disciplina>> likesDecrescente(){
		return new ResponseEntity<List<Disciplina>>(service.likesDecrescente(), HttpStatus.OK);
	}
	
}
