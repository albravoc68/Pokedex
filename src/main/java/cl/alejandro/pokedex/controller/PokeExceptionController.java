package cl.alejandro.pokedex.controller;

import cl.alejandro.pokedex.exceptions.PokeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class PokeExceptionController {

    @ExceptionHandler(value = PokeException.class)
    @ResponseBody
    public ResponseEntity<String> exception(PokeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}