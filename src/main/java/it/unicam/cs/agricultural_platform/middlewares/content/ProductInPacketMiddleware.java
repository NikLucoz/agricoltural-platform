package it.unicam.cs.agricultural_platform.middlewares.content;

import it.unicam.cs.agricultural_platform.dto.content.ContentDTO;
import it.unicam.cs.agricultural_platform.dto.content.ProductInPacketDTO;
import it.unicam.cs.agricultural_platform.dto.content.ProductPacketDTO;
import it.unicam.cs.agricultural_platform.middlewares.Middleware;
import it.unicam.cs.agricultural_platform.middlewares.MiddlewareValidationContext;
import it.unicam.cs.agricultural_platform.services.ProductPacketService;
import it.unicam.cs.agricultural_platform.services.ProductService;

public class ProductInPacketMiddleware extends Middleware<ContentDTO> {

    private final ProductService productService;
    private final ProductPacketService productPacketService;

    public ProductInPacketMiddleware(ProductPacketService productPacketService, ProductService productService) {
        this.productPacketService = productPacketService;
        this.productService = productService;
    }

    @Override
    public boolean handle(ContentDTO data, MiddlewareValidationContext validationContext) {
        if (data instanceof ProductPacketDTO productPacketDTO) {
            for(ProductInPacketDTO productInPacketDTO : productPacketDTO.getProductsInPacket()) {
                if(validationContext.isUpdate()) {
                    if(productInPacketDTO.getPacketId() == 0) return false;
                }

                if(productInPacketDTO.getProductId() == 0) return false;
                if(productInPacketDTO.getQuantity() <= 0) return false;

                if(!productService.existsProduct(productInPacketDTO.getProductId())) return false;
            }
        }
        return handleNext(data, validationContext);
    }
}
