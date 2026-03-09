package com.example.payments;

import java.util.Objects;

public class FastPayAdapter implements PaymentGateway {
    private final FastPayClient client;

    public FastPayAdapter(FastPayClient client) {
        this.client = Objects.requireNonNull(client, "client");
    }

    @Override
    public String charge(String customerId, int amountCents) {
        Objects.requireNonNull(customerId, "customerId");
        if (amountCents <= 0) {
            throw new IllegalArgumentException("amountCents must be > 0");
        }
        return client.payNow(customerId, amountCents);
    }
}
