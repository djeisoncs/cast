package br.com.cast.avaliacao.service;

import br.com.cast.avaliacao.i18n.MensagemI18N;
import br.com.cast.avaliacao.model.Curso;
import br.com.cast.avaliacao.repository.CursoRepository;
import br.com.cast.avaliacao.util.DateUtil;
import br.com.cast.avaliacao.util.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by djeison.cassimiro on 27/12/2020
 */
@Service
public class CursoService extends ServiceImpl<Curso> {

    @Autowired
    private CursoRepository repository;

    @Override
    protected JpaRepository<Curso, UUID> getRepository() { return repository; }

    @Override
    protected NegocioException getRegrasBeforeSalvarAlterar(Curso entidade) {
        NegocioException negocioException = NegocioException.build();

        if (Objects.isNull(entidade.getDataInicio()) || entidade.getDataInicio().before(DateUtil.zerarHoraData(new Date(System.currentTimeMillis())))) {
            negocioException.addMensagem(MensagemI18N.getKey("curso.regraNegocio.dataInicioMenorQueHoje"));
        }

        if (Objects.nonNull(repository.findByDataInicioBetween(entidade.getDataInicio(), entidade.getDataTermino()))) {
            negocioException.addMensagem(MensagemI18N.getKey("curso.regraNegocio.inclusaoDentroDoMesmoPeriodo"));
        }

        return negocioException;
    }
}
