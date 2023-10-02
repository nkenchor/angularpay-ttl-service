package io.angularpay.ttl.adapter.outbound;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.angularpay.ttl.configurations.AngularPayConfiguration;
import io.angularpay.ttl.domain.TimeToLiveRequest;
import io.angularpay.ttl.ports.outbound.FeatureServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeatureServiceAdapter implements FeatureServicePort {

    private final WebClient webClient;
    private final AngularPayConfiguration configuration;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void deleteByTTL(String message) {
        log.info("message received: {}", message);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        Runnable invokeApiTask = () -> {
            try {
                TimeToLiveRequest timeToLiveRequest = objectMapper.readValue(String.valueOf(message), TimeToLiveRequest.class);
                log.info("deletion link: {}", timeToLiveRequest.getDeletionLink());
                webClient
                        .delete()
                        .uri(timeToLiveRequest.getDeletionLink())
                        .header("x-angularpay-username", configuration.getServiceHeaders().getUsername())
                        .header("x-angularpay-device-id", configuration.getServiceHeaders().getDeviceId())
                        .header("x-angularpay-user-reference", configuration.getServiceHeaders().getUserReference())
                        .retrieve()
                        .bodyToMono(Void.class)
                        .subscribe();
            } catch (JsonProcessingException exception) {
                log.error("error parsing message to TimeToLiveRequest ", exception);
            }
        };

        scheduledExecutorService.schedule(
                invokeApiTask,
                configuration.getTimeToLiveSLA().getDelay(),
                getTimeUnit()
        );
    }

    private TimeUnit getTimeUnit() {
        try {
            return TimeUnit.valueOf(configuration.getTimeToLiveSLA().getTimeUnit());
        } catch (Exception ignored) {
            return TimeUnit.MINUTES;
        }
    }
}
