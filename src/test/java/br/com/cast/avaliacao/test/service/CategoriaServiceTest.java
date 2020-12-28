package br.com.cast.avaliacao.test.service;

import br.com.cast.avaliacao.model.Categoria;
import br.com.cast.avaliacao.service.CategoriaService;
import br.com.cast.avaliacao.test.AplicacaoTest;
import br.com.cast.avaliacao.util.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by djeison.cassimiro on 27/12/2020
 */
public class CategoriaServiceTest extends AplicacaoTest {

    @Autowired
    private CategoriaService service;

    @Test(description = "Teste reponsável por salvar uma categoria")
    public void salvar() throws NegocioException {
        Categoria categoria = getEntidade();

        service.salvar(categoria);

        try {
            Categoria consultado = service.get(categoria.getId());

            Assert.assertNotNull(consultado);
            Assert.assertTrue(consultado.isAtivo());
        } finally {
            service.excluirDefinitivamente(categoria.getId());
        }
    }

    @Test(description = "Teste responsável por editar uma categoria")
    public void alterar() throws NegocioException {
        Categoria categoria = getEntidade();

        service.salvar(categoria);

        categoria.setDescricao("Descrição alterada");

        service.alterarAtributos(categoria, "descricao");

        try {
            Categoria consultado = service.get(categoria.getId());

            Assert.assertNotNull(consultado);
            Assert.assertTrue(consultado.isAtivo());
            Assert.assertEquals(consultado.getDescricao(), "Descrição alterada");
        } finally {
            service.excluirDefinitivamente(categoria.getId());
        }
    }

    @Test(description = "Teste responsável por excluir uma categoria")
    public void excluir() throws NegocioException {
        Categoria categoria = getEntidade();

        service.salvar(categoria);
        service.excluir(categoria.getId());

        try {
            Assert.assertNull(service.get(categoria.getId()));
        } finally {
            service.excluirDefinitivamente(categoria.getId());
        }
    }

    private Categoria getEntidade() {
        Categoria categoria = new Categoria();

        categoria.setCodigo(1);
        categoria.setDescricao("Descrição - CategoriaServiceTest");

        return categoria;
    }
}
