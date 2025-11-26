package com.example.FileProcessor.Model;

public class ApiError {

    private String errorCode;

    private String errorMessage;

    private String detailMessage;

    public ApiError(String errorCode, String errorMessage, String detailMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.detailMessage = detailMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }
}
