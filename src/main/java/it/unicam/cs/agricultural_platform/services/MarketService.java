package it.unicam.cs.agricultural_platform.services;

import it.unicam.cs.agricultural_platform.models.Content;
import it.unicam.cs.agricultural_platform.models.user.User;
import it.unicam.cs.agricultural_platform.models.user.cart.UserCart;
import it.unicam.cs.agricultural_platform.repositories.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarketService {

    @Autowired
    private ContentRepository<Content> contentRepository;

    public boolean addItemToUserCart(User user, long id, int quantity){
        UserCart userCart = user.getUserCart();
        if(userCart == null) return false;
        if(!contentRepository.existsById(id)) return false;
        var content = contentRepository.findById(id);
        if(content.getStockQuantity() <= 0) return false;
        return userCart.addToCart(content, quantity);
    }

    public boolean removeItemFromUserCart(User user, long id, int quantity){
        UserCart userCart = user.getUserCart();
        if(userCart == null) return false;
        if(!contentRepository.existsById(id)) return false;
        var content = contentRepository.findById(id);
        if(content.getStockQuantity() <= 0) return false;
        return userCart.removeFromCart(content, quantity);
    }

    public boolean updateContentMarketData(long id, double price, int stockQuantity){
        if(!contentRepository.existsById(id)) return false;
        var content = contentRepository.findById(id);
        if (price < 0 || stockQuantity < 0) return false;
        content.setPrice(price);
        content.setStockQuantity(stockQuantity);
        contentRepository.save(content);
        return true;
    }

}
