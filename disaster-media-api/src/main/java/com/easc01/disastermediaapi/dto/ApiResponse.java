package com.easc01.disastermediaapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private String requestId;
    private String message;
    private Date timestamp;
    private T data;

    @JsonIgnore
    private HttpStatus httpStatus;
}
