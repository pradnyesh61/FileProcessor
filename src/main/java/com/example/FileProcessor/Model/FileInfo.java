package com.example.FileProcessor.Model;

public class FileInfo {

    private String fileName;

    private String url;

    public FileInfo(String fileName, String url) {
        this.fileName = fileName;
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public String getUrl() {
        return url;
    }
}
