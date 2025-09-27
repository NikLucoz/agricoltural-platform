package it.unicam.cs.agricultural_platform.models.user.cart;

import it.unicam.cs.agricultural_platform.models.Content;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class UserCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public boolean addToCart(Content content, int quantity){
        if(content == null) return false;
        if(quantity <= 0) return false;
        CartItem selectedCartItem = null;
        for(CartItem cartItem : items){
            if(cartItem.getWrappee().equals(content)){
                selectedCartItem = cartItem;
            }
        }
        if(selectedCartItem != null){
            selectedCartItem.setQuantity(selectedCartItem.getQuantity() + quantity);
            return true;
        }
        selectedCartItem = new CartItem(quantity, content, this);
        items.add(selectedCartItem);
        return true;
    }

    public boolean removeFromCart(Content content, int quantity){
        if(content == null) return false;
        if(quantity <= 0) return false;
        CartItem selectedCartItem = null;
        for(CartItem cartItem : items){
            if(cartItem.getWrappee().equals(content)){
                selectedCartItem = cartItem;
            }
        }
        if(selectedCartItem != null){
            if(selectedCartItem.getQuantity() - quantity <= 0){
                items.remove(selectedCartItem);
                return true;
            }
            selectedCartItem.setQuantity(selectedCartItem.getQuantity() - quantity);
            return true;
        }
        return false;
    }
}
