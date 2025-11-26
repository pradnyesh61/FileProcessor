package com.example.FileProcessor.model;

public class FileResponse {

    private String status;
    private String detailMessage;

    public FileResponse(String status, String detailMessage) {
        this.status = status;
        this.detailMessage = detailMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }
}
