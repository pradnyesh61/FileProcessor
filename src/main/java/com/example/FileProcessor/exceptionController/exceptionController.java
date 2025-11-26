package com.example.FileProcessor.exceptionController;

import com.example.FileProcessor.model.ApiError;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;

import static com.example.FileProcessor.constant.ApplicationConstant.BAD_REQUEST;
import static com.example.FileProcessor.constant.ApplicationMessages.*;


@RestControllerAdvice
public class exceptionController {

    private final Logger LOGGER = LoggerFactory.getLogger(exceptionController.class);

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ApiError> handleFileNotFoundException(FileNotFoundException e)
    {
        LOGGER.error("ERROR -> {}",e.getMessage());
        return generateResponse(HttpStatus.NOT_FOUND,FOLDER_NOT_FOUND, String.valueOf(e));
    }

    @ExceptionHandler(FileAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleFileAlreadyExistsException(FileAlreadyExistsException e)
    {
        LOGGER.error("Error -> {}",e.getMessage());
        return generateResponse(HttpStatus.CONFLICT,FILE_ALREADY_EXIST, String.valueOf(e));
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<ApiError> handleMultipartException(MultipartException e)
    {
        LOGGER.error("Error -> {}",e.getMessage());
        return generateResponse(HttpStatus.BAD_REQUEST,FILE_NOT_PROVIDED, String.valueOf(e));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequestException(BadRequestException e)
    {
        LOGGER.error("Error -> {}",e.getMessage());
        return generateResponse(HttpStatus.BAD_REQUEST,BAD_REQUEST,e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception e)
    {
        LOGGER.error("error -> {}", e.getMessage());
        return generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR, e.getMessage());
    }

    private ResponseEntity<ApiError> generateResponse(HttpStatus httpStatus, String message, String detailMessage) {
        return new ResponseEntity<>(new ApiError(String.valueOf(httpStatus),message,detailMessage),httpStatus);
    }
}
