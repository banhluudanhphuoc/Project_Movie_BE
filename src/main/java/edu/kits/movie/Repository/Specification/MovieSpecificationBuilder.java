package edu.kits.movie.Repository.Specification;

import edu.kits.movie.Common.SearchOperation;
import edu.kits.movie.Common.SpecSearchCriteria;
import edu.kits.movie.Entity.Movie;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class MovieSpecificationBuilder {
    private final List<SpecSearchCriteria> params;

    public MovieSpecificationBuilder() {
        params = new ArrayList<>();
    }

    public final MovieSpecificationBuilder with(String key, String operation, Object value,
                                                String prefix, String suffix) {
        return with(null, key, operation, value, prefix, suffix);
    }

    public final MovieSpecificationBuilder with(String orPredicate, String key, String operation, Object value,
                                                String prefix, String suffix) {
        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            if (op == SearchOperation.EQUALITY) { // the operation may be complex operation
                boolean startWithAsterisk = prefix != null &&
                        prefix.contains(SearchOperation.ZERO_OR_MORE_REGEX);
                boolean endWithAsterisk = suffix != null &&
                        suffix.contains(SearchOperation.ZERO_OR_MORE_REGEX);

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
            }
            params.add(new SpecSearchCriteria(orPredicate, key, op, value));
        }
        return this;
    }

    public Specification<Movie> build() {
        if (params.size() == 0)
            return null;
        Specification<Movie> result = new MovieSpecification(params.get(0));
        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).isOrPredicate()
                    ? Specification.where(result).or(new MovieSpecification(params.get(i)))
                    : Specification.where(result).and(new MovieSpecification(params.get(i)));
        }
        return result;
    }

//    public final MovieSpecification with(UserSpecification spec) {
//        params.add(spec.getCriteria());
//        return this;
//    }
//
//    public final MovieSpecification with(SpecSearchCriteria criteria) {
//        params.add(criteria);
//        return this;
//    }
}
