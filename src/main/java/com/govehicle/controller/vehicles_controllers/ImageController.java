package com.govehicle.controller.vehicles_controllers;
import com.govehicle.entities.vehicles.Specifications;
import com.govehicle.payload.vehicles.VehicleResponse;
import com.govehicle.repositories.vehiclerepositories.VehicleRepository;
import com.govehicle.services.vehicles_services.ImageService;
import com.govehicle.services.vehicles_services.S3Service;
import com.govehicle.services.vehicles_services.VehicleService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/file")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleService vehicleService;

    @Value("${project.image}")
    private String path;


    @PostMapping("/upload")
    public ResponseEntity<VehicleResponse> imageUpload(
            @RequestParam("image")MultipartFile image,
            @RequestParam("subpath") String brand
            ) {

        String fileName = null;
        try {
            fileName = this.imageService.uploadImage(image);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new VehicleResponse(null, "something went wrong on server"), HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return new ResponseEntity<>(new VehicleResponse(fileName,"Image uploaded successfully"), HttpStatus.OK);

    }

    @GetMapping("/getvehicle/{id}")
    public ResponseEntity<Specifications> getVehicle(@PathVariable int id){

        List<Specifications> foundVehicles = vehicleRepository.findById(id);

        return new ResponseEntity<>(foundVehicles.get(0), HttpStatus.OK);
    }

    // :::::::::::::::::::::::: test method to serve images ::::::::::::::::::::::::::::::::::::

    @GetMapping(value = "/images/{imgName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void serveImages(
            @PathVariable String imgName,
            HttpServletResponse response

    ) throws IOException {
        InputStream resource = this.vehicleService.getResources("images/bajaj", imgName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }


    @GetMapping("/uploadtoaws")
    public String saveImage(
            @RequestParam("image") MultipartFile file
    ){
        String s = s3Service.uploadImage(file);
        String imageUrl = "https://govehicles.s3.ap-south-1.amazonaws.com/"+s;
        return imageUrl;
    }

}
