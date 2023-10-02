
package io.angularpay.ttl.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TimeToLiveRequest {

    @JsonProperty("deletion_link")
    private String deletionLink;
    @JsonProperty("investment_reference")
    private String investmentReference;
    @JsonProperty("request_created_on")
    private String requestCreatedOn;
    @JsonProperty("request_reference")
    private String requestReference;
    @JsonProperty("service_code")
    private String serviceCode;
}
