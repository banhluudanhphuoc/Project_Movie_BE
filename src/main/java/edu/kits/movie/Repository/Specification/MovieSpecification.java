package edu.kits.movie.Repository.Specification;

import edu.kits.movie.Common.SpecSearchCriteria;
import edu.kits.movie.Entity.Genre;
import edu.kits.movie.Entity.Movie;
import edu.kits.movie.Entity.MovieGenre;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;

public class MovieSpecification implements Specification<Movie> {
    private SpecSearchCriteria criteria;

    public MovieSpecification(SpecSearchCriteria criteria) {
        this.criteria = criteria;
    }

    public static Predicate hasGenre(String idGenre, Root<Movie> root,
                                     CriteriaBuilder criteriaBuilder) {
        Join<Movie, MovieGenre> movieGenreJoin = root.join("genres");
//        query.select(movieGenreJoin.get("genres").get("id"));
        return criteriaBuilder.equal(movieGenreJoin.get("id"),Integer.parseInt(idGenre));
    }

    @Override
    public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        System.out.println(criteria.getValue());
        return switch (criteria.getOperation()) {
            case EQUALITY -> criteria.getKey().equalsIgnoreCase("genre") ?
                    hasGenre(criteria.getValue().toString(), root, builder) :
                    builder.equal(root.get(criteria.getKey()), criteria.getValue());
            case NEGATION -> builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
            case GREATER_THAN -> builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
            case LESS_THAN -> builder.lessThan(root.get(criteria.getKey()), (Comparable) criteria.getValue());
            case LIKE -> builder.like(root.get(criteria.getKey()), criteria.getValue().toString());
            case STARTS_WITH -> builder.like(root.get(criteria.getKey()), criteria.getValue() + "%");
            case ENDS_WITH -> builder.like(root.get(criteria.getKey()), "%" + criteria.getValue());
            case CONTAINS -> builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            case GREATER_THAN_OR_EQUAL_TO ->
                    builder.greaterThanOrEqualTo(root.get(criteria.getKey()), (Comparable) criteria.getValue());
            case LESS_THAN_OR_EQUAL_TO ->
                    builder.lessThanOrEqualTo(root.get(criteria.getKey()), (Comparable) criteria.getValue());
            default -> null;
        };
    }
}
