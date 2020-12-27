package br.com.cast.avaliacao.util;

import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Objects;

/**
 * Created by djeison.cassimiro on 26/12/2020
 */
@Getter
public class RegraNegocio {

    private String key;
    private Specification<?> specification;

    private RegraNegocio(String key, Specification<?> specification){
        this.key = key;
        this.specification = specification;
    }

    public static RegraNegocio build(String key){
        return build(key, null);
    }

    public static RegraNegocio build(String key, Specification<?> specification){
        return new RegraNegocio(key, specification);
    }

    public boolean hasSpecification(){
        return !Objects.isNull(specification);
    }

    @SuppressWarnings("unchecked")
    public Predicate getPredicate(CriteriaBuilder criteriaBuilder, CriteriaQuery criteriaQuery, Root root){
        return specification.toPredicate(root, criteriaQuery, criteriaBuilder);
    }

    public interface RegraNegocioPredicateFactory{
        Predicate createPredicate(CriteriaBuilder criteriaBuilder, CriteriaQuery criteriaQuery, Root root);
    }
}
