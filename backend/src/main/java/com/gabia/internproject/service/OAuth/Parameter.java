package com.gabia.internproject.service.OAuth;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Parameter {
    private final String key;
    private final String value;

    public Parameter(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String asUrlEncodedPair() throws UnsupportedEncodingException {

            return URLEncoder.encode(key,"UTF-8").concat("=").concat( URLEncoder.encode(value,"UTF-8"));


    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
