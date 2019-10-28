package br.com.henrique.crud.controller.dto;

import br.com.henrique.crud.modelo.Pessoa;

public class DetalhePessoa {
	
	private Long id;
 	private String nome;
	private String cpf;
	
	
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
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}	
	
	
	public DetalhePessoa(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.cpf = pessoa.getCpf();
	}
	
	
	

}
