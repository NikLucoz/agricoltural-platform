package it.unicam.cs.agricultural_platform.middlewares.market;

import it.unicam.cs.agricultural_platform.dto.user.ItemCartOperationDTO;
import it.unicam.cs.agricultural_platform.middlewares.Middleware;
import it.unicam.cs.agricultural_platform.middlewares.MiddlewareValidationContext;
import it.unicam.cs.agricultural_platform.services.ProductPacketService;
import it.unicam.cs.agricultural_platform.services.ProductService;
import it.unicam.cs.agricultural_platform.services.UserService;

public class ItemCartOperationMiddleware extends Middleware<ItemCartOperationDTO> {

    private final UserService userService;
    private final ProductService productService;
    private final ProductPacketService productPacketService;

    public ItemCartOperationMiddleware(UserService userService, ProductService productService, ProductPacketService productPacketService) {
        this.userService = userService;
        this.productService = productService;
        this.productPacketService = productPacketService;
    }

    @Override
    public boolean handle(ItemCartOperationDTO data, MiddlewareValidationContext validationContext) {
        if(!userService.existsUser(data.getUserId())) return false;
        if(!productService.existsProduct(data.getContentId())) {
            if(!productPacketService.existsPacket(data.getContentId())) return false;
        }
        if(data.getQuantity() < 0) return false;
        return handleNext(data, validationContext);
    }
}
