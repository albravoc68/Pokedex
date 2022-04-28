package cl.alejandro.pokedex.service.impl;

import cl.alejandro.pokedex.gateway.PokeApiClient;
import cl.alejandro.pokedex.model.Pokemon;
import cl.alejandro.pokedex.model.PokemonDataTable;
import cl.alejandro.pokedex.model.PokemonDetail;
import cl.alejandro.pokedex.model.pokeapi.*;
import cl.alejandro.pokedex.service.PokemonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

@Service
public class PokemonServiceImpl implements PokemonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PokemonServiceImpl.class);
    private static final HashMap<String, Pokemon> pokemonByName = new HashMap<>();
    private static final HashMap<String, PokemonDetail> pokemonDetailByName = new HashMap<>();

    @Autowired
    private PokeApiClient pokeApiClient;

    @Value("${api.pokedex.artworkUrl}")
    private String artworkUrl;

    @Value("${api.pokedex.description.lang}")
    private String descLang;

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
        PokemonListResponse listResponse = pokeApiClient.getApiClientResponse(endpoint, PokemonListResponse.class);

        PokemonDataTable result = new PokemonDataTable();
        result.setCurrentPage(page);
        result.setTotal(listResponse.getCount());
        ArrayList<Pokemon> pokemonList = new ArrayList<>();
        for (PokemonListResponse.Detail detail : listResponse.getResults()) {
            String pokeName = detail.getName();
            if (pokemonByName.containsKey(pokeName)) {
                pokemonList.add(pokemonByName.get(pokeName));
                continue;
            }

            Pokemon pokemon = getPokemon(pokeName);
            pokemonList.add(pokemon);
        }
        result.setPokemonList(pokemonList);

        return result;
    }

    public PokemonDetail getPokemonFromPokeApi(String name) {
        if (pokemonDetailByName.containsKey(name)) {
            return pokemonDetailByName.get(name);
        }

        PokemonDetail result = new PokemonDetail();
        if (pokemonByName.containsKey(name)) {
            result.setPokemon(pokemonByName.get(name));
        } else {
            result.setPokemon(getPokemon(name));
        }
        result.setDescription(getDescription(result.getPokemon().getId()));
        result.setEvolutions(getEvolutions(name));

        pokemonDetailByName.put(name, result);
        return result;
    }

    private String getEvolutions(String name) {
        PokemonSpeciesResponse species = pokeApiClient.getApiClientResponse("pokemon-species/" + name, PokemonSpeciesResponse.class);
        String rawChain = species.getEvolutionChain().getUrl()
                .replace("https://pokeapi.co/api/v2/evolution-chain/", "")
                .replace("/", "");

        int chain = Integer.parseInt(rawChain);
        EvolutionChainResponse evolutions = pokeApiClient.getApiClientResponse("evolution-chain/" + chain, EvolutionChainResponse.class);
        return evolutions.toString();
    }

    private String getDescription(int id) {
        try {
            CharacteristicResponse characteristic = pokeApiClient.getApiClientResponse("characteristic/" + id, CharacteristicResponse.class);
            Optional<CharacteristicResponse.Description> desc = Arrays.stream(characteristic.getDescriptions())
                    .filter(c -> descLang.equals(c.getLanguage().getName()))
                    .findFirst();
            return desc.isPresent() ? desc.get().getDescription() : "Not Found.";
        } catch (Exception e) {
            return "Not Found in selected language [" + descLang + "]";
        }
    }

    private Pokemon getPokemon(String name) {
        PokemonResponse pokeResponse = pokeApiClient.getApiClientResponse("pokemon/" + name, PokemonResponse.class);
        Pokemon pokemon = new Pokemon(pokeResponse, artworkUrl + pokeResponse.getId() + ".png");
        pokemonByName.put(name, pokemon);
        return pokemon;
    }

}
