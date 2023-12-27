package com.govehicle.controller.vehicles_controllers;

import com.govehicle.entities.vehicles.Brand;
import com.govehicle.entities.vehicles.ImagePaths;
import com.govehicle.entities.vehicles.Specifications;
import com.govehicle.payload.vehicles.Brands;
import com.govehicle.services.vehicles_services.BrandService;
import com.govehicle.services.vehicles_services.ImageService;
import com.govehicle.services.vehicles_services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private ImageService imageService;

    private Specifications specifications;
    private Brand brandDetails = new Brand();
    private String imagepath;
    private String imagepath2;



    @PostMapping("/body")
    public void setSpecs(@RequestBody Specifications specifications) {

        this.specifications = specifications;
        System.out.println(specifications);
    }


    @PostMapping("/images")
    public ResponseEntity<Specifications> SaveVehicle(
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "image2", required = false) MultipartFile image2,
            @RequestParam(value = "brandIcon", required = false) MultipartFile brandIcon,
            @RequestParam("brand") String brand) {

        if (image == null && image2 == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        try {

            try {

                List<String> brandList = this.brandService.createBrand(brand, brandIcon);
                this.brandDetails.setBrandName(brandList.get(0));
                this.brandDetails.setBrandImage(brandList.get(1));
            } catch (Exception e) {

            }


            if (image != null) {
                imagepath = imageService.uploadImage(image);
                System.out.println(imagepath);
            }
            if (image2 != null) {
                imagepath2 = imageService.uploadImage(image2);
                System.out.println(imagepath2);
            }

            List<ImagePaths> imglist = new ArrayList<>();

            if (image != null) {
                ImagePaths imagePath1 = new ImagePaths();
                imagePath1.setPath(imagepath);
                imglist.add(imagePath1);
            }

            if (image2 != null) {
                ImagePaths imagePaths2 = new ImagePaths();
                imagePaths2.setPath(imagepath2);
                imglist.add(imagePaths2);
            }

            this.specifications.setBrand(this.brandDetails);

            if(this.specifications.getBrand() == null){
                this.specifications.setBrand(this.brandDetails);
            }

            this.specifications.setImages(imglist);
            if (this.specifications.getImages() == null) {
                this.specifications.setImages(new ArrayList<>());
            }

            // this.specifications.getImages().addAll(imglist);

            Specifications savedVehicle = vehicleService.saveVehicle(this.specifications);
            this.specifications = null;
            this.brandDetails = new Brand();
            // return new ResponseEntity<>(savedVehicle, HttpStatus.OK);
            System.out.println(savedVehicle);
            return ResponseEntity.ok(savedVehicle);

        } catch (IOException e) {
            return null;
        }
    }


    // ::::::::::::::::: getting all vehicles ::::::::::::::::::: //
    @GetMapping("/getall")
    public ResponseEntity<List<Specifications>> getAllVehicles() {
        List<Specifications> all = vehicleService.getAll();

        if (!all.isEmpty()) return ResponseEntity.ok(all);
        else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    // :::::::::::::::::::: save brands :::::::::::::::::::::::::://


//    @PostMapping("/savebrand")
//    public ResponseEntity<String> saveBrand(
//        @RequestParam(value = "brandImage", required = false) MultipartFile brandImage,
//        @RequestParam("brand") String brand
//    ){
//
//        String savedBrand;
//
//        if(brandImage == null) {
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }
//
//
//        try{
//            if(brandImage != null){
//               //  savedBrand = imageService.createBrand(brandImage, path, brand);
//
//                List<String> brandDetails = imageService.createBrand(brandImage, path, brand);
//
//                // adding data in brands object
//            }
//
//
//            return null;
//
//        }catch(Exception e){
//                e.printStackTrace();
//            return null;
//        }
//
//    }
//

    // ::::::::::::::::::::::::: get brands ::::::::::::::::::::::::::::::::: //
    @GetMapping("/getAllBrands")
    public ResponseEntity<List<Brand>> getBrands() {

        return ResponseEntity.ok(brandService.getAllBrands());






//        List<Brands> allBrandsList = vehicleService.getAllBrands();
//        if (!allBrandsList.isEmpty()) return ResponseEntity.ok(allBrandsList);
//        else return null;
    }


}
