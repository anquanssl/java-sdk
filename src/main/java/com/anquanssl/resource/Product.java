package com.anquanssl.resource;

import com.anquanssl.Client;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.HashMap;
import java.util.Map;

public class Product {
    private final Client client;

    public Product(Client client) {
        this.client = client;
    }

    public Map<String, Object> productList() throws IOException, InvalidKeyException {
        return client.get("/product/list", new HashMap<>(), new HashMap<>());
    }
}