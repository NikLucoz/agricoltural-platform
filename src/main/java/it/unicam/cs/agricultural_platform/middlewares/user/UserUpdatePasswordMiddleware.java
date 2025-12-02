package it.unicam.cs.agricultural_platform.middlewares.user;

import it.unicam.cs.agricultural_platform.dto.content.PasswordChangeRequestDTO;
import it.unicam.cs.agricultural_platform.middlewares.Middleware;
import it.unicam.cs.agricultural_platform.middlewares.MiddlewareValidationContext;
import it.unicam.cs.agricultural_platform.services.UserService;

public class UserUpdatePasswordMiddleware extends Middleware<PasswordChangeRequestDTO> {

    private final UserService userService;

    public UserUpdatePasswordMiddleware(UserService userService){
        this.userService = userService;
    }

    public boolean handle(PasswordChangeRequestDTO data, MiddlewareValidationContext validationContext){
        if(!userService.existsUser(data.getUserId())) return false;
        if(data.getOldPassword().isBlank() || data.getOldPassword() == null) return false;
        if(data.getNewPassword().isBlank() || data.getNewPassword() == null) return false;
        if (data.getNewPassword().length() < 8) return false;

        var user = userService.getUserById(data.getUserId());
        if(!user.getPassword().equals(data.getOldPassword())) return false;
        if(data.getOldPassword().equals(data.getNewPassword())) return false;

        return handleNext(data, validationContext);
    }
}
