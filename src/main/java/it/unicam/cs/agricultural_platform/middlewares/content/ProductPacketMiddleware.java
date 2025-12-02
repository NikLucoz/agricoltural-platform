package it.unicam.cs.agricultural_platform.middlewares.content;

import it.unicam.cs.agricultural_platform.dto.content.ContentDTO;
import it.unicam.cs.agricultural_platform.dto.content.ProductPacketDTO;
import it.unicam.cs.agricultural_platform.middlewares.Middleware;
import it.unicam.cs.agricultural_platform.middlewares.MiddlewareValidationContext;
import it.unicam.cs.agricultural_platform.models.user.UserType;
import it.unicam.cs.agricultural_platform.services.ProductPacketService;
import it.unicam.cs.agricultural_platform.services.UserService;

public class ProductPacketMiddleware extends Middleware<ContentDTO> {

    private final UserService userService;

    private final ProductPacketService productPacketService;

    public ProductPacketMiddleware(UserService userService, ProductPacketService productPacketService) {
        this.userService = userService;
        this.productPacketService = productPacketService;
    }

    @Override
    public boolean handle(ContentDTO data, MiddlewareValidationContext validationContext) {
        if(data instanceof ProductPacketDTO packetDTO) {
            var authorId = data.getAuthorId();
            if(validationContext.isUpdate()) {
                var originalProductId = validationContext.getOptionalId();
                if(!productPacketService.existsPacket(originalProductId)) return false;
                var productPacket = productPacketService.getProductPacket(originalProductId);
                authorId = productPacket.getAuthor().getId();
            }

            if(packetDTO.getProductsInPacket() == null || packetDTO.getProductsInPacket().isEmpty()) return false;
            if(!userService.hasUserType(authorId, UserType.DISTRIBUTOR)) return false;
        }
        return handleNext(data, validationContext);
    }
}
