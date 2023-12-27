package com.govehicle.repositories.vehiclerepositories;

import com.govehicle.entities.vehicles.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

}
