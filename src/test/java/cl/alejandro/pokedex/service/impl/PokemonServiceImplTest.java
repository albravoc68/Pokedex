package cl.alejandro.pokedex.service.impl;

import cl.alejandro.pokedex.dummies.DummyModels;
import cl.alejandro.pokedex.gateway.PokeApiClient;
import cl.alejandro.pokedex.model.PokemonDataTable;
import cl.alejandro.pokedex.model.PokemonDetail;
import cl.alejandro.pokedex.model.pokeapi.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.fail;

@RunWith(MockitoJUnitRunner.class)
public class PokemonServiceImplTest extends DummyModels {

    @InjectMocks
    private PokemonServiceImpl pokemonService;

    @Mock
    private PokeApiClient pokeApiClient;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(pokemonService, "descLang", "en");
        ReflectionTestUtils.setField(pokemonService, "artworkUrl", "artworkUrl/");
    }

    @Test
    public void getPokemonPageList_negativePage_test() {
        Mockito.when(pokeApiClient.getApiClientResponse(Mockito.anyString(), Mockito.any())).thenReturn("");
        try {
            pokemonService.getPokemonPageList(-1, 1);
            fail("Require exception");
        } catch (Exception e) {
            // must be fail.
        }
    }

    @Test
    public void getPokemonPageList_negativeItems_test() {
        Mockito.when(pokeApiClient.getApiClientResponse(Mockito.anyString(), Mockito.any())).thenReturn("");
        try {
            pokemonService.getPokemonPageList(1, -1);
            fail("Require exception");
        } catch (Exception e) {
            // must be fail.
        }
    }

    @Test
    public void getPokemonPageList_ok_test() {
        Mockito.when(pokeApiClient.getApiClientResponse("pokemon?limit=2&offset=0", PokemonListResponse.class)).thenReturn(buildPokemonListResponse(2));
        Mockito.when(pokeApiClient.getApiClientResponse("pokemon/name0", PokemonResponse.class)).thenReturn(buildPokemonResponse(1));
        Mockito.when(pokeApiClient.getApiClientResponse("pokemon/name1", PokemonResponse.class)).thenReturn(buildPokemonResponse(2));

        PokemonDataTable table = pokemonService.getPokemonPageList(1, 2);
        Assert.assertEquals(1, table.getCurrentPage());
        Assert.assertEquals(2, table.getTotal());
        Assert.assertEquals(2, table.getPokemonList().size());
        Assert.assertEquals("name1", table.getPokemonList().get(0).getName());
        Assert.assertEquals("name2", table.getPokemonList().get(1).getName());
    }

    @Test
    public void getPokemonFromPokeApi_ok_test() {
        Mockito.when(pokeApiClient.getApiClientResponse("pokemon/name0", PokemonResponse.class)).thenReturn(buildPokemonResponse(1));
        Mockito.when(pokeApiClient.getApiClientResponse("characteristic/1", CharacteristicResponse.class)).thenReturn(buildCharacteristicResponse(2));
        Mockito.when(pokeApiClient.getApiClientResponse("pokemon-species/name0", PokemonSpeciesResponse.class)).thenReturn(buildPokemonSpeciesResponse());
        Mockito.when(pokeApiClient.getApiClientResponse("evolution-chain/1", EvolutionChainResponse.class)).thenReturn(buildEvolutionChainResponse());

        PokemonDetail result = pokemonService.getPokemonFromPokeApi("name0");
        Assert.assertEquals(1, result.getPokemon().getId());
        Assert.assertEquals("description2", result.getDescription());
        Assert.assertEquals("evolution1", result.getEvolutions());
    }

}
