package com.fodala.pojo;

public class AjaxResponse {
    private String fileName;
    private boolean uploaded;
    private String url;
    private String message;
    private Error error;

    public AjaxResponse(String fileName, boolean uploaded, String url, String message, Error error) {
        this.fileName = fileName;
        this.uploaded = uploaded;
        this.url = url;
        this.message = message;
        this.error = error;
    }

    public AjaxResponse() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isUploaded() {
        return uploaded;
    }

    public void setUploaded(boolean uploaded) {
        this.uploaded = uploaded;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }


    public static class Error {
        private String message;

        public Error(String message) {
            this.message = message;
        }

        public Error() {
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
