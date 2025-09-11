package it.unicam.cs.agricultural_platform.models.product;

import jakarta.persistence.*;

@Entity
public class ProductInPacket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "packet_id")
    private ProductPacket packet;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    public ProductInPacket() {}

    public ProductInPacket(ProductPacket packet, Product product, int quantity) {
        this.packet = packet;
        this.product = product;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public ProductPacket getPacket() {
        return packet;
    }

    public void setPacket(ProductPacket packet) {
        this.packet = packet;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
