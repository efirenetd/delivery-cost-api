package org.efire.net.config;

import org.efire.net.rule.Condition;
import org.efire.net.rule.ItemRule;
import org.efire.net.rule.ItemRuleBuilder;
import org.efire.net.rule.util.ItemCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

import static org.efire.net.rule.ItemRuleBuilder.compute;
import static org.efire.net.rule.ItemRuleBuilder.condition;

@Configuration
public class RuleConfig {

    public static final Condition IS_REJECTED = ItemCondition.isWeightExceedBy(80);
    public static final Condition IS_HEAVY_ITEM = ItemCondition.isWeightInBetween(15, 81);
    public static final Condition IS_SMALL_ITEM = ItemCondition.isVolumeLessThan(2000)
            .and(ItemCondition.isWeightLessThanEqual(15));
    public static final Condition IS_MEDIUM_ITEM = ItemCondition.isVolumeInBetween(2000, 3500)
            .and(ItemCondition.isWeightLessThanEqual(15));
    public static final Condition IS_LARGE_ITEM = ItemCondition.isVolumeGreaterThanEqual(3500)
            .and(ItemCondition.isWeightLessThanEqual(15));

    @Bean
    public Set<ItemRule> itemRules() {
        return ItemRuleBuilder.newBuilder()
                .addRule(
                        condition(IS_REJECTED),
                        compute(item -> item.setCostAmount(0))
                )
                .addRule(
                        condition(IS_HEAVY_ITEM),
                        compute(item -> item.setCostAmount(30 * item.getWeight()))
                )
                .addRule(
                        condition(IS_SMALL_ITEM),
                        compute(item ->  item.setCostAmount((float)0.04 * item.getVolume()))
                )
                .addRule(
                        condition(IS_MEDIUM_ITEM),
                        compute(item -> item.setCostAmount((float)0.06 * item.getVolume()))
                )
                .addRule(
                        condition(IS_LARGE_ITEM),
                        compute(item -> item.setCostAmount((float) 0.08 * item.getVolume()))
                )
                .build();
    }
}
