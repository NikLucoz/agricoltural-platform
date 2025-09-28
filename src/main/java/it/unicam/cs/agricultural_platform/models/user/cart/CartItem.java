package it.unicam.cs.agricultural_platform.models.user.cart;

import it.unicam.cs.agricultural_platform.models.Content;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "wrappee_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Content wrappee;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private UserCart cart;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Content getWrappee() {
        return wrappee;
    }

    public void setWrappee(Content wrappee) {
        this.wrappee = wrappee;
    }

    public Double getPrice() {
        return wrappee.getPrice() * quantity;
    }

    public CartItem(int quantity, Content wrappee, UserCart cart) {
        this.quantity = quantity;
        this.wrappee = wrappee;
        this.cart = cart;
    }
    public CartItem(){}
}
