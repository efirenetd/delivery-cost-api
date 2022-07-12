package org.efire.net.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ResponseDto {

    private String status;
    private String message;
    private Instant timestamp;
    private Object data;

}
