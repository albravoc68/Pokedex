package cl.alejandro.pokedex.gateway.impl;

import cl.alejandro.pokedex.gateway.PokeApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PokeApiClientImpl implements PokeApiClient {

    @Value("${api.pokedex.baseUrl}")
    private String baseUrl;

    @Override
    public WebClient getClient() {
        final int size = 16 * 1024 * 1024;
        final ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();

        return WebClient.builder()
                .baseUrl(baseUrl)
                .exchangeStrategies(strategies)
                .build();
    }

}
