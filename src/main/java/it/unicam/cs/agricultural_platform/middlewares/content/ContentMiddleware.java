package it.unicam.cs.agricultural_platform.middlewares.content;

import it.unicam.cs.agricultural_platform.dto.content.ContentDTO;
import it.unicam.cs.agricultural_platform.middlewares.Middleware;
import it.unicam.cs.agricultural_platform.middlewares.MiddlewareValidationContext;
import it.unicam.cs.agricultural_platform.services.UserService;

public class ContentMiddleware extends Middleware<ContentDTO> {
    private final UserService userService;

    public ContentMiddleware(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean handle(ContentDTO data, MiddlewareValidationContext validationContext) {
        if(data.getName() == null || data.getName().isBlank()) return false;
        if(data.getDescription() == null || data.getDescription().isBlank()) return false;

        if(data.getAuthorId() == null || data.getAuthorId() == 0) return false;
        if(!userService.existsUser(data.getAuthorId())) return false;

        if(data.getPrice() < 0) return false;
        if(data.getStockQuantity() < 0) return false;
        return handleNext(data, validationContext);
    }
}