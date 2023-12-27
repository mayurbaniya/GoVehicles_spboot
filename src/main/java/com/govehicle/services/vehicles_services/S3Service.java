package com.govehicle.services.vehicles_services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class S3Service {

    @Value("${bucketName}")
    private String bucketName;

    private final AmazonS3 s3;

    public S3Service(AmazonS3 s3){
        this.s3 = s3;
    }

    public String uploadImage(MultipartFile file){

        String filename = file.getOriginalFilename();


        try{
            File convertedFile = convertMultiPartToFile(file);
            PutObjectResult putObjectResult = s3.putObject(bucketName, filename, convertedFile);
            return  filename;
        }catch (Exception ex){
            System.out.println(ex);
        return "something went wrong";
        }


    }


    private File convertMultiPartToFile(MultipartFile file) throws IOException {

        File convoFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convoFile);

        fos.write(file.getBytes());
        fos.close();
        return convoFile;
    }
}
