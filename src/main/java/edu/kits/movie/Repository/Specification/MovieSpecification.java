package edu.kits.movie.Repository.Specification;

import edu.kits.movie.Common.SpecSearchCriteria;
import edu.kits.movie.Entity.Movie;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;

public class MovieSpecification implements Specification<Movie> {
    private SpecSearchCriteria criteria;

    public MovieSpecification(SpecSearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return switch (criteria.getOperation()) {
            case EQUALITY -> builder.equal(root.get(criteria.getKey()), criteria.getValue());
            case NEGATION -> builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
            case GREATER_THAN -> builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
            case LESS_THAN -> builder.lessThan(root.get(criteria.getKey()), (Comparable) criteria.getValue());
            case LIKE -> builder.like(root.get(criteria.getKey()), criteria.getValue().toString());
            case STARTS_WITH -> builder.like(root.get(criteria.getKey()), criteria.getValue() + "%");
            case ENDS_WITH -> builder.like(root.get(criteria.getKey()), "%" + criteria.getValue());
            case CONTAINS -> builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            case GREATER_THAN_OR_EQUAL_TO ->
                    builder.greaterThanOrEqualTo(root.get(criteria.getKey()),(Comparable) criteria.getValue());
            case LESS_THAN_OR_EQUAL_TO ->
                    builder.lessThanOrEqualTo(root.get(criteria.getKey()), (Comparable) criteria.getValue());
            default -> null;
        };
    }
}
