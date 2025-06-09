package co.icesi.vehicleManager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.icesi.vehicleManager.controllers.api.VehicleController;
import co.icesi.vehicleManager.dtos.VehicleDto;
import co.icesi.vehicleManager.mappers.VehicleMapper;
import co.icesi.vehicleManager.model.Vehicle;
import co.icesi.vehicleManager.services.interfaces.VehicleService;

@RestController
@RequestMapping("/vehicles")
public class VehicleControllerImpl implements VehicleController{
    
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleMapper vehicleMapper;


    @Override
    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_VEHICLE')")
    public ResponseEntity<?> findAllVehicle() {
        return ResponseEntity.ok().body(vehicleService.getAllVehicle()
                                        .stream()
                                        .map(vehicleMapper::vehicleToVehicleDto)
                                        .toList());                   
    }

    @Override
    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_VEHICLE')")
    public ResponseEntity<?> addVehicle(@RequestBody VehicleDto dto) {
       Vehicle saved = vehicleService.createVehicle(vehicleMapper.vehicleDtoToVehicle(dto));
       return ResponseEntity.status(201).body(vehicleMapper.vehicleToVehicleDto(saved));
    }

    @Override
    @PutMapping
    @PreAuthorize("hasAuthority('UPDATE_VEHICLE')")
    public ResponseEntity<?> updateVehicle(@RequestBody VehicleDto dto) {
      Vehicle saved = vehicleService.updateVehicle(vehicleMapper.vehicleDtoToVehicle(dto));

      return ResponseEntity.ok(vehicleMapper.vehicleToVehicleDto(saved));
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_VEHICLE')")
    public ResponseEntity<?> deleteVehicle(@PathVariable long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.status(200).body(null);
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VIEW_VEHICLE')")
    public ResponseEntity<?> findById(@PathVariable long id) {

        Vehicle vehicle = vehicleService.getVehicleById(id);

        if(vehicle == null){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(vehicleMapper.vehicleToVehicleDto(vehicle));
        }

    }
    
}
