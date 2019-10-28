package br.com.henrique.crud;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.henrique.crud.controller.PessoaController;
import br.com.henrique.crud.modelo.Pessoa;
import br.com.henrique.crud.repository.PessoaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCrud {
	
	@Autowired
    private PessoaController pessoa;
	
	
	@MockBean
	private PessoaRepository pessoaRepository;
	

    @Test
    public void contexLoads() throws Exception {
        assertThat(pessoa).isNotNull();
    }
    
    @Test
    public void validaOsCampos() throws Exception{
    	Pessoa pessoa = new Pessoa();
    	assertEquals(false, pessoa.validaCampo("12312131311", "Henr1ique"));
    }

}
