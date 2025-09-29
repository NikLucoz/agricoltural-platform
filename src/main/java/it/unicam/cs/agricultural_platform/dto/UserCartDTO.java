package it.unicam.cs.agricultural_platform.dto;

import it.unicam.cs.agricultural_platform.models.product.Product;
import it.unicam.cs.agricultural_platform.models.product.ProductPacket;
import it.unicam.cs.agricultural_platform.models.user.cart.CartItem;
import it.unicam.cs.agricultural_platform.models.user.cart.UserCart;

import java.util.ArrayList;
import java.util.List;

public class UserCartDTO {


    private long id;
    private long userId;
    private List<Long> productIds  = new ArrayList<>();
    private List<Long> productPacketIds  = new ArrayList<>();


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

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    public List<Long> getProductPacketIds() {
        return productPacketIds;
    }

    public void setProductPacketIds(List<Long> productPacketIds) {
        this.productPacketIds = productPacketIds;
    }


    public static UserCartDTO fromUserCart (UserCart userCart){
        UserCartDTO userCartDTO = new UserCartDTO();
        userCartDTO.setId(userCart.getId());
        userCartDTO.setUserId(userCart.getUser().getId());
        for(CartItem cartItem : userCart.getItems() ){
            if(cartItem.getWrappee() instanceof Product) {
                userCartDTO.productIds.add(((Product) cartItem.getWrappee()).getId());
            }
            if(cartItem.getWrappee() instanceof ProductPacket) {
                userCartDTO.productPacketIds.add(((ProductPacket) cartItem.getWrappee()).getId());
            }
        }
        return userCartDTO;
    }
}
