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
public class PokemonResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private Type[] types;
    private int weight;
    private Ability[] abilities;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Type {
        private static final long serialVersionUID = 1L;
        private int slot;
        private TypeDetail type;

        @NoArgsConstructor
        @AllArgsConstructor
        @Getter
        @Setter
        public static class TypeDetail {
            private String name;
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Ability {
        private static final long serialVersionUID = 1L;
        private int slot;
        private AbilityDetail ability;

        @NoArgsConstructor
        @AllArgsConstructor
        @Getter
        @Setter
        public static class AbilityDetail {
            private String name;
        }
    }

}
