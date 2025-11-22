package it.unicam.cs.agricultural_platform.facades;

import it.unicam.cs.agricultural_platform.dto.content.UpdateMarketDataDTO;
import it.unicam.cs.agricultural_platform.dto.user.ItemCartOperationDTO;
import it.unicam.cs.agricultural_platform.middlewares.Middleware;
import it.unicam.cs.agricultural_platform.middlewares.market.ItemCartOperationMiddleware;
import it.unicam.cs.agricultural_platform.middlewares.market.UpdateMarketDataMiddleware;
import it.unicam.cs.agricultural_platform.middlewares.user.UserDataMiddleware;
import it.unicam.cs.agricultural_platform.middlewares.user.UserPasswordMiddleware;
import it.unicam.cs.agricultural_platform.models.user.User;
import it.unicam.cs.agricultural_platform.services.MarketService;
import it.unicam.cs.agricultural_platform.services.ProductPacketService;
import it.unicam.cs.agricultural_platform.services.ProductService;
import it.unicam.cs.agricultural_platform.services.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MarketFacade {

    @Autowired
    private MarketService marketService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductPacketService productPacketService;


    private Middleware<ItemCartOperationDTO> itemCartOperationMiddleware;
    private Middleware<UpdateMarketDataDTO> updateMarketDataMiddleware;

    @PostConstruct
    private void init(){
        itemCartOperationMiddleware = Middleware.link(new ItemCartOperationMiddleware(userService, productService, productPacketService));
        updateMarketDataMiddleware = Middleware.link(new UpdateMarketDataMiddleware(productService, productPacketService));
    }

    public boolean addItemToUserCart(ItemCartOperationDTO itemCartOperationDTO){
        if(!itemCartOperationMiddleware.handle(itemCartOperationDTO)) return false;
        var user = userService.getUserById(itemCartOperationDTO.getUserId());
        return marketService.addItemToUserCart(user, itemCartOperationDTO.getContentId(), itemCartOperationDTO.getQuantity());
    }

    public boolean removeItemFromUserCart(ItemCartOperationDTO itemCartOperationDTO){
        if(!itemCartOperationMiddleware.handle(itemCartOperationDTO)) return false;
        var user = userService.getUserById(itemCartOperationDTO.getUserId());
        return marketService.removeItemFromUserCart(user, itemCartOperationDTO.getContentId(), itemCartOperationDTO.getQuantity());
    }

    public boolean updateContentMarketData(UpdateMarketDataDTO updateDTO){
        if(!updateMarketDataMiddleware.handle(updateDTO)) return false;
        return marketService.updateContentMarketData(updateDTO.getContentId(), updateDTO.getPrice(), updateDTO.getStockQuantity());
    }
}
