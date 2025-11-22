package it.unicam.cs.agricultural_platform.middlewares.market;

import it.unicam.cs.agricultural_platform.dto.content.UpdateMarketDataDTO;
import it.unicam.cs.agricultural_platform.middlewares.Middleware;
import it.unicam.cs.agricultural_platform.services.ProductPacketService;
import it.unicam.cs.agricultural_platform.services.ProductService;
import it.unicam.cs.agricultural_platform.services.UserService;

public class UpdateMarketDataMiddleware extends Middleware<UpdateMarketDataDTO> {
    private final ProductService productService;
    private final ProductPacketService productPacketService;

    public UpdateMarketDataMiddleware(ProductService productService, ProductPacketService productPacketService) {
        this.productService = productService;
        this.productPacketService = productPacketService;
    }

    @Override
    public boolean handle(UpdateMarketDataDTO data) {
        if(!productService.existsProduct(data.getContentId())) {
            if(!productPacketService.existsPacket(data.getContentId())) return false;
        }

        if(data.getPrice() < 0) return false;
        if(data.getStockQuantity() < 0) return false;

        return handleNext(data);
    }
}
