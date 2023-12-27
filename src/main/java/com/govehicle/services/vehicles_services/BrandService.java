package com.govehicle.services.vehicles_services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.govehicle.entities.vehicles.Brand;
import com.govehicle.payload.vehicles.Brands;
import com.govehicle.repositories.vehiclerepositories.BrandRepository;
import com.govehicle.repositories.vehiclerepositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Value("${bucketName}")
    private String bucketName;

    private final AmazonS3 s3;

    public BrandService(AmazonS3 s3){
        this.s3 = s3;
    }

    public List<String> createBrand(String brand, MultipartFile image) {

        String filteredBrand = brand.replaceAll("[^a-zA-Z0-9]", "-");
        //getting imagename
        String imageName = image.getOriginalFilename();
        String brandImageName = filteredBrand.concat(imageName.substring(imageName.lastIndexOf(".")));
        System.out.println("this is the brand name: ::::: ->  " + brandImageName);
        String imageUrl = "https://govehicles.s3.ap-south-1.amazonaws.com/";

        try{
            File convertedFile = convertMultiPartToFile(image);
            PutObjectResult putObjectResult = s3.putObject(bucketName, brandImageName, convertedFile);

            List<String> brandList = new ArrayList<>();
            brandList.add(filteredBrand);
            brandList.add(imageUrl+brandImageName);

            return brandList;
        }catch(Exception e){

            List<String> brandList = new ArrayList<>();
            brandList.add(filteredBrand);
            brandList.add(imageUrl+"not_found.jpeg");

            return brandList;
        }




//        // full path
//        String iconFolder =File.separator+"brandicons";
//
//        File file = new File(path+iconFolder);
//        if(!file.exists()){
//            boolean mkdir = file.mkdir();
//        }
//
//        String fullPath = path+iconFolder+File.separator+brandImageName;
//        System.out.println("full path: -> " + fullPath);
//
//        try(InputStream inputStream = image.getInputStream()){
//
//            Files.copy(inputStream, Paths.get(fullPath));
//            System.out.println("image saved at : "+ fullPath );
//            return  iconFolder + File.separator + brandImageName;
//
//        }catch (Exception e){
//            e.printStackTrace();
//            return "error hapend";
//        }


    }


    public List<Brand> getAllBrands() {
         return brandRepository.findAll();
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {

        File convoFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convoFile);

        fos.write(file.getBytes());
        fos.close();
        return convoFile;
    }
}
