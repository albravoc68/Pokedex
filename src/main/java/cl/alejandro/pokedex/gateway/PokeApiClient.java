package cl.alejandro.pokedex.gateway;

import org.springframework.web.reactive.function.client.WebClient;

public interface PokeApiClient {

    WebClient getClient();

}
