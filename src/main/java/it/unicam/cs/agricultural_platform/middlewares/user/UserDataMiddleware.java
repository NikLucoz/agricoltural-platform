package it.unicam.cs.agricultural_platform.middlewares.user;

import it.unicam.cs.agricultural_platform.dto.user.UserDTO;
import it.unicam.cs.agricultural_platform.middlewares.Middleware;
import it.unicam.cs.agricultural_platform.middlewares.MiddlewareValidationContext;
import it.unicam.cs.agricultural_platform.services.UserService;

public class UserDataMiddleware extends Middleware<UserDTO> {
    private final UserService userService;

    public UserDataMiddleware(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean handle(UserDTO data, MiddlewareValidationContext validationContext) {
        if (data.getName() == null && !data.getName().isBlank()) return false;
        if (data.getSurname() == null && !data.getSurname().isBlank()) return false;
        if (data.getUsername() == null && !data.getUsername().isBlank()) return false;
        if (data.getEmail() == null && !data.getEmail().isBlank()) return false;

        if (validationContext.isCreate()) {
            if(userService.existsUserByUsername(data.getUsername())) return false;
        }

        return handleNext(data, validationContext);
    }
}
