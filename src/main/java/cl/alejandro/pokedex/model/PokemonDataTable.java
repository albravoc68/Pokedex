package cl.alejandro.pokedex.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PokemonDataTable implements Serializable {
    private static final long serialVersionUID = 1L;

    private int currentPage;
    private int total;
    private ArrayList<Pokemon> pokemonList;

}
