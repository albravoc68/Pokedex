package cl.alejandro.pokedex.controller;

import cl.alejandro.pokedex.model.Pokemon;
import cl.alejandro.pokedex.model.PokemonDataTable;
import cl.alejandro.pokedex.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/")
public class PokedexController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping(path = "/page")
    public ResponseEntity<PokemonDataTable> getPageByNumber(
            @RequestParam("number") int number,
            @RequestParam("itemsPerPage") int itemsPerPage
    ) {
        return new ResponseEntity<>(pokemonService.getPokemonPageList(number, itemsPerPage), HttpStatus.OK);
    }

}
