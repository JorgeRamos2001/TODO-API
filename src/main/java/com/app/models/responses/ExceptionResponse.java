package com.app.models.responses;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ExceptionResponse {
    private int statusCode;
    private String message;
    private Object details;
    private LocalDateTime timestamp;
    private String path;

}
