package co.icesi.vehicleManager.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import co.icesi.vehicleManager.dtos.VehicleDto;
import co.icesi.vehicleManager.model.User;
import co.icesi.vehicleManager.model.Vehicle;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    @Mappings({
            @Mapping(target = "ownerId", source = "owner", qualifiedByName = "ownerId"),
    })
    VehicleDto vehicleToVehicleDto(Vehicle vehicle);

    @Mappings({
            @Mapping(target = "owner", source = "ownerId", qualifiedByName = "ownerObj"),
    })
    Vehicle vehicleDtoToVehicle(VehicleDto vehicle);

    @Named("ownerId")
    public default Long mapOwnerIdToLong(User user) {
        if (user == null) {
            return null;
        }
        return user.getId();
    }

    @Named("ownerObj")
    public default User owner(Long userId) {
        User user = new User();
        user.setId(userId);
        return user;
    }
}
