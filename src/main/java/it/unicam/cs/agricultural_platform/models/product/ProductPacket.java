package it.unicam.cs.agricultural_platform.models.product;

import it.unicam.cs.agricultural_platform.models.Content;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ProductPacket extends Content {

    @OneToMany(mappedBy = "packet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductInPacket> productsInPacket = new ArrayList<>();

    public ProductPacket() {

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

    public List<ProductInPacket> getProductsInPacket() {
        return productsInPacket;
    }
}
