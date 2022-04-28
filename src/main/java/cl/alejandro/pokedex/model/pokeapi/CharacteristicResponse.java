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
public class CharacteristicResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Description[] descriptions;


    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Description {
        private String description;
        private Language language;

        @NoArgsConstructor
        @AllArgsConstructor
        @Getter
        @Setter
        public static class Language {
            private String name;
        }
    }
}
