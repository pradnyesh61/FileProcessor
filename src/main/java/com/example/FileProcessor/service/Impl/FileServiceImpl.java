package com.example.FileProcessor.service.Impl;

import com.example.FileProcessor.controller.FileProcessorController;
import com.example.FileProcessor.model.FileInfo;
import com.example.FileProcessor.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.FileProcessor.constant.ApplicationMessages.FOLDER_NOT_FOUND;


@Service
public class FileServiceImpl implements FileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);

    @Value("${file.storage.path}")
    private String path;

    @Override
    public String uploadFile(MultipartFile file) throws IOException {

        String fileName = file.getOriginalFilename();

        String filePath = path + fileName;

        isFolderPresent(true);

        Files.copy(file.getInputStream(), Paths.get(filePath));
        LOGGER.info("File {} copied to local folder.", file.getOriginalFilename());

        return fileName;
    }

    private void isFolderPresent(boolean isFileUploadRequest) throws FileNotFoundException {
        LOGGER.info("Check if folder is present or not, if not and request is of upload file then create the folder else throwing not found exception");
        File folderPath = new File(path);
        if (!folderPath.exists()) {
            if (!isFileUploadRequest) {
                throw new FileNotFoundException(FOLDER_NOT_FOUND);
            }
            folderPath.mkdirs();
        }
    }

    @Override
    public List<FileInfo> listFiles() throws FileNotFoundException {
        File folder = new File(path);
        isFolderPresent(false);

        String[] files = folder.list();
        if (files == null) {
            return Collections.emptyList();
        }

        return Arrays.stream(files)
                .map(fileName -> new File(folder, fileName))
                .map(file -> new FileInfo(file.getName(), file.getAbsolutePath()))
                .collect(Collectors.toList());
    }
}
