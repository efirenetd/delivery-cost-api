package org.efire.net.rule;

import java.util.LinkedHashSet;
import java.util.Set;

public class ItemRuleBuilder {

    private Set<ItemRule> itemRules = new LinkedHashSet<>();

    public static ItemRuleBuilder newBuilder() {
        return new ItemRuleBuilder();
    }

    public ItemRuleBuilder addRule(Condition condition, Compute compute) {
        itemRules.add(ItemRule.create(condition, compute));
        return this;
    }
    public Set<ItemRule> build() {
        return itemRules;
    }

    public static Condition condition(Condition condition) {
        return condition;
    }

    public static Compute compute(Compute compute) {
        return compute;
    }
}
