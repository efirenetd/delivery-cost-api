package org.efire.net.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@Builder
@ToString
public class Item {

    private float weight;
    private float height;
    private float width;
    private float length;
    private float costAmount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Voucher voucher;

    @ToString.Include
    public float getVolume() {
        return height * width * length;
    }
}
