package com.example.FileProcessor.service;

import com.example.FileProcessor.model.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface S3Service {
    
    void uploadFile(MultipartFile file) throws IOException;

    List<FileInfo> listFiles() throws FileNotFoundException;

}
