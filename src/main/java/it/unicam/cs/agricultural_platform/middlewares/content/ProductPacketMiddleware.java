package it.unicam.cs.agricultural_platform.middlewares.content;

import it.unicam.cs.agricultural_platform.dto.content.ContentDTO;
import it.unicam.cs.agricultural_platform.dto.content.ProductPacketDTO;
import it.unicam.cs.agricultural_platform.middlewares.Middleware;
import it.unicam.cs.agricultural_platform.middlewares.MiddlewareValidationContext;
import it.unicam.cs.agricultural_platform.models.user.UserType;
import it.unicam.cs.agricultural_platform.services.UserService;

public class ProductPacketMiddleware extends Middleware<ContentDTO> {

    private final UserService userService;

    public ProductPacketMiddleware(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean handle(ContentDTO data, MiddlewareValidationContext validationContext) {
        if(data instanceof ProductPacketDTO packetDTO) {
            if(packetDTO.getProductsInPacket() == null || packetDTO.getProductsInPacket().isEmpty()) return false;
            if(!userService.hasUserType(data.getAuthorId(), UserType.DISTRIBUTOR)) return false;
        }
        return handleNext(data, validationContext);
    }
}
