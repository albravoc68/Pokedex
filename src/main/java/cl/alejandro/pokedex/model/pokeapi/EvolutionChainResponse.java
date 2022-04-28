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
public class EvolutionChainResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Chain chain;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Chain {
        @JsonProperty("evolves_to")
        private Evolve[] evolvesTo;
        private Species species;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Evolve {
        private Species species;
        @JsonProperty("evolves_to")
        private Evolve[] evolvesTo;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Species {
        private String name;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(chain.species.getName());
        for (Evolve e : chain.evolvesTo) {
            result.append(" - ").append(e.getSpecies().getName());
            if (e.getEvolvesTo() != null && e.getEvolvesTo().length > 0) {
                result.append(" - ").append(e.getEvolvesTo()[0].getSpecies().getName());
            }
        }

        return result.toString();
    }

}
