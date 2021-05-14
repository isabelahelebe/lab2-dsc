package com.dsclab2.lab2.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.dsclab2.lab2.dtos.ComentarioDTO;
import com.dsclab2.lab2.dtos.DisciplinaDTO;
import com.dsclab2.lab2.dtos.NotaDTO;
import com.dsclab2.lab2.entidades.Comentario;
import com.dsclab2.lab2.entidades.Disciplina;
import com.dsclab2.lab2.entidades.Nota;
import com.dsclab2.lab2.repositorios.ComentarioDAO;
import com.dsclab2.lab2.repositorios.DisciplinaDAO;
import com.dsclab2.lab2.repositorios.NotaDAO;

@Service
public class DisciplinaServico {

	@Autowired
	private DisciplinaDAO disciplinaDao;
	
	@Autowired
	private NotaDAO notaDao;
	
	@Autowired
	private ComentarioDAO comentarioDao;

	public DisciplinaServico(DisciplinaDAO disciplinaDao, NotaDAO notaDao, ComentarioDAO comentarioDao) {
		super();
		this.disciplinaDao = disciplinaDao;
		this.notaDao = notaDao;
		this.comentarioDao = comentarioDao;
	}

	public Disciplina adicionarDisciplina(DisciplinaDTO disciplinaDto) {
		Disciplina disciplina = new Disciplina(disciplinaDto.getNome());
		this.disciplinaDao.save(disciplina);
		return disciplina;
	}
	
	public List<Disciplina> adicionarDisciplina(List<Disciplina> disciplinas) {
		this.disciplinaDao.saveAll(disciplinas);
		return disciplinas;
	}
	
	public List<Disciplina> verDisciplinas(){
		return this.disciplinaDao.findAll();
	}
	
	public Disciplina verDisciplina(Long id) {
		if(this.disciplinaDao.existsById(id)) {
			return this.disciplinaDao.findById(id).get();
		}
		System.out.println("Disciplina com o ID " + id + " foi encontrada!");
		throw new HttpClientErrorException(HttpStatus.NOT_FOUND); 
	}
	
	public Disciplina curtirDisciplina(Long id){
		if(this.disciplinaDao.existsById(id)) {
			Disciplina disciplina = this.disciplinaDao.findById(id).get();
			disciplina.setLikes(disciplina.getLikes() + 1);
			return this.disciplinaDao.save(disciplina);
		}
		System.out.println("Disciplina com o ID " + id + " foi encontrada!");
		throw new HttpClientErrorException(HttpStatus.NOT_FOUND); 
	}
	
	public Disciplina adicionarNota(Long id, NotaDTO notaDto){
		if(this.disciplinaDao.existsById(id)) {
			Disciplina disciplina = this.disciplinaDao.findById(id).get();
			if(disciplina.getNotas().isEmpty()) {
				disciplina.setNota(notaDto.getNota());
				disciplina.addNota(notaDao.save(new Nota(notaDto.getNota(), disciplina)));
				disciplinaDao.save(disciplina);
				return disciplina;
			}
			Nota ultima = disciplina.getNotas().get(disciplina.getNotas().size() - 1);
			double media = (ultima.getNota() + notaDto.getNota()) / 2;
			disciplina.setNota(media);
			disciplina.addNota(notaDao.save(new Nota(notaDto.getNota(), disciplina)));
			disciplinaDao.save(disciplina);
			return disciplina;
		}

		System.out.println("Disciplina com o ID " + id + " não foi encontrada!");
		throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
	}
	
	public Disciplina adicionarComentario(Long id, ComentarioDTO comentarioDto) {
		if(this.disciplinaDao.existsById(id)) {
			Disciplina disciplina = this.disciplinaDao.findById(id).get();
			
			disciplina.addComentario(comentarioDao.save(new Comentario(comentarioDto.getComentario(), disciplina)));
			return this.disciplinaDao.save(disciplina);
		}
		System.out.println("Disciplina com o ID " + id + " foi encontrada!");
		throw new HttpClientErrorException(HttpStatus.NOT_FOUND); 
	}
	
	public List<Disciplina> notaDecrescente(){
		return disciplinaDao.findByOrderByNotaDesc();
	}
	
	public List<Disciplina> likesDecrescente(){
		return disciplinaDao.findByOrderByLikesDesc();
	}
	
}
