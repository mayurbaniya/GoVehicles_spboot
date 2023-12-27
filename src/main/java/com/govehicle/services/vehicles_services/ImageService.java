package com.govehicle.services.vehicles_services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImageService {

    @Value("${bucketName}")
    private String bucketName;

    private final AmazonS3 s3;

    public ImageService(AmazonS3 s3){
        this.s3 = s3;
    }


    public String uploadImage(MultipartFile image) throws IOException {
        // getting image name

        String imagename = image.getOriginalFilename();

        String randomID = UUID.randomUUID().toString();
        String imageUUIDName = randomID.concat(imagename.substring(imagename.lastIndexOf(".")));

        String imageUrl = "https://govehicles.s3.ap-south-1.amazonaws.com/";


        try{
            File convertedFile = convertMultiPartToFile(image);
            s3.putObject(bucketName, imageUUIDName, convertedFile);

            return imageUrl+imageUUIDName;

        }catch (Exception e){
            return "something went wrong";
        }


//        String filteredBrand = brand.replaceAll("[^a-zA-Z0-9]","-");
//        System.out.println("brand : - :" + brand);
//
//        // getting image name
//        String imagename = image.getOriginalFilename();
//
//        String randomID = UUID.randomUUID().toString();
//        String imageUUIDName = randomID.concat(imagename.substring(imagename.lastIndexOf(".")));
//
//
//        //getting full path
//        String imagePath = path + File.separator+ filteredBrand + File.separator + imageUUIDName;
//
//
//        // creating a folder if not created
//        File file = new File(path+File.separator+filteredBrand.toLowerCase());
//        if(!file.exists()) {
//            boolean mkdir = file.mkdir();
//            System.out.println("mkdirs result: "+ mkdir);
//        }
//
//        // copying/uploading image
//        try(InputStream inputStream = image.getInputStream()) {
//            Files.copy(inputStream, Paths.get(imagePath));
//            return brand + File.separator + imageUUIDName;
//        }catch (Exception e){
//            e.printStackTrace();
//             return null;
//        }

    }
    private File convertMultiPartToFile(MultipartFile file) throws IOException {

        File convoFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convoFile);

        fos.write(file.getBytes());
        fos.close();
        return convoFile;
    }

}


// ::::::::::::::::::::: creating brand folder  ::::::::::::::::::::::::::: //
//    public List<String> createBrand(MultipartFile brandImage, String path, String brand) throws IOException {
//        String filteredBrand = brand.replaceAll("[^a-zA-Z0-9]","-");
//        System.out.println("brand : - :" + brand);
//
//        // getting image name
//        String imageName = brandImage.getOriginalFilename();
//
//        // random UUID brand image name
//        String randomID = UUID.randomUUID().toString();
//        String imageUUIDName = randomID.concat(imageName.substring(imageName.lastIndexOf(".")));
//
//        // full image path
//        String imagePath = path + File.separator + filteredBrand + File.separator + imageUUIDName;
//
//
//        // creating a brand folder
//        File file = new File(path+filteredBrand.toLowerCase());
//            if(!file.exists()){
//                boolean mkdir = file.mkdir();
//                System.out.println("created brand: " + mkdir);
//            }else{
//
//            }
//
//            // saving/ copying image
//
//        try(InputStream inputStream = brandImage.getInputStream()){
//            Files.copy(inputStream, Paths.get(imagePath));
//
//            List<String> brands = new ArrayList<>();
//            brands.add(filteredBrand);
//            brands.add(filteredBrand + File.separator + imageUUIDName);
//            return brands;
////return brand + File.separator + imageUUIDName;
//        }catch(Exception e){
//            e.printStackTrace();
//            return null;
//        }
//
//    }


