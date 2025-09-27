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


    public List<ProductInPacket> getProductsInPacket() {
        return productsInPacket;
    }

    public void setProductsInPacket(List<ProductInPacket> productsInPacket) {
        this.productsInPacket.clear();
        if (productsInPacket != null) {
            for (ProductInPacket pip : productsInPacket) {
                pip.setPacket(this);
                this.productsInPacket.add(pip);
            }
        }
    }
}
