package io.angularpay.ttl.domain;

public enum AngularPayTTLTopics {
    CRYPTO("crypto-ttl"),
    MENIAL("menial-ttl"),
    PAYNABLE("paynable-ttl"),
    PEER_FUND("peer-fund-ttl"),
    PITCH("pitch-ttl"),
    PMT("pmt-ttl"),
    SUPPLY("supply-ttl");

    private final String topic;

    public String topic() {
        return this.topic;
    }

    AngularPayTTLTopics(String topic) {
        this.topic = topic;
    }
}
