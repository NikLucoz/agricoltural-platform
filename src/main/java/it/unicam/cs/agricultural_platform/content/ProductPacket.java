package it.unicam.cs.agricultural_platform.content;

import java.util.ArrayList;
import java.util.List;

public class ProductPacket extends Content{

    private String name;
    private String description;
    private List<Product> products;

    public ProductPacket(long id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
        this.products = new ArrayList<>();
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

    public List<Product> getProducts() {return this.products;}

    public boolean addProduct(Product product){
        if(this.products.contains(product)){
            System.out.println("Prodotto già presente");
            return false;
        }
        else {
            this.products.add(product);
            return true;
        }
    }

    public boolean removeProduct(Product product){
        if(this.products.contains(product)){
            this.products.remove(product);
            return true;
        }
        else {
            System.out.println("Nessun prodotto da rimuovere");
            return false;
        }
    }
}
