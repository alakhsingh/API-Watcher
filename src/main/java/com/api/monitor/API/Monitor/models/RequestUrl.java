package com.api.monitor.API.Monitor.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "url")
public class RequestUrl implements Serializable {
    @Id
    @Column(name = "url")
    private String url;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "protocol")
    private String protocol;
    @Column(name = "requestmethod")
    private String requestMethod;
    @Column(name = "tag")
    private String tag;

    public RequestUrl(String url, String name, String description, String protocol, String requestMethod, String tag) {
        super();
        this.url = url;
        this.name = name;
        this.description = description;
        this.protocol = protocol;
        this.requestMethod = requestMethod;
        this.tag = tag;
    }

    public RequestUrl() {
        super();
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

}
