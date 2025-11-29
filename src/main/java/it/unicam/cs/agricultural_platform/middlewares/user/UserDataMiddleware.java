package it.unicam.cs.agricultural_platform.middlewares.user;

import it.unicam.cs.agricultural_platform.dto.user.UserDTO;
import it.unicam.cs.agricultural_platform.middlewares.Middleware;
import it.unicam.cs.agricultural_platform.models.user.User;
import it.unicam.cs.agricultural_platform.services.UserService;

public class UserDataMiddleware extends Middleware<UserDTO> {
    private final UserService userService;

    public UserDataMiddleware(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean handle(UserDTO data) {
        if (data.getName() == null && !data.getName().isBlank()) return false;
        if (data.getSurname() == null && !data.getSurname().isBlank()) return false;
        if (data.getUsername() == null && !data.getUsername().isBlank()) return false;
        if (data.getEmail() == null && !data.getEmail().isBlank()) return false;
        return handleNext(data);
    }
}
