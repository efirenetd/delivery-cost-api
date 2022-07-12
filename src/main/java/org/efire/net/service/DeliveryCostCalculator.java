package org.efire.net.service;

import org.efire.net.model.Item;
import org.efire.net.rule.ItemRule;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class DeliveryCostCalculator {

    private Set<ItemRule> itemRules;

    public DeliveryCostCalculator(Set<ItemRule> itemRules) {
        this.itemRules = itemRules;
    }

    public Item calculateCost(Item item) {
        itemRules.forEach(itemRule -> {
            if (itemRule.test(item)) {
                itemRule.execute(item);
            }
        });
        return item;
    }
}
