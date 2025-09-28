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

    public boolean addUser(User user) {
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteUser(long id) {
        if (userRepository.existsById(id)) {
            try {
                userRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public boolean updateUser(long id, User updatedUser) {
        var user = userRepository.findById(id);
        if(updatedUser == null) return false;
        if (user == null) return false;

        if (updatedUser.getName() != null && !updatedUser.getName().isBlank()) {
            user.setName(updatedUser.getName());
        }

        if (updatedUser.getSurname() != null && !updatedUser.getSurname().isBlank()) {
            user.setSurname(updatedUser.getSurname());
        }

        if (updatedUser.getEmail() != null && !updatedUser.getEmail().isBlank()) {
            user.setEmail(updatedUser.getEmail());
        }

        if (updatedUser.getCodFis() != null && !updatedUser.getCodFis().isBlank()) {
            user.setCodFis(updatedUser.getCodFis());
        }

        if (updatedUser.getpIva() != null && !updatedUser.getpIva().isBlank()) {
            user.setpIva(updatedUser.getpIva());
        }

        if (updatedUser.getUserTypes() != null && !updatedUser.getUserTypes().isEmpty()) {
            user.setUserTypes(updatedUser.getUserTypes());
        }

        userRepository.save(user);
        return true;
    }

    public boolean changePassword(User user, String oldPassword, String newPassword) {
        if(oldPassword.equals(newPassword)) return false;
        if(!oldPassword.isBlank() && newPassword != null && !newPassword.isBlank()) {
            if(user.getPassword().equals(oldPassword)) {
                user.setPassword(newPassword);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    public boolean existsUserByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
