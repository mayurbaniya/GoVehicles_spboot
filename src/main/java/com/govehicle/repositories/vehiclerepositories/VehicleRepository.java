package com.govehicle.repositories.vehiclerepositories;

import com.govehicle.entities.vehicles.Specifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Specifications, Integer> {

    List<Specifications> findById(int id);

}
