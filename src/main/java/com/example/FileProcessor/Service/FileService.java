package com.example.FileProcessor.Service;

import com.example.FileProcessor.Model.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface FileService {

    String uploadFile(MultipartFile file) throws IOException;

    List<FileInfo> listFiles() throws FileNotFoundException;
}
