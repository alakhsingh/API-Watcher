package com.api.monitor.API.Monitor.models;

public class Url {

    private String urlAddress;
    private Integer status;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
}
