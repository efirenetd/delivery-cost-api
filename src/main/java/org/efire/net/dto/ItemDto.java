package org.efire.net.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ItemDto {

    @DecimalMin(value = "0.00", inclusive = false)
    @Digits(fraction = 2, integer = 3)
    @NotNull(message = "Weight should not be null.")
    private BigDecimal weight;

    @DecimalMin(value = "0.00", inclusive = false)
    @Digits(fraction = 2, integer = 3)
    @NotNull(message = "Height should not be null.")
    private BigDecimal height;

    @DecimalMin(value = "0.00", inclusive = false)
    @Digits(fraction = 2, integer = 3)
    @NotNull(message = "Width should not be null.")
    private BigDecimal width;

    @DecimalMin(value = "0.00", inclusive = false)
    @Digits(fraction = 2, integer = 3)
    @NotNull(message = "Length should not be null.")
    private BigDecimal length;

    private String voucher;

}
