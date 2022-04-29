package cl.alejandro.pokedex.controller;

import cl.alejandro.pokedex.model.PokemonDataTable;
import cl.alejandro.pokedex.model.PokemonDetail;
import cl.alejandro.pokedex.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Controller
@RequestMapping("/")
public class PokedexController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping("/")
    public String getHome() {
        return "home";
    }

    @GetMapping(path = "/page")
    public ResponseEntity<PokemonDataTable> getPageByNumber(
            @RequestParam("number") int number,
            @RequestParam("itemsPerPage") int itemsPerPage
    ) {
        return new ResponseEntity<>(pokemonService.getPokemonPageList(number, itemsPerPage), HttpStatus.OK);
    }

    @GetMapping(path = "/pokemon")
    public ResponseEntity<PokemonDetail> getPokemon(@RequestParam("name") String name) {
        return new ResponseEntity<>(pokemonService.getPokemonFromPokeApi(name.toLowerCase(Locale.ROOT)), HttpStatus.OK);
    }

}
