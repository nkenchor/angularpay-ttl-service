package io.angularpay.ttl.ports.outbound;

public interface FeatureServicePort {
    void deleteByTTL(String message);
}
