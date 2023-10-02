package io.angularpay.ttl.adapter.inbound;

import io.angularpay.ttl.adapter.outbound.FeatureServiceAdapter;
import io.angularpay.ttl.domain.commands.PlatformConfigurationsConverterCommand;
import io.angularpay.ttl.models.platform.PlatformConfigurationIdentifier;
import io.angularpay.ttl.ports.inbound.InboundMessagingPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static io.angularpay.ttl.models.platform.PlatformConfigurationSource.TOPIC;

@Service
@RequiredArgsConstructor
public class RedisMessageAdapter implements InboundMessagingPort {

    private final FeatureServiceAdapter featureServiceAdapter;
    private final PlatformConfigurationsConverterCommand converterCommand;

    @Override
    public void onMessage(String message) {
        featureServiceAdapter.deleteByTTL(message);
    }

    @Override
    public void onMessage(String message, PlatformConfigurationIdentifier identifier) {
        this.converterCommand.execute(message, identifier, TOPIC);
    }
}
