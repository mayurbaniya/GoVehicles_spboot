package com.govehicle.services.vehicles_services;

import com.govehicle.entities.vehicles.Specifications;
import com.govehicle.payload.vehicles.Brands;
import com.govehicle.repositories.vehiclerepositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ImageService imageService;

    // :::::::::::::::::   saving vehicle   ::::::::::::::::: //
    public Specifications saveVehicle(Specifications specifications){

        return vehicleRepository.save(specifications);
    }


    // :::::::::::::::::  fetching all vehicles :::::::::::::::: //
    public List<Specifications> getAll(){
        return vehicleRepository.findAll();
    }





    // ::::::::::::::::: get all brand names ::::::::::::::::::: //
    private static final String DIRECTORY= "images/";

    public List<Brands> getAllBrands(){




        return null;

//        File imagesDirectory = new File(DIRECTORY);
//
//        if(imagesDirectory.exists() && imagesDirectory.isDirectory()){
//            List<String> brandNames = Arrays.stream(imagesDirectory.listFiles(File::isDirectory))
//                    .map(File::getName)
//                    .filter(directoryName -> !directoryName.contains("brandicons"))
//                    .collect(Collectors.toList());
//
//
//            // here...
//
//            String iconPath = Paths.get(DIRECTORY+ File.separator + "brandicons/").toString();
//            System.out.println("icon path: -> " + iconPath + "<- path");
//
//            return brandNames.stream()
//                    .filter(brandName -> new File(iconPath, brandName + ".png").exists())
//                    .map(brandName -> {
//                        File imageFile = new File(iconPath, brandName + ".png");
//                        try {
//                            InputStream inputStream = new FileInputStream(imageFile);
//                            return new Brands(inputStream, brandName);
//                        } catch (Exception e) {
//                            e.printStackTrace();  // Handle the exception as needed
//                            return null; // or throw an exception
//                        }
//                    })
//                    .collect(Collectors.toList());
//
//
//
////            return null;
//
//        }else{
//            return null;
//        }

    }


    public InputStream getResources(String path, String fileName) throws FileNotFoundException {

        String fullPath = path + File.separator + fileName;
        InputStream is = new FileInputStream(fullPath);
        return  is;
    }



}
