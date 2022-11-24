package com.quan.cardadatabase.Exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String message;
    private LocalDateTime localDateTime;
    private Throwable throwable;

    public ErrorResponse(String message, LocalDateTime localDateTime, Throwable throwable) {
        this.message = message;
        this.localDateTime = localDateTime;
        this.throwable = throwable;
    }
    
}
