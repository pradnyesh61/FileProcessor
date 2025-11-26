package com.example.FileProcessor.controller;

import com.example.FileProcessor.model.FileInfo;
import com.example.FileProcessor.model.FileResponse;
import com.example.FileProcessor.model.UploadedFilesResponse;
import com.example.FileProcessor.service.FileService;
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

    private final FileService fileService;

    public FileProcessorController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("file/upload")
    public ResponseEntity<FileResponse> fileUpload(@RequestParam(FILE) MultipartFile file) throws IOException {
        String fileName = fileService.uploadFile(file);
        return new ResponseEntity<>(new FileResponse(SUCCESS,FILE_UPLOAD_SUCCESS+SEMI_COLON+fileName), HttpStatus.CREATED);

    }

    @GetMapping("/files")
    public ResponseEntity getFile() throws FileNotFoundException {

        List<FileInfo> files = fileService.listFiles();

        if(files.isEmpty()) {
            //As sending HTTP message 204 so body can not be sent here.
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(new UploadedFilesResponse(files.size(),files),HttpStatus.OK);
    }


}
