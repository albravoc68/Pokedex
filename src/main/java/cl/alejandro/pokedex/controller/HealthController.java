package cl.alejandro.pokedex.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HealthController.class);

    @GetMapping
    public ResponseEntity<String> getHealth() {
        LOGGER.info("Status Ok");
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

}
