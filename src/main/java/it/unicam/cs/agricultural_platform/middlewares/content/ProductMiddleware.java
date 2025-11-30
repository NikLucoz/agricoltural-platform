package it.unicam.cs.agricultural_platform.middlewares.content;

import it.unicam.cs.agricultural_platform.dto.content.ContentDTO;
import it.unicam.cs.agricultural_platform.dto.content.ProductDTO;
import it.unicam.cs.agricultural_platform.middlewares.Middleware;
import it.unicam.cs.agricultural_platform.models.user.UserType;
import it.unicam.cs.agricultural_platform.services.UserService;

public class ProductMiddleware extends Middleware<ContentDTO> {

    private final UserService userService;

    public ProductMiddleware(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean handle(ContentDTO data) {
        if(data instanceof ProductDTO productDTO) {
            if(!userService.hasUserType(productDTO.getAuthorId(), UserType.PRODUCER)) return false;
            if(productDTO.getProcesses() != null && !productDTO.getProcesses().isBlank()) {
                if(!userService.hasUserType(productDTO.getAuthorId(), UserType.PROCESSOR)) return false;
            }
        }
        return handleNext(data);
    }
}