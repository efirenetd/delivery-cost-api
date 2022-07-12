package org.efire.net.rule;

import org.efire.net.model.Item;

import java.util.function.Function;
import java.util.function.Predicate;

public abstract class ItemRule implements Predicate<Item>, Function<Item, Item> {

    private Predicate<Item> itemRulePredicate;

    public ItemRule() {
        this.itemRulePredicate = this::matches;
    }

    abstract boolean matches(Item item);
    public abstract Item execute(Item item);

    @Override
    public boolean test(Item item) {
        return this.itemRulePredicate.test(item);
    }

    @Override
    public Item apply(Item item) {
        return this.execute(item);
    }

    public static ItemRule create(Condition condition, Compute compute) {
        return new ItemRule() {
            @Override
            boolean matches(Item item) {
                return condition.test(item);
            }

            @Override
            public Item execute(Item item) {
                compute.accept(item);
                return item;
            }
        };
    }
}
