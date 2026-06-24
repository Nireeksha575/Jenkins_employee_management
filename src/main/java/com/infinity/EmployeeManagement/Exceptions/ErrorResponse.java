package com.infinity.EmployeeManagement.Exceptions;

import java.time.LocalDateTime;

public class ErrorResponse {
    private LocalDateTime time;
    private String message;
    private String description;

    public ErrorResponse(LocalDateTime time, String message, String description) {
        this.time = time;
        this.message = message;
        this.description = description;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
