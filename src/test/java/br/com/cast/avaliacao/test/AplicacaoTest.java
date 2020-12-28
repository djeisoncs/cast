package br.com.cast.avaliacao.test;

import br.com.cast.avaliacao.Aplicacao;
import br.com.cast.avaliacao.util.NegocioException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * Created by djeison.cassimiro on 27/12/2020
 */
@SpringBootTest(classes = Aplicacao.class)
@TestPropertySource(properties = {"spring.profiles.active=teste"})
public abstract class AplicacaoTest extends AbstractTestNGSpringContextTests {

    @BeforeClass
    public void beforeClass() throws NegocioException { onBeforeClass(); }

    @AfterClass
    public void afterClass() throws NegocioException { onAfterClass(); }

    public void onBeforeClass() throws NegocioException {}

    public void onAfterClass() throws NegocioException {}
}
