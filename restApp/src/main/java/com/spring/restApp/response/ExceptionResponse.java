package com.spring.restApp.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class ExceptionResponse{
    private String timeStamp= getCurrentTimestamp();
    private HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    private boolean status = false;
    private String message;


	private String getCurrentTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        return now.format(formatter);
    }
}
