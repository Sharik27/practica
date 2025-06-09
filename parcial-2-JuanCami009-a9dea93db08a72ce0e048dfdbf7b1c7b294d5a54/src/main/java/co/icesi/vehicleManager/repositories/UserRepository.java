package co.icesi.vehicleManager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.icesi.vehicleManager.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);
    
}
