package br.com.cast.avaliacao.repository;

import br.com.cast.avaliacao.model.Curso;
import br.com.cast.avaliacao.util.RegraNegocioList;
import org.springframework.stereotype.Repository;

/**
 * Created by djeison.cassimiro on 26/12/2020
 */
@Repository
public class CursoRepository extends RepositoryImpl<Curso> {

    @Override
    protected RegraNegocioList getRegrasBeforeSalvarAlterar(Curso entidade) {
        RegraNegocioList regraNegocioList = RegraNegocioList.build();

        addRegraCursoNaoPermitidoNoMesmoPeriodo(regraNegocioList, entidade);

        return regraNegocioList;
    }

    private void addRegraCursoNaoPermitidoNoMesmoPeriodo(RegraNegocioList regraNegocioList, Curso entidade) {
        regraNegocioList.add("curso.regraNegocio.inclusaoDentroDoMesmoPeriodo",  (root, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.between(root.get("dataInicio"), entidade.getDataInicio(), entidade.getDataTermino()),
                criteriaBuilder.between(root.get("dataTermino"), entidade.getDataInicio(), entidade.getDataTermino())
        ));
    }
}
