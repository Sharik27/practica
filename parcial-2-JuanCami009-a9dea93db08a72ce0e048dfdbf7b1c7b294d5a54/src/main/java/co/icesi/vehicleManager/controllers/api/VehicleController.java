package co.icesi.vehicleManager.controllers.api;

import org.springframework.http.ResponseEntity;

import co.icesi.vehicleManager.dtos.VehicleDto;

public interface VehicleController {

    public ResponseEntity<?> findAllVehicle();

    public ResponseEntity<?> addVehicle(VehicleDto dto);

    public ResponseEntity<?> updateVehicle(VehicleDto dto);

    public ResponseEntity<?> deleteVehicle(long id);

    public ResponseEntity<?> findById(long id);

}
