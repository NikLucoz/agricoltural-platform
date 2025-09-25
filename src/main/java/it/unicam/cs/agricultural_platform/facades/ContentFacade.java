package it.unicam.cs.agricultural_platform.facades;

import it.unicam.cs.agricultural_platform.dto.ProductDTO;
import it.unicam.cs.agricultural_platform.dto.ProductInPacketDTO;
import it.unicam.cs.agricultural_platform.dto.ProductPacketDTO;
import it.unicam.cs.agricultural_platform.models.product.Product;
import it.unicam.cs.agricultural_platform.models.product.ProductInPacket;
import it.unicam.cs.agricultural_platform.models.product.ProductPacket;
import it.unicam.cs.agricultural_platform.models.user.User;
import it.unicam.cs.agricultural_platform.services.ProductPacketService;
import it.unicam.cs.agricultural_platform.services.ProductService;
import it.unicam.cs.agricultural_platform.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContentFacade {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductPacketService productPacketService;
    @Autowired
    private UserService userService;


    // === GENERIC ===

    public List<Product> getProducts() {
        return productService.getProducts();
    }

    public Product getProduct(long id) {
        return productService.getProduct(id);
    }

    public List<Product> getProductsByUser(long userId) {
        if(userService.existsUser(userId)){
            User user = userService.getUserById(userId);
            return productService.getProductsByUser(user);
        }
        return new ArrayList<>();
    }

    public List<ProductPacket> getProductPackets() {return productPacketService.getProductPackets();}
    public ProductPacket getProductPacket(long id) {return productPacketService.getProductPacket(id);}
    public List<ProductPacket> getUserProductPackets(long userId) {
        if(userService.existsUser(userId)){
            User user = userService.getUserById(userId);
            return productPacketService.getProductPacketsByUser(user);
        }
        return new ArrayList<>();
    }

    // === APPROVED ===

    public List<Product> getAllApprovedProducts() {
        return productService.getAllApprovedProducts();
    }

    public List<Product> getAllApprovedProducts(String filter) {
        if(filter != null && !filter.isBlank()) {
            return productService.getAllApprovedProducts(filter);
        }
        return new ArrayList<>();
    }

    public Product getApprovedProduct(long id) {
        return productService.getApprovedProduct(id);
    }

    public List<Product> getAllApprovedProductsByUser(long userId) {
        if(userService.existsUser(userId)){
            User user = userService.getUserById(userId);
            return productService.getAllApprovedProductsByUser(user);
        }
        return new ArrayList<>();
    }

    public void setProductApprovedStatus(long id, boolean approvedStatus) {
        if(productService.existsProduct(id)) {
            productService.setProductApproveStatus(id, approvedStatus);
        }
    }


    // === NOT APPROVED ===

    public List<Product> getAllNotApprovedProducts() {
        return productService.getAllNotApprovedProducts();
    }

    public Product getNotApprovedProduct(long id) {
        return productService.getNotApprovedProduct(id);
    }

    public List<Product> getAllNotApprovedProductsByUser(long userId) {
        if(userService.existsUser(userId)){
            User user = userService.getUserById(userId);
            return productService.getAllNotApprovedProductsByUser(user);
        }
        return new ArrayList<>();
    }


    // == REVIEW NEEDED ===

    public List<Product> getAllReviewNeededProducts() {
        return productService.getAllReviewNeededProducts();
    }

    public List<Product> getAllReviewNeededProductsByUser(long userId) {
        if(userService.existsUser(userId)){
            User user = userService.getUserById(userId);
            return productService.getAllReviewNeededProductsByUser(user);
        }
        return new ArrayList<>();
    }


    // === CRUD ===

    public boolean addProduct(ProductDTO productDTO) {
        var author = userService.getUserById(productDTO.getAuthorId());
        var product = ProductDTO.fromDTO(productDTO, author);
        return productService.addProduct(product);
    }

    public boolean updateProduct(long id, Product product) {
        return productService.updateProduct(id, product);
    }

    public boolean deleteProduct(long id) {
        return productService.deleteProduct(id);
    }

    public boolean addProductPacket(ProductPacketDTO productPacketDTO) {
        var author = userService.getUserById(productPacketDTO.getAuthorId());
        var productPacket = ProductPacketDTO.fromDTO(productPacketDTO, author);

        for(var productInPacketDto : productPacketDTO.getProductsInPacket()) {
            var product_id = productInPacketDto.getProductId();

            if (!productService.existsProduct(product_id)) continue;
            var product = productService.getProduct(product_id);
            productPacket.addProduct(product, productInPacketDto.getQuantity());
        }

        return productPacketService.addProductPacket(productPacket);
    }

    public boolean deleteProductPacket(long id) {return productPacketService.deleteProductPacket(id);}

    public boolean updateProductPacket(long id, ProductPacket productPacket) { return productPacketService.updateProductPacket(id, productPacket);}
}
