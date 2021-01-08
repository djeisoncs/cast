package br.com.cast.avaliacao.test.service;

import br.com.cast.avaliacao.i18n.MensagemI18N;
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
        categoriaService.excluir(categoria.getId());
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
            service.excluir(curso.getId());
        }
    }

    @Test(description = "Teste responsável tentar salvar um curso para uma data planejada em outro curso cadastrado anteriormente")
    public void salvar_com_periodo__planejado_em_outro_curso() throws NegocioException {
        Curso curso = getEntidade();

        service.salvar(curso);

        try {
            Curso cursoComPeriodoInvalido = getEntidade();
            cursoComPeriodoInvalido.setDescricao("Curso Periodo inválido");
            try {
                service.salvar(cursoComPeriodoInvalido);
                service.excluir(cursoComPeriodoInvalido.getId());
                Assert.fail("Não deveria ser possível salvar dois cursos dentro do mesmo periodo");
            } catch (NegocioException e) {
                Assert.assertEquals(e.getMensagensSeparadosPorLinhas().replace("\n",""), MensagemI18N.getKey("curso.regraNegocio.inclusaoDentroDoMesmoPeriodo"));
            }

        } finally {
            service.excluir(curso.getId());
        }
    }

    @Test(description = "Teste responsável por editar um curso")
    public void alterar() throws NegocioException {
        Curso curso = getEntidade();

        service.salvar(curso);

        String descricao = "Curso com descrição alterada";

        try {
            curso.setDescricao(descricao);

            service.editar(curso);
            Curso consultado = service.get(curso.getId());

            Assert.assertNotNull(consultado);
            Assert.assertTrue(consultado.isAtivo());
            Assert.assertEquals(consultado.getDescricao(), descricao);
        } finally {
            service.excluir(curso.getId());
        }
    }

    @Test(description = "Teste responsável por tentar editar um curso para uma data planejada em outro curso cadastrado anteriormente")
    public void alterar_curso_para_periodo_planejado_em_outro_curso() throws NegocioException {
        Curso curso = getEntidade();
        service.salvar(curso);

        Curso segundoCurso = getEntidade();
        segundoCurso.setDescricao("Descrição segundo curso");
        segundoCurso.setDataInicio(DateUtil.somarDiasAData(segundoCurso.getDataTermino(), 1));
        segundoCurso.setDataTermino(DateUtil.somarDiasAData(segundoCurso.getDataTermino(), 2));
        service.salvar(segundoCurso);

        try {
            segundoCurso.setDataInicio(curso.getDataInicio());

            try {
                service.editar(segundoCurso);
                Assert.fail("Não deveria ser possível salvar dois cursos dentro do mesmo periodo");
            } catch (NegocioException e) {
                Assert.assertEquals(e.getMensagensSeparadosPorLinhas().replace("\n",""), MensagemI18N.getKey("curso.regraNegocio.inclusaoDentroDoMesmoPeriodo"));
            }

        } finally {
            service.excluir(curso.getId());
            service.excluir(segundoCurso.getId());
        }


    }

    @Test(description = "Teste responsável por excluir uma categoria")
    public void excluir() throws NegocioException {
        Curso curso = getEntidade();

        service.salvar(curso);
        service.excluir(curso.getId());

        Assert.assertNull(service.get(curso.getId()));
    }

    private Curso getEntidade() {
        Curso curso = new Curso();

        curso.setCategoria(categoria);
        curso.setDataInicio(DateUtil.zerarHoraData(new Date(System.currentTimeMillis())));
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
