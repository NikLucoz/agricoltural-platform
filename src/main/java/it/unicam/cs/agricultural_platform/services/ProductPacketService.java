package it.unicam.cs.agricultural_platform.services;

import it.unicam.cs.agricultural_platform.models.Content;
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

    public ProductPacket getProductPacket(long id){return productPacketRepository.findById(id);}

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

    public boolean updateProductPacket(ProductPacket productPacket, ProductPacket updatedProductPacket){
        if(updatedProductPacket == null) return false;
        if(productPacket == null) return false;

        if (updatedProductPacket.getName() != null && !updatedProductPacket.getName().isBlank()) {
            productPacket.setName(updatedProductPacket.getName());
        }

        if (updatedProductPacket.getDescription() != null && !updatedProductPacket.getDescription().isBlank()) {
            productPacket.setDescription(updatedProductPacket.getDescription());
        }

        if (updatedProductPacket.getProductsInPacket() != null && !updatedProductPacket.getProductsInPacket().isEmpty()) {
            productPacket.setProductsInPacket(updatedProductPacket.getProductsInPacket());
        }

        productPacketRepository.save(productPacket);
        return true;
    }

    public List<ProductPacket> getProductPacketsByUser(User user) {
        return productPacketRepository.findAllByAuthor(user);
    }

    public List<? extends Content> getAllNotApprovedProductPackets() {
        return productPacketRepository.findAllByIsApprovedFalse();
    }

    public List<? extends Content> getAllNotApprovedProductPacketsByUser(User user) {
        return productPacketRepository.findAllByAuthorAndIsApprovedFalse(user);
    }

    public List<? extends Content> getAllApprovedProductPacketsByUser(User user) {
        return productPacketRepository.findAllByAuthorAndIsApprovedTrue(user);
    }

    public List<? extends Content> getAllApprovedProductPackets() {
        return productPacketRepository.findAllByIsApprovedTrue();
    }

    public List<? extends Content> getAllReviewNeededProductPackets() {
        return productPacketRepository.findAllByReviewNeededTrue();
    }

    public List<? extends Content> getAllReviewNeededProductPacketsByUser(User user) {
        return productPacketRepository.findAllByAuthorAndReviewNeededTrue(user);
    }

    public Content getApprovedProductPacket(long id) {
        return productPacketRepository.findByIdAndIsApprovedTrue(id);
    }

    public Content getNotApprovedProductPacket(long id) {
        return productPacketRepository.findByIdAndIsApprovedFalse(id);
    }

    public void setProductPacketApproveStatus(long id, boolean approvedStatus) {
        var productPacket = productPacketRepository.findById(id);
        productPacket.setApproved(approvedStatus);

        productPacket.setReviewNeeded(!approvedStatus);
        productPacketRepository.save(productPacket);
    }

    public List<ProductInPacket> getProductInPackets(long id) {
        if(!productPacketRepository.existsById(id)) return null;
        var productPacket = productPacketRepository.findById(id);
        return productPacket.getProductsInPacket();
    }
}
