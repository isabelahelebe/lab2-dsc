package com.dsclab2.lab2.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "comentarios")
@Entity
public class Comentario {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String comentario;
	
	private int likes;
	
	@ManyToOne
	@JoinColumn(name = "disciplina_id")
	private Disciplina disciplina;

	public Comentario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comentario(String comentario) {
		super();
		this.comentario = comentario;
	}

	public Comentario(String comentario, Disciplina disciplina) {
		super();
		this.comentario = comentario;
		this.disciplina = disciplina;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getlikes() {
		return likes;
	}

	public void setlikes(int likes) {
		this.likes = likes;
	}

	public Long getId() {
		return id;
	}
}
