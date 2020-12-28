package br.com.cast.avaliacao.model;

import br.com.cast.avaliacao.util.Constantes;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by djeison.cassimiro on 26/12/2020
 */
@Getter
@Setter
@Entity
@Table(schema = Constantes.SCHEMA_CAST, name = "tb_categoria")
public class Categoria extends Entidade implements Serializable {

    @Column
    private Integer codigo;

    @Column
    private String descricao;

    @OneToMany(mappedBy = "categoria")
    private List<Curso> cursos = new ArrayList<>();
}
