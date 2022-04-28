package cl.alejandro.pokedex.service;

import cl.alejandro.pokedex.model.PokemonDataTable;

public interface PokemonService {

    PokemonDataTable getPokemonPageList(int page, int itemsPerPage);

}
