package com.api.monitor.API.Monitor.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;

@Entity
@Table(name = "status")
public class Url implements Serializable {

    @Id
    @Column(name = "url")
    private String url;
    @Column(name = "protocol")
    private String protocol;
    @Column(name = "responsecode")
    private Integer responseCode;
    @Column(name = "requestmethod")
    private String requestMethod;
    @Column(name = "tag")
    private String tag;
    @Column(name = "timestamp")
    private String timestamp;
    @Column(name = "timestampSinceLastChange")
    private String timestampSinceLastChange;

    public Url(String url, String requestMethod, String protocol, String tag) {
        this.url = url;
        this.requestMethod = requestMethod;
        this.protocol = protocol;
        this.tag = tag;
    }

    public Url(String url, Integer responseCode, String requestMethod, String timestamp, String protocol, String timestampSinceLastChange, String tag) {
        super();
        this.url = url;
        this.responseCode = responseCode;
        this.requestMethod = requestMethod;
        this.timestamp = timestamp;
        this.protocol = protocol;
        this.timestampSinceLastChange = timestampSinceLastChange;
        this.tag = tag;
    }

    public String getTimestampSinceLastChange() {
        return timestampSinceLastChange;
    }

    public void setTimestampSinceLastChange(String timestampSinceLastChange) {
        this.timestampSinceLastChange = timestampSinceLastChange;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Url() {
        super();
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
