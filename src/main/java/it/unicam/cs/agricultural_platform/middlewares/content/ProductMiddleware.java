package it.unicam.cs.agricultural_platform.middlewares.content;

import it.unicam.cs.agricultural_platform.dto.content.ContentDTO;
import it.unicam.cs.agricultural_platform.dto.content.ProductDTO;
import it.unicam.cs.agricultural_platform.middlewares.Middleware;
import it.unicam.cs.agricultural_platform.middlewares.MiddlewareValidationContext;
import it.unicam.cs.agricultural_platform.models.user.UserType;
import it.unicam.cs.agricultural_platform.services.ProductService;
import it.unicam.cs.agricultural_platform.services.UserService;

public class ProductMiddleware extends Middleware<ContentDTO> {

    private final UserService userService;
    private final ProductService productService;

    public ProductMiddleware(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public boolean handle(ContentDTO data, MiddlewareValidationContext validationContext) {
        if(data instanceof ProductDTO productDTO) {
            var authorId = data.getAuthorId();
            if(validationContext.isUpdate()) {
                var originalProductId = validationContext.getOptionalId();
                if(!productService.existsProduct(originalProductId)) return false;
                var product = productService.getProduct(originalProductId);
                authorId = product.getAuthor().getId();
            }

            if(!userService.hasUserType(authorId, UserType.PRODUCER)) return false;
            if(productDTO.getProcesses() != null && !productDTO.getProcesses().isBlank()) {
                if(!userService.hasUserType(authorId, UserType.PROCESSOR)) return false;
            }
        }
        return handleNext(data, validationContext);
    }
}