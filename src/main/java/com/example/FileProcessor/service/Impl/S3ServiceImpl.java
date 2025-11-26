package com.example.FileProcessor.service.Impl;

import com.example.FileProcessor.model.FileInfo;
import com.example.FileProcessor.service.S3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class S3ServiceImpl implements S3Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(S3ServiceImpl.class);


    private final S3Client s3Client;

    private final S3Presigner s3Presigner;

    @Value("${aws.bucket.name}")
    private String bucketName;

    public S3ServiceImpl(S3Client s3Client, S3Presigner presigner) {
        this.s3Client = s3Client;
        this.s3Presigner = presigner;
    }

    public void uploadFile(MultipartFile file) throws IOException {
        s3Client.putObject(PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(file.getOriginalFilename())
                        .build(),
                RequestBody.fromBytes(file.getBytes()));
        LOGGER.info("File {} uploaded to bucket {}", file.getOriginalFilename(), bucketName);
    }

    @Override
    public List<FileInfo> listFiles() {

        ListObjectsV2Request listObjects = ListObjectsV2Request.builder()
                .bucket(bucketName)
                .build();

        return s3Client.listObjectsV2(listObjects)
                .contents()
                .stream()
                .map(s3Object -> {
                    GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                            .bucket(bucketName)
                            .key(s3Object.key())
                            .build();

                    PresignedGetObjectRequest presignedRequest =
                            s3Presigner.presignGetObject(r -> r.signatureDuration(Duration.ofMinutes(15))
                                    .getObjectRequest(getObjectRequest));

                    LOGGER.debug("Generated presigned URL for file {}: {}", s3Object.key(), presignedRequest.url());
                    return new FileInfo(s3Object.key(), presignedRequest.url().toString());
                })
                .collect(Collectors.toList());
    }


}