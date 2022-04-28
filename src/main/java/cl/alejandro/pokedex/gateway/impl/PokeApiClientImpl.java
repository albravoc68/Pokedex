package cl.alejandro.pokedex.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PokeApiClientImpl implements PokeApiClient {

    @Value("{api.pokedex.baseUrl}")
    private String baseUrl;

    @Override
    public WebClient getClient() {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

}
