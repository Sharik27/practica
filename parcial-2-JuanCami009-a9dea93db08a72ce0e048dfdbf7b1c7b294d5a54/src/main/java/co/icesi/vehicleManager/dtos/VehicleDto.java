package co.icesi.vehicleManager.dtos;

import lombok.Data;

@Data
public class VehicleDto {
    private Long id, ownerId;

    private String licensePlate, model, make, color;
    private Integer yearRelease;


}
