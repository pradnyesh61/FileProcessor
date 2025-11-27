package com.example.FileProcessor.controller;

import com.example.FileProcessor.model.FileInfo;
import com.example.FileProcessor.model.FileResponse;
import com.example.FileProcessor.model.UploadedFilesResponse;
import com.example.FileProcessor.service.FileService;
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
@RequestMapping("local/v1/")
public class FileProcessorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileProcessorController.class);


    private final FileService fileService;

    public FileProcessorController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("file/upload")
    public ResponseEntity<FileResponse> fileUpload(@RequestParam(FILE) MultipartFile file) throws IOException {
        LOGGER.info("Uploading file: {}", file.getOriginalFilename());
        String fileName = fileService.uploadFile(file);
        return new ResponseEntity<>(new FileResponse(SUCCESS,FILE_UPLOAD_SUCCESS+SEMI_COLON+fileName), HttpStatus.OK);

    }

    @GetMapping("/files")
    public ResponseEntity getFile() throws FileNotFoundException {
        LOGGER.info("Fetching filesList from local folder");
        List<FileInfo> files = fileService.listFiles();

        if(files.isEmpty()) {
            //As sending HTTP message 204 so body can not be sent here.
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        LOGGER.info("Fetched {} filesList from local folder.",files.size());
        return new ResponseEntity<>(new UploadedFilesResponse(files.size(),files),HttpStatus.OK);
    }


}
