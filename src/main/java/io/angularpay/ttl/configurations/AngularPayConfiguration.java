package io.angularpay.ttl.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("angularpay")
@Data
public class AngularPayConfiguration {

    private TimeToLiveSLA timeToLiveSLA;
    private Redis redis;
    private ServiceHeaders serviceHeaders;

    @Data
    public static class TimeToLiveSLA {
        private int delay;
        private String timeUnit;
    }

    @Data
    public static class Redis {
        private String host;
        private int port;
        private int timeout;
    }

    @Data
    public static class ServiceHeaders {
        private String username;
        private String deviceId;
        private String userReference;
    }
}
