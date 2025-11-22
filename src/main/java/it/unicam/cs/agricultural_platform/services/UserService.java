package it.unicam.cs.agricultural_platform.services;

import it.unicam.cs.agricultural_platform.repositories.CartItemRepository;
import it.unicam.cs.agricultural_platform.repositories.UserRepository;
import it.unicam.cs.agricultural_platform.models.user.User;
import it.unicam.cs.agricultural_platform.models.user.UserType;
import it.unicam.cs.agricultural_platform.models.user.cart.UserCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    // === GENERIC ===

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(long id) {
        return userRepository.findById(id);
    }

    public UserCart getUserCart(long id) {
        User user = userRepository.findById(id);
        if(user != null) {
            return user.getUserCart();
        }
        return null;
    }

    public boolean existsUser(long id) {
        return userRepository.existsById(id);
    }

    public boolean hasUserType(long id, UserType type) {
        return userRepository.findById(id).hasUserType(type);
    }

    public void changePassword(User user, String newPassword) {
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    public boolean existsUserByUsername(String username) {
        return userRepository.existsByUsername(username);
    }


    // === CRUD ===

    public boolean addUser(User user) {
        try {
            user.createCart();
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteUser(long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateUser(long id, User updatedUser) {
        var user = userRepository.findById(id);
        if(updatedUser == null) return false;
        if (user == null) return false;

        user.setName(updatedUser.getName());
        user.setSurname(updatedUser.getSurname());
        user.setEmail(updatedUser.getEmail());
        user.setCodFis(updatedUser.getCodFis());
        user.setpIva(updatedUser.getpIva());
        user.setUsername(updatedUser.getUsername());
        user.setUserTypes(updatedUser.getUserTypes());

        userRepository.save(user);
        return true;
    }


    // === MANAGEMENT ===

    public boolean setUserType(User user, UserType userType) {
        if(hasUserType(user.getId(), userType)) return false;

        user.addUserType(userType);
        userRepository.save(user);
        return true;
    }

    public boolean removeUserType(User user, UserType userType) {
        if(!hasUserType(user.getId(), userType)) return false;

        user.removeUserType(userType);
        userRepository.save(user);
        return true;
    }
}