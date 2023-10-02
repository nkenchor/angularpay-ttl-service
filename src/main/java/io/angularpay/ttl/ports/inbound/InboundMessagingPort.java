package io.angularpay.ttl.ports.inbound;

import io.angularpay.ttl.models.platform.PlatformConfigurationIdentifier;

public interface InboundMessagingPort {
    void onMessage(String message);
    void onMessage(String message, PlatformConfigurationIdentifier identifier);
}
