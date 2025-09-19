package it.unicam.cs.agricultural_platform.models.user.cart;

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
}
