package com.disneyApp.controller;

import com.disneyApp.Dto.ApiErrorDto;
import com.disneyApp.exception.ParamNotFound;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

public class RestExceptionHandler extends ResponseEntityExceptionHandler {


        @ExceptionHandler(value = {ParamNotFound.class})
        protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
            // Instanciamos y construimos una Dto
            ApiErrorDto errorDTO = new ApiErrorDto (HttpStatus.BAD_REQUEST, ex.getMessage(), Arrays.asList("Param Not Found"));
            return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
        }
}
