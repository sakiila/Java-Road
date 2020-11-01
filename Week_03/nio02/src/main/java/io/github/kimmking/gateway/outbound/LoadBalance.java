package io.github.kimmking.gateway.outbound;

public enum LoadBalance {

    RANDOM,
    ROUND_ROBIN;

    private String policy;

    public String getPolicy() {
        return policy;
    }
}
