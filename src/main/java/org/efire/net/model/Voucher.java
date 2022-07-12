package org.efire.net.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Voucher implements Serializable {

    private String code;
    private BigDecimal discount;

}
