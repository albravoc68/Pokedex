package cl.alejandro.pokedex.gateway.impl;

import cl.alejandro.pokedex.exceptions.PokeException;
import cl.alejandro.pokedex.gateway.PokeApiClient;
import cl.alejandro.pokedex.service.impl.PokemonServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Service
public class PokeApiClientImpl implements PokeApiClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(PokemonServiceImpl.class);
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(15);

    @Value("${api.pokedex.baseUrl}")
    private String baseUrl;

    private WebClient getClient() {
        final int size = 16 * 1024 * 1024;
        final ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();

        return WebClient.builder()
                .baseUrl(baseUrl)
                .exchangeStrategies(strategies)
                .build();
    }

    @Override
    public <T> T getApiClientResponse(String endpoint, Class<T> returnClass) {
        try {
            return getClient()
                    .get()
                    .uri(endpoint)
                    .retrieve()
                    .bodyToMono(returnClass)
                    .block(REQUEST_TIMEOUT);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new PokeException("Error connecting to PokeApi.");
        }
    }

}
