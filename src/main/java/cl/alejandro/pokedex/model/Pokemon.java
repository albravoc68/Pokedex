package cl.alejandro.pokedex.model;

import cl.alejandro.pokedex.model.pokeapi.PokemonResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pokemon implements Serializable {
    private static final long serialVersionUID = 1L;

    public Pokemon(PokemonResponse pokeResponse, String pictureUrl) {
        this.id = pokeResponse.getId();
        this.name = pokeResponse.getName();
        this.picture = pictureUrl;

        this.types = new ArrayList<>();
        for (PokemonResponse.Type type : pokeResponse.getTypes()) {
            types.add(type.getType().getName());
        }

        this.weight = pokeResponse.getWeight();

        this.abilities = new ArrayList<>();
        for (PokemonResponse.Ability ability : pokeResponse.getAbilities()) {
            abilities.add(ability.getAbility().getName());
        }
    }

    private int id;
    private String name;
    private String picture;
    private ArrayList<String> types;
    private int weight;
    private ArrayList<String> abilities;

}
