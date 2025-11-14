package it.unicam.cs.agricultural_platform.middlewares.user;

import it.unicam.cs.agricultural_platform.middlewares.Middleware;
import it.unicam.cs.agricultural_platform.models.user.User;

public class UserDataMiddleware extends Middleware<User> {
    @Override
    public boolean handle(User data) {
        if (data.getName() == null && !data.getName().isBlank()) return false;
        if (data.getSurname() == null && !data.getSurname().isBlank()) return false;
        if (data.getUsername() == null && !data.getUsername().isBlank()) return false;
        if (data.getEmail() == null && !data.getEmail().isBlank()) return false;
        return handleNext(data);
    }
}
