package dev.harshit.productservice.advice;

import dev.harshit.productservice.dtos.ErrorDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDto> handleNullPointerException(){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("Something went wrong. Please try again");

        return new ResponseEntity<>(errorDto, HttpStatusCode.valueOf(404));
    }
}

// How to handle Exception Way 2:- Create a class annotated with @ControllerAdvice indicating that
// it will to provide global advice for controllers
// @ControllerAdvice is used to define global advice(interceptor) for controllers. It allows to apply
// the same advice to multiple controllers or globally across all controllers in Spring MVC application