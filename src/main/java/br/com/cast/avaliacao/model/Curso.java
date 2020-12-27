package br.com.cast.avaliacao.model;

import br.com.cast.avaliacao.anotacoes.EntityProperties;
import br.com.cast.avaliacao.util.Constantes;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by djeison.cassimiro on 26/12/2020
 */
@Getter
@Setter
@Entity
@Table(schema = Constantes.SCHEMA_CAST, name = "tb_curso")
public class Curso extends AbstractPersistable<UUID> implements Serializable {

    @Column
    private String descricao;

    @Column(name = "data_inicio")
    private Date dataInicio;

    @Column(name = "data_termino")
    private Date dataTermino;

    @Column(name = "qtd_alunos_por_turma")
    private Integer qtdAlunosTurma;

    @EntityProperties(value = {"id", "descricao"})
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
}
