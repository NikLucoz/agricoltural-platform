package it.unicam.cs.agricultural_platform.middlewares.user;

import it.unicam.cs.agricultural_platform.dto.user.UserDTO;
import it.unicam.cs.agricultural_platform.middlewares.Middleware;
import it.unicam.cs.agricultural_platform.services.UserService;

public class UserCreateMiddleware extends Middleware<UserDTO> {
    private final UserService userService;

    public UserCreateMiddleware(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean handle(UserDTO data) {
        if(userService.existsUserByUsername(data.getUsername())) return false;
        return handleNext(data);
    }
}
