package com.example.FileProcessor.model;

import java.util.List;

public class UploadedFilesResponse {

    private int totalFilesPresent;

    private List<FileInfo> fileNames;

    public UploadedFilesResponse(int totalFilesPresent, List<FileInfo> fileNames) {
        this.totalFilesPresent = totalFilesPresent;
        this.fileNames = fileNames;
    }

    public int getTotalFilesPresent() {
        return totalFilesPresent;
    }

    public void setTotalFilesPresent(int totalFilesPresent) {
        this.totalFilesPresent = totalFilesPresent;
    }

    public List<FileInfo> getFileNames() {
        return fileNames;
    }

    public void setFileNames(List<FileInfo> fileNames) {
        this.fileNames = fileNames;
    }
}
