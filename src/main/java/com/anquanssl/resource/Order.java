package com.anquanssl.resource;

import com.anquanssl.Client;
import com.anquanssl.request.CertificateCreateRequest;
import com.anquanssl.request.CertificateDetailRequest;
import com.anquanssl.request.CertificateReissueRequest;
import com.anquanssl.request.CertificateValidateDCVRequest;
import com.anquanssl.request.CertificateRefundRequest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private final Client client;

    private final Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setObjectToNumberStrategy(ToNumberPolicy.LAZILY_PARSED_NUMBER)
            .create();

    public Order(Client client) {
        this.client = client;
    }

    private HashMap<String, Object> objectToMap(Object data) {
        String json = gson.toJson(data);
        return gson.fromJson(json, new TypeToken<HashMap<String, Object>>() {}.getType());
    }

    private HashMap<String, String> objectToStringMap(Object data) {
        String json = gson.toJson(data);
        System.out.println(json);
        return gson.fromJson(json, new TypeToken<HashMap<String, String>>() {}.getType());
    }

    public Map<String, Object> certificateCreate(CertificateCreateRequest certificateCreateRequest) throws IOException, InvalidKeyException {
        return client.post("/certificate/create", new HashMap<>(), objectToMap(certificateCreateRequest));
    }

    public Map<String, Object> certificateDetail(CertificateDetailRequest certificateDetailRequest) throws IOException, InvalidKeyException {
        return client.get("/certificate/detail", objectToStringMap(certificateDetailRequest), new HashMap<>());
    }

    public Map<String, Object> certificateReissue(CertificateReissueRequest certificateReissueRequest) throws IOException, InvalidKeyException {
        return client.post("/certificate/reissue", new HashMap<>(), objectToMap(certificateReissueRequest));
    }

    public Map<String, Object> certificateValidateDCV(CertificateValidateDCVRequest certificateValidateDCVRequest) throws IOException, InvalidKeyException {
        return client.post("/certificate/validate-dcv", new HashMap<>(), objectToMap(certificateValidateDCVRequest));
    }

    public Map<String, Object> certificateRefund(CertificateRefundRequest certificateRefundRequest) throws IOException, InvalidKeyException {
        return client.post("/certificate/refund", new HashMap<>(), objectToMap(certificateRefundRequest));
    }
}