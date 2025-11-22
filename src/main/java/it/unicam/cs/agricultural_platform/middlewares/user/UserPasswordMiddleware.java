package it.unicam.cs.agricultural_platform.middlewares.user;

import it.unicam.cs.agricultural_platform.dto.user.UserDTO;
import it.unicam.cs.agricultural_platform.middlewares.Middleware;
import it.unicam.cs.agricultural_platform.models.user.User;
import it.unicam.cs.agricultural_platform.services.UserService;

public class UserPasswordMiddleware extends Middleware<UserDTO> {
    private final UserService userService;

    public UserPasswordMiddleware(UserService userService) {
        this.userService = userService;
    }


    @Override
    public boolean handle(UserDTO data) {
        if (data.getPassword() == null && !data.getPassword().isBlank()) return false;
        if (data.getPassword().length() < 8) return false;
        return handleNext(data);
    }
}
