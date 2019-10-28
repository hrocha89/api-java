package br.com.henrique.crud.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.henrique.crud.controller.dto.DetalhePessoa;
import br.com.henrique.crud.controller.dto.PessoaDto;
import br.com.henrique.crud.modelo.Pessoa;
import br.com.henrique.crud.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@GetMapping
	public List<Pessoa>  lista(){
		List<Pessoa> pessoas = pessoaRepository.findAll();
		return pessoas;
	}
	
	
	@PostMapping
	public ResponseEntity<Pessoa> cadastra(@RequestBody @Valid Pessoa pessoa, UriComponentsBuilder uriBuilder) {
		boolean p = pessoa.validaCampo(pessoa.getCpf(), pessoa.getNome());
		
		if(p == true) {
			pessoaRepository.save(pessoa);
			URI uri = uriBuilder.path("/pessoas/{id}").buildAndExpand(pessoa.getId()).toUri();
			return ResponseEntity.created(uri).body(pessoa);
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalhePessoa> unico(@PathVariable Long id) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		if (pessoa.isPresent()) {
			return ResponseEntity.ok(new DetalhePessoa(pessoa.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("{id}")
	@Transactional
	public ResponseEntity<PessoaDto> atualiza(@PathVariable Long id, @RequestBody @Valid Pessoa p){				
		if(p.validaCampo(p.getCpf(), p.getNome()) == true) {
			Optional<Pessoa> optional = pessoaRepository.findById(id);
			Pessoa pessoa = p.atualiza(id, pessoaRepository);
			if(optional.isPresent()) {
				p.atualiza(id, pessoaRepository);
				return ResponseEntity.ok(new PessoaDto(pessoa));						
			}
		}		
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		if (pessoa.isPresent()) {
			pessoaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	
	
	

}
