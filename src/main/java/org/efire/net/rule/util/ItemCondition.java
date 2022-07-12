package org.efire.net.rule.util;

import org.efire.net.model.Item;
import org.efire.net.rule.Condition;

public class ItemCondition implements Condition {

    private Condition itemCondition;

    public ItemCondition(Condition itemCondition) {
        this.itemCondition = itemCondition;
    }

    public static Condition isWeightExceedBy(float weight) {
        return new ItemCondition(item -> item.getWeight() > weight);
    }

    public static Condition isWeightInBetween(float from, float to) {
        return new ItemCondition(item -> {
            var weight = item.getWeight();
            return weight > from && weight < to;
        });
    }

    public static Condition isWeightLessThanEqual(float weight) {
        return new ItemCondition(item -> item.getWeight() <= weight);
    }

    public static Condition isVolumeInBetween(float from, float to) {
        return new ItemCondition(item -> {
            var volume = item.getVolume();
            return volume > from && volume < to;
        });
    }

    public static Condition isVolumeLessThan(float volume) {
        return new ItemCondition(item -> item.getVolume() < volume);
    }

    public static Condition isVolumeGreaterThanEqual(float volume) {
        return new ItemCondition(item -> item.getVolume() >= volume);
    }

    @Override
    public boolean test(Item item) {
        return itemCondition.test(item);
    }
}
