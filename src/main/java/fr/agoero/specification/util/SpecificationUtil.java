package fr.agoero.specification.util;

import java.util.Optional;
import java.util.function.Predicate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@SuppressWarnings("rawtypes")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SpecificationUtil {

    public static <X> Join getJoin(final Root<X> root, final String attributeName) {
        final Optional<Join<X, ?>> join = root.getJoins()
                                              .stream()
                                              .filter(equalAttributeNamePredicate(attributeName))
                                              .findFirst();
        return join.orElseGet(() -> root.join(attributeName));
    }

    public static <X, Z> Join getJoin(final Join<X, Z> initialJoin, final String attributeName) {
        final Optional<Join<Z, ?>> join = initialJoin.getJoins()
                                                     .stream()
                                                     .filter(equalAttributeNamePredicate(attributeName))
                                                     .findFirst();
        return join.orElseGet(() -> initialJoin.join(attributeName));
    }

    public static <X> Specification<X> initOrAndSpecification(Specification<X> specification,
        Specification<X> specificationToAnd) {
        return specification == null ? specificationToAnd : specification.and(specificationToAnd);
    }

    private static <T> Predicate<Join<T, ?>> equalAttributeNamePredicate(String attributeName) {
        return join -> join.getAttribute()
                           .getName()
                           .equals(attributeName);
    }

}
