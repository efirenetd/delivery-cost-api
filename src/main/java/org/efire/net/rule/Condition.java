package org.efire.net.rule;

import org.efire.net.model.Item;

import java.util.Objects;
import java.util.function.Predicate;

@FunctionalInterface
public interface Condition extends Predicate<Item> {
    default Condition and(Condition other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) && other.test(t);
    }
    default Condition or(Condition other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) || other.test(t);
    }

    default Condition negate() {
        return (t) -> !test(t);
    }
}
