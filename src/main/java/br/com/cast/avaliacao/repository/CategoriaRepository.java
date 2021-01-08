package br.com.cast.avaliacao.repository;

import br.com.cast.avaliacao.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by djeison.cassimiro on 26/12/2020
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {
}
