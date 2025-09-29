package it.unicam.cs.agricultural_platform.dto.user;

import it.unicam.cs.agricultural_platform.models.user.cart.CartItem;
import it.unicam.cs.agricultural_platform.models.user.cart.UserCart;

import java.util.ArrayList;
import java.util.List;

public class UserCartDTO {
    private long id;
    private long userId;
    private List<CartItemDTO> cartItems = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<CartItemDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDTO> cartItems) {
        this.cartItems = cartItems;
    }

    public static UserCartDTO fromUserCart (UserCart userCart){
        UserCartDTO userCartDTO = new UserCartDTO();
        userCartDTO.setId(userCart.getId());
        userCartDTO.setUserId(userCart.getUser().getId());
        for(CartItem cartItem : userCart.getItems() ){
            userCartDTO.cartItems.add(CartItemDTO.fromCartItem(cartItem));
        }

        return userCartDTO;
    }
}
