package br.com.henrique.crud;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.henrique.crud.controller.PessoaController;

@RunWith(SpringRunner.class)
@SpringBootTest
class CrudApplicationTests {

	@Autowired
    private PessoaController pessoa;

    @Test
    public void contexLoads() throws Exception {
        assertThat(pessoa).isNotNull();
    }

}
