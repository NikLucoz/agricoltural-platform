package it.unicam.cs.agricultural_platform.models.user.cart;

import it.unicam.cs.agricultural_platform.models.Content;
import jakarta.persistence.*;

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
    private Content wrappee;

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
}
