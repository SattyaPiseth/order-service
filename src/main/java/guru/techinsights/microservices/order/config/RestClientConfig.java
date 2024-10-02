package guru.techinsights.microservices.order.config;

import guru.techinsights.microservices.order.client.InventoryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.Duration;

/**
 * @author Sattya
 * created at 10/1/2024 2:42 AM
 *
 * Configuration class for setting up a RestClient to interact with external services,
 * particularly the Inventory service.
 */
@Configuration
public class RestClientConfig {

    // Injecting the 'inventory.url' property from the application properties file.
    @Value("${inventory.url}")
    private String inventoryServiceUrl;

    /**
     * Creates a bean of InventoryClient, which will be used to communicate with the Inventory service.
     * The InventoryClient interface will be implemented dynamically by the HttpServiceProxyFactory.
     *
     * @return InventoryClient instance
     */
    @Bean
    public InventoryClient inventoryClient(){
        // Building a RestClient instance with the base URL of the Inventory service and custom request factory.
        RestClient restClient = RestClient.builder()
                .baseUrl(inventoryServiceUrl)  // Set base URL for Inventory service
                .requestFactory(getClientRequestFactory())  // Set the request factory (timeouts, etc.)
                .build();

        // Creating a RestClientAdapter to adapt the RestClient for dynamic proxy generation.
        var restClientAdapter = RestClientAdapter.create(restClient);

        // Creating a proxy factory that will generate the implementation for the InventoryClient interface.
        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();

        // Dynamically creating an instance of InventoryClient using the proxy factory.
        return httpServiceProxyFactory.createClient(InventoryClient.class);
    }

    /**
     * Configures the request factory with custom timeout settings for HTTP connections and responses.
     *
     * @return ClientHttpRequestFactory with the specified settings.
     */
    private ClientHttpRequestFactory getClientRequestFactory(){
        // Setting default connection and read timeouts to 3 seconds.
        ClientHttpRequestFactorySettings clientHttpRequestFactorySettings = ClientHttpRequestFactorySettings.DEFAULTS
                .withConnectTimeout(Duration.ofSeconds(3))  // 3-second connection timeout
                .withReadTimeout(Duration.ofSeconds(3));    // 3-second read timeout

        // Return a ClientHttpRequestFactory using the settings defined above.
        return ClientHttpRequestFactories.get(clientHttpRequestFactorySettings);
    }
}
