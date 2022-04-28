package cl.alejandro.pokedex.model.pokeapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PokemonListResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private int count;
    private Detail[] results;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Detail {
        private String name;
        private String url;
    }

}
