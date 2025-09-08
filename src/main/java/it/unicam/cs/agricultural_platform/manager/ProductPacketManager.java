package it.unicam.cs.agricultural_platform.manager;

import it.unicam.cs.agricultural_platform.content.Product;
import it.unicam.cs.agricultural_platform.content.Content;
import it.unicam.cs.agricultural_platform.content.ProductPacket;
import it.unicam.cs.agricultural_platform.content.user.User;
import it.unicam.cs.agricultural_platform.repository.ItemRepository;
import it.unicam.cs.agricultural_platform.repository.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ProductPacketManager {

    private Repository<ProductPacket> productPacketRepository = new ItemRepository<>();

    public ProductPacket createProductPacket(String name, String description, List<Product> products) {
        ProductPacket productPacket = new ProductPacket(productPacketRepository.getNextId(), name, description, products);
        return productPacketRepository.add(productPacket);
    }

    public ProductPacket getProductPacket(long id){
        try {
            return productPacketRepository.getItemByID(id);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public List<ProductPacket> getAllProductPackets(){
        return productPacketRepository.getAllItemsList();
    }

    public List<ProductPacket> getAllProductPacketsByAuthor(User author){
        return getAllProductPackets().stream()
                .filter(productPacket -> productPacket.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    public ProductPacket updateProductPacket(long id, String name, String description){
        try {
            ProductPacket productPacket = productPacketRepository.getItemByID(id);
            productPacket.setName(name);
            productPacket.setDescription(description);
            return productPacket;
        }catch (NoSuchElementException e) {
            return null;
        }
    }

    public boolean deleteProductPacket(long id){
        try {
            ProductPacket productPacket = productPacketRepository.getItemByID(id);
            return productPacketRepository.remove(productPacket);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean addProductToPacket(long id, Product product){
        try {
            ProductPacket productPacket = productPacketRepository.getItemByID(id);
            productPacket.addProduct(product);
            return true;
        }catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean removeProductFromPacket(long id, Product product){
        try {
            ProductPacket productPacket = productPacketRepository.getItemByID(id);
            productPacket.removeProduct(product);
            return true;
        }catch (NoSuchElementException e) {
            return false;
        }
    }

    public List<ProductPacket> getAllApprovedProductPackets(){
        return getAllProductPackets().stream()
                .filter(ProductPacket::isApproved)
                .collect(Collectors.toList());
    }

    public List<ProductPacket> getAllApprovedProductPacketsByAuthor(User author){
        return getAllProductPacketsByAuthor(author).stream()
                .filter(ProductPacket::isApproved)
                .collect(Collectors.toList());
    }

    public List<ProductPacket> getAllPendingProductPackets(){
        return getAllProductPackets().stream()
                .filter(productPacket -> !productPacket.isApproved())
                .collect(Collectors.toList());
    }

    public List<ProductPacket> getAllPendingProductPacketsByAuthor(User author){
        return getAllPendingProductPackets().stream()
                .filter(productPacket -> productPacket.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    public List<ProductPacket> getAllReviewNeededProductPackets(){
        return getAllProductPackets().stream()
                .filter(Content::isReviewNeeded)
                .collect(Collectors.toList());
    }

    public List<ProductPacket> getAllReviewNeededProductsByAuthor(User author){
        return getAllProductPacketsByAuthor(author).stream()
                .filter(Content::isReviewNeeded)
                .collect(Collectors.toList());
    }

    public void approveProductPacket(long id, boolean isApproved){
        ProductPacket productPacket = productPacketRepository.getItemByID(id);
        productPacket.setApproved(isApproved);
    }

    public void markReviewNeeded(long id, boolean state){
        ProductPacket productPacket = productPacketRepository.getItemByID(id);
        productPacket.setReviewNeeded(state);
    }
}
