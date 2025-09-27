package it.unicam.cs.agricultural_platform.facades;

import it.unicam.cs.agricultural_platform.services.UserService;
import it.unicam.cs.agricultural_platform.models.user.User;
import it.unicam.cs.agricultural_platform.models.user.cart.UserCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFacade {
    @Autowired
    private UserService userService;

    public List<User> getUsers() {
        return userService.getUsers();
    }

    public User getUser(long id) {
        return userService.getUserById(id);
    }

    public UserCart getUserCart(long id) {
        return userService.getUserCart(id);
    }
}
