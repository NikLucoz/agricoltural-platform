package it.unicam.cs.agricultural_platform.repositories;

import it.unicam.cs.agricultural_platform.models.user.User;
import it.unicam.cs.agricultural_platform.models.user.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);
    List<User> findByUserTypesContaining(UserType userType);
}
