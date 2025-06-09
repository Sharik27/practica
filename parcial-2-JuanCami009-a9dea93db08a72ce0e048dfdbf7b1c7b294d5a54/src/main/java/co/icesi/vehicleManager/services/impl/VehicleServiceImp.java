package co.icesi.vehicleManager.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.icesi.vehicleManager.model.Vehicle;
import co.icesi.vehicleManager.repositories.VehicleRepository;
import co.icesi.vehicleManager.services.interfaces.VehicleService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class VehicleServiceImp implements VehicleService{

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle createVehicle(Vehicle Vehicle) {
       return vehicleRepository.save(Vehicle);
    }

    @Override
    public Vehicle updateVehicle(Vehicle Vehicle) {
        Vehicle v = getVehicleById(Vehicle.getId());
        v.setColor(Vehicle.getColor());
        v.setLicensePlate(Vehicle.getLicensePlate());
        v.setModel(Vehicle.getModel());
        v.setMake(Vehicle.getMake());
        v.setYearRelease(Vehicle.getYearRelease());
        v.setOwner(Vehicle.getOwner());

        return vehicleRepository.save(v);
    }

    @Override
    public void deleteVehicle(long VehicleId) {
        vehicleRepository.deleteById(VehicleId);
    }

    @Override
    public void assignVehicle(long VehicleId, long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assignVehicle'");
    }

    @Override
    public void unassignVehicle(long VehicleId, long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'unassignVehicle'");
    }

    @Override
    public Vehicle getVehicleById(long VehicleId) {
       return vehicleRepository.findById(VehicleId)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with ID: " + VehicleId));
    }

    @Override
    public List<Vehicle> getAllVehicle() {
        return vehicleRepository.findAll();
    }
    
}
