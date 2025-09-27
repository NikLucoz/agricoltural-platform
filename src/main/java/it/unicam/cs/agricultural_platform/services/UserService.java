package it.unicam.cs.agricultural_platform.services;

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
}
