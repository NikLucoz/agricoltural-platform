package it.unicam.cs.agricultural_platform.models.product;

import it.unicam.cs.agricultural_platform.models.Content;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ProductPacket extends Content {
    private String name;
    private String description;

    @OneToMany(mappedBy = "packet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductInPacket> productsInPacket = new ArrayList<>();

    public ProductPacket(long id, String name, String description) {
        this.name = name;
        this.description = description;
        this.productsInPacket = new ArrayList<>();
    }

    public ProductPacket() {

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean addProduct(Product product, int quantity) {
        if(product == null) return false;
        if(quantity <= 0) return false;

        ProductInPacket pip = new ProductInPacket(this, product, quantity);
        productsInPacket.add(pip);
        return true;
    }

    public boolean removeProduct(Product product) {
        if(product == null) return false;
        if(productsInPacket.isEmpty()) return false;

        ProductInPacket pip = productsInPacket.stream().filter(p -> p.getProduct() == product).findFirst().orElse(null);
        if(pip == null) return false;

        if(pip.getQuantity() - 1 <= 0) {
            productsInPacket.remove(pip);
            return true;
        }

        pip.setQuantity(pip.getQuantity() - 1);
        return true;
    }
}
