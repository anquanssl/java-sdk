package com.anquanssl;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.net.URI;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.stream.Collectors;

import com.anquanssl.utils.HttpBuildQuery;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import com.google.gson.reflect.TypeToken;

public class Client {
    private final String accessKeyID;
    private final String accessKeySecret;
    private final String APIOrigin;

    private final Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setObjectToNumberStrategy(ToNumberPolicy.LAZILY_PARSED_NUMBER)
            .create();

    private final String ORIGIN_API = "https://api.orion.pki.plus/api/v1";

    public Client(String accessKeyID, String accessKeySecret, String apiOrigin) {
        this.accessKeyID = accessKeyID;
        this.accessKeySecret = accessKeySecret;
        if (Objects.equals(apiOrigin, "")){
            apiOrigin = ORIGIN_API;
        }
        this.APIOrigin = apiOrigin;
    }

    public Map<String, Object> get(String uri, Map<String, String> query, Map<String, Object> body) throws IOException, InvalidKeyException {
        return call("GET", uri, query, body);
    }

    public Map<String, Object> post(String uri, Map<String, String> query, Map<String, Object> body) throws IOException, InvalidKeyException {
        return call("POST", uri, query, body);
    }

    private Map<String, Object> call(String method, String uri, Map<String, String> query, Map<String, Object> body) throws IOException, InvalidKeyException {
        query.put("accessKeyId", accessKeyID);
        query.put("nonce", java.util.UUID.randomUUID().toString().replace("-", ""));
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        query.put("timestamp", now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")));

        Map<String, Object> parameter = new HashMap<>(query);
        String queryJson = HttpBuildQuery.httpBuildQuery(parameter, "");
        parameter.putAll(body);
        String baseString = HttpBuildQuery.httpBuildQuery(parameter, "");
        URL url = new URL(APIOrigin + uri + "?" + queryJson);
        String signature = sign(url.getPath() + "?" + baseString, accessKeySecret);

        query.put("sign", signature);
        url = new URL(APIOrigin + uri + "?" + HttpBuildQuery.httpBuildQuery(new HashMap<>(query), ""));

        HttpURLConnection connection = (HttpURLConnection) (url).openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.connect();
        if ("POST".equals(method)) {
            String bodyJson = gson.toJson(body);
            OutputStream os = connection.getOutputStream();
            os.write(bodyJson.getBytes(StandardCharsets.UTF_8));
            os.close();
        }

        int responseCode = connection.getResponseCode();
        InputStream inputStream = responseCode == HttpURLConnection.HTTP_OK ?
                connection.getInputStream() : connection.getErrorStream();
        String result = new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));
        return gson.fromJson(result, new TypeToken<Map<String, Object>>() {}.getType());
    }

    private String sign(String baseString, String accessKeySecret) throws InvalidKeyException {
        Mac hmacSha256;
        try {
            hmacSha256 = Mac.getInstance("HmacSHA256");
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
        SecretKeySpec secretKey = new SecretKeySpec(accessKeySecret.getBytes(), "HmacSHA256");
        hmacSha256.init(secretKey);
        byte[] hash = hmacSha256.doFinal(baseString.getBytes());
        return Base64.getEncoder().encodeToString(hash);
    }
}