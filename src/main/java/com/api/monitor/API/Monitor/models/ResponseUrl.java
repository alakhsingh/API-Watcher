package com.api.monitor.API.Monitor.models;

public class ResponseUrl {
    private String message;
    private Details details;

    public ResponseUrl(String message, Details details) {
        this.message = message;
        this.details = details;
    }

    public ResponseUrl() {
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("Test [message=%s]", message);
    }

    public static class Details {
        private String date;
        private String statusCode;

        public String getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(String statusCode) {
            this.statusCode = statusCode;
        }

        public Details(String date, String statusCode){
            this.date = date;
            this.statusCode = statusCode;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }


}