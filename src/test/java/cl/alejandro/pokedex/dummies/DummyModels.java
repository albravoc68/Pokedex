package cl.alejandro.pokedex.dummies;

import cl.alejandro.pokedex.model.pokeapi.*;

public class DummyModels {

    protected PokemonListResponse buildPokemonListResponse(int amount) {
        PokemonListResponse result = new PokemonListResponse();
        result.setCount(amount);
        result.setResults(buildDetails(amount));
        return result;
    }

    protected PokemonListResponse.Detail[] buildDetails(int amount) {
        PokemonListResponse.Detail[] details = new PokemonListResponse.Detail[amount];
        for (int i = 0; i < amount; i++) {
            PokemonListResponse.Detail detail = new PokemonListResponse.Detail();
            detail.setName("name" + i);
            detail.setUrl("url" + i);
            details[i] = detail;
        }
        return details;
    }

    protected PokemonResponse buildPokemonResponse(int id) {
        PokemonResponse result = new PokemonResponse();
        result.setId(id);
        result.setName("name" + id);
        result.setWeight(1);
        result.setTypes(buildTypes(2));
        result.setAbilities(buildAbilities(2));

        return result;
    }

    protected PokemonResponse.Type[] buildTypes(int amount) {
        PokemonResponse.Type[] types = new PokemonResponse.Type[amount];
        for (int i = 0; i < amount; i++) {
            PokemonResponse.Type type =  new PokemonResponse.Type();
            type.setSlot(i);
            type.setType(new PokemonResponse.Type.TypeDetail("name" + i));
            types[i] = type;
        }
        return types;
    }

    protected PokemonResponse.Ability[] buildAbilities(int amount) {
        PokemonResponse.Ability[] abilities = new PokemonResponse.Ability[amount];
        for (int i = 0; i < amount; i++) {
            PokemonResponse.Ability ability =  new PokemonResponse.Ability();
            ability.setSlot(i);
            ability.setAbility(new PokemonResponse.Ability.AbilityDetail("name" + i));
            abilities[i] = ability;
        }
        return abilities;
    }

    protected CharacteristicResponse buildCharacteristicResponse(int amount) {
        CharacteristicResponse response = new CharacteristicResponse();
        response.setDescriptions(buildDescriptions(amount));
        return response;
    }

    protected CharacteristicResponse.Description[] buildDescriptions(int amount) {
        CharacteristicResponse.Description[] descriptions = new CharacteristicResponse.Description[amount];
        for (int i = 0; i < amount; i++) {
            CharacteristicResponse.Description description =  new CharacteristicResponse.Description();
            description.setDescription("description" + amount);
            description.setLanguage(new CharacteristicResponse.Description.Language("en"));
            descriptions[i] = description;
        }
        return descriptions;
    }

    protected PokemonSpeciesResponse buildPokemonSpeciesResponse() {
        PokemonSpeciesResponse response = new PokemonSpeciesResponse();
        response.setEvolutionChain(new PokemonSpeciesResponse.EvolutionChain("https://pokeapi.co/api/v2/evolution-chain/1/"));
        return response;
    }

    protected EvolutionChainResponse buildEvolutionChainResponse() {
        EvolutionChainResponse response = new EvolutionChainResponse();
        response.setChain(buildChain());
        return response;
    }

    protected EvolutionChainResponse.Chain buildChain() {
        EvolutionChainResponse.Chain chain = new EvolutionChainResponse.Chain();
        chain.setSpecies(new EvolutionChainResponse.Species("evolution1"));
        chain.setEvolvesTo(new EvolutionChainResponse.Evolve[0]);
        return chain;
    }

}
