package org.efire.net.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemDtoTests {

    private static Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void givenWeightWhenMoreThan3digitsExpectOutOfBoundError() {
        var itemDto = new ItemDto();
        itemDto.setWeight(BigDecimal.valueOf(1111.00));
        itemDto.setHeight(BigDecimal.ONE);
        itemDto.setWidth(BigDecimal.ONE);
        itemDto.setLength(BigDecimal.ONE);

        Set<ConstraintViolation<ItemDto>> violations = validator.validate(itemDto);
        assertThat(violations).hasSize(1);
        assertThat(violations).extracting("message")
                .containsOnly("numeric value out of bounds (<3 digits>.<2 digits> expected)");
    }

    @Test
    void givenWeightAndWidthIsNullExpectError() {
        var itemDto = new ItemDto();
        itemDto.setWeight(null);
        itemDto.setHeight(BigDecimal.ONE);
        itemDto.setWidth(null);
        itemDto.setLength(BigDecimal.ONE);

        Set<ConstraintViolation<ItemDto>> violations = validator.validate(itemDto);

        assertThat(violations).hasSize(2);
        assertThat(violations).extracting("message")
                .containsOnly("Weight should not be null.",
                        "Width should not be null.");
    }
}
