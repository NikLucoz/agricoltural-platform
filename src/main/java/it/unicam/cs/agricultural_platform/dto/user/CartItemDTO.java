package it.unicam.cs.agricultural_platform.dto.user;

import it.unicam.cs.agricultural_platform.models.user.cart.CartItem;

public class CartItemDTO {
    private long contentID;
    private int quantity;

    public CartItemDTO() {
    }

    public CartItemDTO(int contentID, int quantity) {
        this.contentID = contentID;
        this.quantity = quantity;
    }

    public long getContentID() {
        return contentID;
    }

    public void setContentID(long contentID) {
        this.contentID = contentID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static CartItemDTO fromCartItem(CartItem cartItem) {
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setQuantity(cartItem.getQuantity());
        cartItemDTO.setContentID(cartItem.getWrappee().getId());
        return cartItemDTO;
    }
}
