# 📁 File Processor – Spring Boot + AWS S3

A lightweight Spring Boot application that allows to:

✔ Upload files to an AWS S3 bucket  
✔ Retrieve the list of uploaded files  
✔ Get pre-signed URLs for secure, temporary file access  

This project demonstrates clean code structure, S3 integration, global exception handling, and proper logging.



## 🚀 Features

- Upload files to AWS S3
- List all files stored in the bucket
- Generate pre-signed URLs (valid for 15 minutes)
- Global Exception Handling using `@RestControllerAdvice`
- Well-structured Service + Controller Layer
- Externalized AWS credentials & bucket config
- Proper logging



## 🧱 Tech Stack

| Component | Technology |
|----------|------------|
| Backend  | Spring Boot |
| Cloud Storage | AWS S3 |
| Build Tool | Maven |
| Java Version | 21 |
| AWS SDK | AWS SDK v2 |


## Flow Diagram
<img width="1975" height="1549" alt="NICE-Assesement" src="https://github.com/user-attachments/assets/dfccc257-2e89-45e7-ae33-13e70daab613" />

## Architecture diagram
<img width="1776" height="1294" alt="untitled (11)" src="https://github.com/user-attachments/assets/8c4dc931-203b-4a15-93ca-8215c5be0b98" />


## Run Locally
- make sure Java verison 21 and maven is installed
- git clone [https://github.com/pradnyesh61/FileProcessor.git](https://github.com/pradnyesh61/FileProcessor.git)
- cd FileProcessor
- mvn clean install
- Update properties in application.properties file
     - cloud.aws.credentials.access-key
     - cloud.aws.credentials.secret-key
     - cloud.aws.region.static
     - aws.bucket.name

- mvn spring-boot:run

## Rest-Endpoints-
- /upload
  - URL -http://localhost:8080/S3/v1/upload
  - HTTTP Method - Post
  - Request - multipart
    - file: TestFile1.docx
  - Response -
   {
  "status": "Success",
  "detailMessage": "File uploaded successfully!:TestFile1.docx"
}

- /files
   - URL - http://localhost:8080/S3/v1/files
   - HTTP Method - GET
   - Request - {}
   - Response -{
  "totalFilesPresent": 1,
  "fileNames": [
    {
      "fileName": "TestFile1.docx",
      "url": "https://my-fileprocessor-bucket.s3.ap-south-1.amazonaws.com/TestFile1.docx?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20251126T214128Z&X-Amz-SignedHeaders=host&X-Amz-Expires=900&X-Amz-Credential=AKIASHAV6RDLRDRECYBQ%2F20251126%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Signature=f3bc42ba55bf02875307609df073e179c12d9ebdb9841b48038c4b376c708083"
    }
  ]
}



## Test Result -
if test result is not accessible then please check in the repository - /TestResult/test_result.pdf

[test_result.pdf](https://github.com/user-attachments/files/23783688/test_result.pdf)


## Sample file to upload - 
Please check in the repository - /UploadFiles

https://github.com/pradnyesh61/FileProcessor/tree/main/UploadFiles

## Deployment Process -

https://github.com/pradnyesh61/FileProcessor/tree/main/deploymentProcess



## Postman Collection -
https://github.com/pradnyesh61/FileProcessor/tree/main/PostmanCollection



