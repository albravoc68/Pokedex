package cl.alejandro.pokedex.service;

import cl.alejandro.pokedex.model.PokemonDataTable;
import cl.alejandro.pokedex.model.PokemonDetail;

public interface PokemonService {

    PokemonDataTable getPokemonPageList(int page, int itemsPerPage);

    PokemonDetail getPokemonFromPokeApi(String name);

}
