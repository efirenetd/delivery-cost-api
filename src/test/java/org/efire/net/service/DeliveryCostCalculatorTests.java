package org.efire.net.service;

import org.efire.net.model.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DeliveryCostCalculatorTests {

    @Autowired
    private DeliveryCostCalculator deliveryCostCalculator;

    @Test
    void testWeightExceedBy80ExpectCostAmountZero() {
        Item inputItem = Item.builder()
                    .weight(81)
                    .costAmount(100).build();
        var updatedItem = deliveryCostCalculator.calculateCost(inputItem);
        assertThat(updatedItem.getCostAmount()).isZero();

    }

    @Test
    void testWeightBetween15to81ExpectHeavyItemCost() {
        Item inputItem = Item.builder()
                .weight(30).build();
        var updatedItem = deliveryCostCalculator.calculateCost(inputItem);
        System.out.println(updatedItem);
        assertThat(updatedItem.getVolume()).isEqualTo(0f);
        assertThat(updatedItem.getCostAmount()).isEqualTo(900.0f);
    }

    @Test
    void testVolumeIsLessThan2000ExpectSmallItemCost() {
        Item inputItem = Item.builder()
                .weight(11)
                .height(10)
                .width(10)
                .length(10)
                .build();
        var updatedItem = deliveryCostCalculator.calculateCost(inputItem);
        assertThat(updatedItem.getVolume()).isEqualTo(1000.0f);
        assertThat(updatedItem.getCostAmount()).isEqualTo(40.0f);
    }

    @Test
    void testVolumeExpectMediumItemCost() {
        Item inputItem = Item.builder()
                .weight(14)
                .height(14)
                .width(14)
                .length(14)
                .build();
        var updatedItem = deliveryCostCalculator.calculateCost(inputItem);
        assertThat(updatedItem.getVolume()).isEqualTo(2744.0f);
        assertThat(updatedItem.getCostAmount()).isEqualTo(164.64f);
    }

    @Test
    void givenVolumeIsWithinMediumItemAButWeightIsHeavyItemExpectHeavyItemCost() {
        Item inputItem = Item.builder()
                .weight(16)
                .height(14)
                .width(14)
                .length(14)
                .build();
        var updatedItem = deliveryCostCalculator.calculateCost(inputItem);
        assertThat(updatedItem.getVolume()).isEqualTo(2744.0f);
        assertThat(updatedItem.getCostAmount()).isEqualTo(480.0f);
    }

    @Test
    void givenVolumeIsWithinSmallItemAButWeightExceededExpectZeroCostRejected() {
        Item inputItem = Item.builder()
                .weight(81)
                .height(10)
                .width(10)
                .length(10)
                .build();
        var updatedItem = deliveryCostCalculator.calculateCost(inputItem);
        assertThat(updatedItem.getCostAmount()).isZero();
    }

    @Test
    void givenVolumeIsGreaterExpectLargeItemCost() {
        Item inputItem = Item.builder()
                .weight(14)
                .height(20)
                .width(20)
                .length(20)
                .build();
        var updatedItem = deliveryCostCalculator.calculateCost(inputItem);
        assertThat(updatedItem.getVolume()).isEqualTo(8000.0f);
        assertThat(updatedItem.getCostAmount()).isEqualTo(640.0f);
    }
}
