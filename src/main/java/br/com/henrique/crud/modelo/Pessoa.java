package br.com.henrique.crud.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.henrique.crud.repository.PessoaRepository;

@Entity
@Table(name="pessoa")
public class Pessoa {
	
	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 200)
	@NotNull @NotEmpty
 	private String nome;
	
	@Column(length = 11)
	@NotNull @NotEmpty
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
	
	
	public Pessoa atualiza(Long id, PessoaRepository pessoaRepository) {
		Pessoa pessoa = pessoaRepository.getOne(id);
		
		pessoa.setNome(this.nome);
		pessoa.setCpf(this.cpf);

		return pessoa;	
		
	}
	
	
	public boolean validaCampo(String cpf, String nome) {
		this.cpf = cpf;
		this.nome = nome;
		
		cpf = cpf.trim();
		nome = nome.trim();
		
		if(cpf.length() == 11 && cpf.matches("[0-9]*") && nome.matches("[a-zA-Z]*")) {
			return true;
		}
		return false;
	}	

}
