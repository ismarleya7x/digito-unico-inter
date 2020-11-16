package br.com.ismarleycarvalho.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 2048)
	private String nome;
	
	@Column(length = 9999)
	private String email;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<DigitoUnico> digito = new ArrayList<>();
	
	public Usuario() {}

	public Usuario(Long id, String nome, String email) {
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	

	public Usuario(Long id, String nome, String email, List<DigitoUnico> digito) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.digito = digito;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<DigitoUnico> getDigito() {
		return digito;
	}

	public void setDigito(List<DigitoUnico> digito) {
		this.digito = digito;
	}

	
	
}
