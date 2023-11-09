package com.desarrolloweb.redsocial.Tools;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class FileExceptionAdice {
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Map<String, String>> handleMaxSizeException(MaxUploadSizeExceededException ex){
        System.out.println("error de tamaño");
        System.out.println(ex);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Maximo de tamaño 100 megas");
        return ResponseEntity.badRequest().body(response);
    }

}
