package cl.alejandro.pokedex.service.impl;

import cl.alejandro.pokedex.exceptions.PokeException;
import cl.alejandro.pokedex.gateway.PokeApiClient;
import cl.alejandro.pokedex.model.Pokemon;
import cl.alejandro.pokedex.model.PokemonDataTable;
import cl.alejandro.pokedex.model.pokeapi.PokemonListResponse;
import cl.alejandro.pokedex.model.pokeapi.PokemonResponse;
import cl.alejandro.pokedex.service.PokemonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class PokemonServiceImpl implements PokemonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PokemonServiceImpl.class);
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(15);
    private static final HashMap<String, Pokemon> pokemonDetailByName = new HashMap<>();

    @Autowired
    private PokeApiClient pokeApiClient;

    @Value("${api.pokedex.artworkUrl}")
    private String artworkUrl;

    public PokemonDataTable getPokemonPageList(int page, int itemsPerPage) {
        if (page <= 0) {
            LOGGER.error("Error in #getPokemonPageList the page parameter must be greater than 0.");
            throw new RuntimeException("Page must be greater than 0.");
        }
        if (itemsPerPage < 0) {
            LOGGER.error("Error in #getPokemonPageList the itemsPerPage cant be negative.");
            throw new RuntimeException("Pokemon per page cant be negative.");
        }

        int offset = (page - 1) * itemsPerPage;
        String endpoint = "pokemon?limit=" + itemsPerPage + "&offset=" + offset;
        PokemonListResponse listResponse = getApiClientJsonResponse(endpoint, PokemonListResponse.class);

        PokemonDataTable result = new PokemonDataTable();
        result.setCurrentPage(page);
        result.setTotal(listResponse.getCount());
        ArrayList<Pokemon> pokemonList = new ArrayList<>();
        for (PokemonListResponse.Detail detail : listResponse.getResults()) {
            String pokename = detail.getName();
            if (pokemonDetailByName.containsKey(pokename)) {
                pokemonList.add(pokemonDetailByName.get(pokename));
                continue;
            }

            PokemonResponse pokeResponse = getApiClientJsonResponse("pokemon/" + pokename, PokemonResponse.class);
            Pokemon pokemon = new Pokemon(pokeResponse, artworkUrl + pokeResponse.getId() + ".png");
            pokemonList.add(pokemon);
            pokemonDetailByName.put(pokename, pokemon);
        }
        result.setPokemonList(pokemonList);

        return result;
    }

    private <T> T getApiClientJsonResponse(String endpoint, Class<T> returnClass) {
        try {
            return pokeApiClient
                    .getClient()
                    .get()
                    .uri(endpoint)
                    .retrieve()
                    .bodyToMono(returnClass)
                    .block(REQUEST_TIMEOUT);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new PokeException("Error connecting to PokeApi.");
        }
    }

}
