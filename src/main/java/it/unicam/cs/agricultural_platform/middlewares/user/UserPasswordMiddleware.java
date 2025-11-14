package it.unicam.cs.agricultural_platform.middlewares.user;

import it.unicam.cs.agricultural_platform.middlewares.Middleware;
import it.unicam.cs.agricultural_platform.models.user.User;

public class UserPasswordMiddleware extends Middleware<User> {

    @Override
    public boolean handle(User data) {
        if (data.getPassword() == null && !data.getPassword().isBlank()) return false;
        if (data.getPassword().length() < 8) return false;
        return handleNext(data);
    }
}
