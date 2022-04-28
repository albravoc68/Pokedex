package cl.alejandro.pokedex.gateway;

public interface PokeApiClient {

    <T> T getApiClientResponse(String endpoint, Class<T> returnClass);

}
