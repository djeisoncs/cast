package br.com.cast.avaliacao.repository;

import br.com.cast.avaliacao.model.Curso;
import br.com.cast.avaliacao.util.RegraNegocioList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

/**
 * Created by djeison.cassimiro on 26/12/2020
 */
@Repository
public interface CursoRepository extends JpaRepository<Curso, UUID> {

    Curso findByDataInicioBetween(Date dataInicio, Date dataFinal);
//    @Override
//    protected RegraNegocioList getRegrasBeforeSalvarAlterar(Curso entidade) {
//        RegraNegocioList regraNegocioList = RegraNegocioList.build();
//
//        addRegraCursoNaoPermitidoNoMesmoPeriodo(regraNegocioList, entidade);
//
//        return regraNegocioList;
//    }
//
//    private void addRegraCursoNaoPermitidoNoMesmoPeriodo(RegraNegocioList regraNegocioList, Curso entidade) {
//        regraNegocioList.add("curso.regraNegocio.inclusaoDentroDoMesmoPeriodo",  (root, query, criteriaBuilder) -> criteriaBuilder.and(
//                criteriaBuilder.between(root.get("dataInicio"), entidade.getDataInicio(), entidade.getDataTermino()),
//                criteriaBuilder.between(root.get("dataTermino"), entidade.getDataInicio(), entidade.getDataTermino())
//        ));
//    }
}
