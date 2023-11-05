package com.desarrolloweb.redsocial.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@RestController
@RequestMapping("/v1")
public class S3Controller {
    @Autowired
    private AmazonS3 amazonS3;
    private String bucketName = "react-desarrollo-web-cristian";

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize()); // Donde 'file' es el MultipartFile
            PutObjectRequest request = new PutObjectRequest(bucketName, file.getOriginalFilename(), file.getInputStream(), metadata);
            amazonS3.putObject(request);
            return "File uploaded successfully!";
        } catch (Exception e) {
            return "Error uploading file: " + e.getMessage();
        }
    }
}
