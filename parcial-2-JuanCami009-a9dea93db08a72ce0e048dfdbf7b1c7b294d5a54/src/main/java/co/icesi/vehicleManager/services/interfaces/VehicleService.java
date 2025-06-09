package co.icesi.vehicleManager.services.interfaces;

import java.util.List;

import co.icesi.vehicleManager.model.Vehicle;

public interface VehicleService {
    Vehicle createVehicle(Vehicle Vehicle);
    Vehicle updateVehicle(Vehicle Vehicle);
    void deleteVehicle(long VehicleId);
    void assignVehicle(long VehicleId, long userId);
    void unassignVehicle(long VehicleId, long userId);
    Vehicle getVehicleById(long VehicleId);
    List<Vehicle> getAllVehicle();
}
