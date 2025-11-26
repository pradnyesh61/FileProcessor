package com.example.FileProcessor.controller;


import com.example.FileProcessor.model.FileInfo;
import com.example.FileProcessor.model.FileResponse;
import com.example.FileProcessor.model.UploadedFilesResponse;
import com.example.FileProcessor.service.S3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static com.example.FileProcessor.constant.ApplicationConstant.*;
import static com.example.FileProcessor.constant.ApplicationMessages.FILE_UPLOAD_SUCCESS;


@RestController
@RequestMapping("S3/v1")
public class S3Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(S3Controller.class);

    private final S3Service s3Service;

    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    public ResponseEntity<FileResponse> upload(@RequestParam(FILE) MultipartFile file) throws IOException {
        LOGGER.info("Uploading file: {}", file.getOriginalFilename());
        s3Service.uploadFile(file);
        return new ResponseEntity<>(new FileResponse(SUCCESS, FILE_UPLOAD_SUCCESS + SEMI_COLON + file.getOriginalFilename()), HttpStatus.OK);
    }

    @GetMapping("/files")
    public ResponseEntity<UploadedFilesResponse> listFiles() throws FileNotFoundException {
        LOGGER.info("Fetching files from S3");
        List<FileInfo> files = s3Service.listFiles();

        if (files.isEmpty()) {
            LOGGER.info("No files found in S3 bucket");
            //As sending HTTP message 204 so body can not be sent here.
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        LOGGER.info("Found {} files in S3", files.size());
        return new ResponseEntity<>(new UploadedFilesResponse(files.size(), files), HttpStatus.OK);
    }
}