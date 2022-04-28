package cl.alejandro.pokedex.model.pokeapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PokemonSpeciesResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("evolution_chain")
    private EvolutionChain evolutionChain;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class EvolutionChain {
        private String url;
    }

}
