package it.unicam.cs.agricultural_platform.services;

import it.unicam.cs.agricultural_platform.models.product.Product;
import it.unicam.cs.agricultural_platform.models.product.ProductInPacket;
import it.unicam.cs.agricultural_platform.models.product.ProductPacket;
import it.unicam.cs.agricultural_platform.models.user.User;
import it.unicam.cs.agricultural_platform.repositories.ProductPacketRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductPacketService {

    @Autowired
    private ProductPacketRepository productPacketRepository;

    public List<ProductPacket> getProductPackets(){return productPacketRepository.findAll();}
    public ProductPacket getProductPacket(long id){return productPacketRepository.findProductPacketById(id);}

    @Transactional
    public boolean addProductPacket(ProductPacket productPacket){
        try {
            productPacketRepository.save(productPacket);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteProductPacket(long id){
        if(productPacketRepository.existsById(id)){
            productPacketRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // TODO: DA RIVEDERE
    public boolean updateProductPacket(long id, ProductPacket updatedProductPacket){
        var productPacket = productPacketRepository.findProductPacketById(id);
        if(updatedProductPacket == null) return false;
        if(productPacket == null) return false;

        if (updatedProductPacket.getName() != null && !updatedProductPacket.getName().isBlank()) {
            productPacket.setName(updatedProductPacket.getName());
        }

        if (updatedProductPacket.getDescription() != null && !updatedProductPacket.getDescription().isBlank()) {
            productPacket.setDescription(updatedProductPacket.getDescription());
        }

        // TODO: Da capire come fare se voglio togliere dei valori? perchè in questo modo sto sempre aggiungendo e mai togliendo
        if (updatedProductPacket.getProductsInPacket() != null && !updatedProductPacket.getProductsInPacket().isEmpty()) {
            // TODO: Manca il purgare la lista prima di mettere quelli nuovi
            for (ProductInPacket productInPacket : updatedProductPacket.getProductsInPacket()) {
                productPacket.addProduct(productInPacket.getProduct(), productInPacket.getQuantity());
            }
        }

        productPacketRepository.save(productPacket);
        return true;
    }

    public List<ProductPacket> getProductPacketsByUser(User user) {
        return productPacketRepository.findAllProductPacketsByAuthor(user);
    }
}
