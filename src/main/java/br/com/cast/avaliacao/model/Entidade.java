package br.com.cast.avaliacao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.lang.Nullable;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by djeison.cassimiro on 27/12/2020
 */
@MappedSuperclass
@Getter
@Setter
public class Entidade extends AbstractPersistable<UUID> implements Serializable {

    private Status status;
    private UUID id;

    public void ativar() { setStatus(Status.ATIVO); }

    public void desativar() { setStatus(Status.INATIVO); }

    @JsonIgnore
    public boolean isAtivo() { return Status.ATIVO.equals(getStatus()); }

    public void setId(UUID id) { this.id = id; }
}
