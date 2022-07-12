package org.efire.net.expection;

import org.efire.net.dto.ResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@ControllerAdvice
public class CustomerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        var exceptionResponse = ResponseDto.builder()
                .message("Validation failed")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .timestamp(Instant.now())
                .data(details)
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        var details = ex.getBindingResult().getAllErrors()
                .stream().map(ObjectError::getDefaultMessage).collect(toList());
        var validation_failed = ResponseDto.builder()
                .message("Validation failed")
                .status(HttpStatus.BAD_REQUEST.toString())
                .timestamp(Instant.now())
                .data(details)
                .build();
        return new ResponseEntity<>(validation_failed, HttpStatus.BAD_REQUEST);
    }
}
