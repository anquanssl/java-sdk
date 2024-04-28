package com.anquanssl.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public class HttpBuildQuery {

    private static final Gson gson = new Gson();

    public static String encodeURIComponent(String component) {
        Map<String, String> m = new HashMap<>();
        m.put(" ",  "+");
        m.put("\n", "%0A");
        m.put("!", "%21");
        m.put("\"", "%22");
        m.put("#", "%23");
        m.put("$", "%24");
        m.put("%", "%25");
        m.put("&", "%26");
        m.put("'", "%27");
        m.put("(", "%28");
        m.put(")", "%29");
        m.put("*", "%2A");
        m.put("+", "%2B");
        m.put(",", "%2C");
        m.put("/", "%2F");
        m.put(":", "%3A");
        m.put(";", "%3B");
        m.put("<", "%3C");
        m.put("=", "%3D");
        m.put(">", "%3E");
        m.put("?", "%3F");
        m.put("@", "%40");
        m.put("[", "%5B");
        m.put("\\", "%5C");
        m.put("]", "%5D");
        m.put("^", "%5E");
        m.put("`", "%60");
        m.put("{", "%7B");
        m.put("|", "%7C");
        m.put("}", "%7D");
        m.put("~", "%7E");

        StringBuilder result = new StringBuilder();
        for(int i = 0; i < component.length(); i++) {
            String c = component.substring(i, i+1);

            String s = m.get(c);
            if (s != null) {
                result.append(s);
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    public static String httpBuildQuery(Map<String, Object> data, String prefix) {
        if (data == null || data.isEmpty()) {
            return "";
        }

        StringBuilder query = new StringBuilder();
        List<String> keys = new ArrayList<>(data.keySet());
        Collections.sort(keys);

        for (String key : keys) {
            String param = key;
            Object value = data.get(key);

            if (!prefix.isEmpty()) {
                key = prefix + "[" + param + "]";
            }

            if (value instanceof Map) {
                String json = gson.toJson(value);
                query.append(httpBuildQuery(gson.fromJson(json, new TypeToken<Map<String, Object>>() {}.getType()), key));
            } else {
                query.append(encodeURIComponent(key))
                        .append("=")
                        .append(encodeURIComponent(String.valueOf(value)));
            }

            query.append("&");
        }
        query.deleteCharAt(query.length() - 1);

        return query.toString();
    }
}