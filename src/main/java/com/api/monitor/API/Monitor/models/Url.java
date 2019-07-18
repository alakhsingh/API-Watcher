package com.api.monitor.API.Monitor.models;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;

public class Url {

    private String urlAddress;
    private Integer responseCode;
    private String requestMethod;
    private String timestamp;

    public Url(String urlAddress, String requestMethod) {
        this.urlAddress = urlAddress;
        this.requestMethod = requestMethod;
    }

    public String getUrlAddress() {
        return urlAddress;
    }

    public void setUrlAddress(String urlAddress) {
        this.urlAddress = urlAddress;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public static void setHeaders(HttpURLConnection con) throws IOException {
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "*/*");
        con.setRequestProperty("Cache-Control", "no-cache");
        con.setRequestProperty("Connection", "keep-alive");
        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.flush();
        out.close();
    }
}
