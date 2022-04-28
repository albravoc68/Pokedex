package cl.alejandro.pokedex.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PokemonDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    private Pokemon pokemon;
    private String description;
    private String evolutions;

}
