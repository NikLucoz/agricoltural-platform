package it.unicam.cs.agricultural_platform.facades;

import it.unicam.cs.agricultural_platform.models.user.User;
import it.unicam.cs.agricultural_platform.services.MarketService;
import it.unicam.cs.agricultural_platform.services.ProductPacketService;
import it.unicam.cs.agricultural_platform.services.ProductService;
import it.unicam.cs.agricultural_platform.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MarketFacade {

    @Autowired
    private MarketService marketService;

    @Autowired
    private UserService userService;

    public boolean addItemToUserCart(long userId, long id, int quantity){
        if(!userService.existsUser(id)) return false;
        var user = userService.getUserById(id);
        return marketService.addItemToUserCart(user, id, quantity);
    }

    public boolean removeItemFromUserCart(long userId, long id, int quantity){
        if(!userService.existsUser(id)) return false;
        var user = userService.getUserById(id);
        return marketService.removeItemFromUserCart(user, id, quantity);
    }

    public boolean updateContentMarketData(long id, double price, int stockQuantity){
        return marketService.updateContentMarketData(id, price, stockQuantity);
    }
}
