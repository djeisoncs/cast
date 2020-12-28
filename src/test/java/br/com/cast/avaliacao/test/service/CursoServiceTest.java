package br.com.cast.avaliacao.test.service;

import br.com.cast.avaliacao.model.Categoria;
import br.com.cast.avaliacao.model.Curso;
import br.com.cast.avaliacao.service.CategoriaService;
import br.com.cast.avaliacao.service.CursoService;
import br.com.cast.avaliacao.test.AplicacaoTest;
import br.com.cast.avaliacao.util.DateUtil;
import br.com.cast.avaliacao.util.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;

/**
 * Created by djeison.cassimiro on 27/12/2020
 */
public class CursoServiceTest extends AplicacaoTest {

    @Autowired
    private CursoService service;

    @Autowired
    private CategoriaService categoriaService;

    private Categoria categoria;

    @Override
    public void onBeforeClass() throws NegocioException {
        super.onBeforeClass();
        salvarCategoria();
    }

    @Override
    public void onAfterClass() throws NegocioException {
        super.onAfterClass();
        categoriaService.excluirDefinitivamente(categoria.getId());
    }

    @Test(description = "Teste resposável por salvar um curso")
    public void salvar() throws NegocioException {
        Curso curso = getEntidade();

        service.salvar(curso);

        try {
            Curso consultado = service.get(curso.getId());

            Assert.assertNotNull(consultado);
            Assert.assertTrue(consultado.isAtivo());
        } finally {
            service.excluirDefinitivamente(curso.getId());
        }
    }

    private Curso getEntidade() {
        Curso curso = new Curso();

        curso.setCategoria(categoria);
        curso.setDataInicio(new Date(System.currentTimeMillis()));
        curso.setDataTermino(DateUtil.somarDiasAData(curso.getDataInicio(), 1));
        curso.setDescricao("Descrição - CursoServiceTest");

        return curso;
    }

    private void salvarCategoria() throws NegocioException {
        criarCategoria();
        categoriaService.salvar(categoria);
    }

    private void criarCategoria() {
        categoria = new Categoria();

        categoria.setCodigo(1);
        categoria.setDescricao("Descrição - CategoriaServiceTest");
    }
}
