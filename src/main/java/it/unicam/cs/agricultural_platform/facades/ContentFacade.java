package it.unicam.cs.agricultural_platform.facades;

import it.unicam.cs.agricultural_platform.dto.ProductDTO;
import it.unicam.cs.agricultural_platform.dto.ProductPacketDTO;
import it.unicam.cs.agricultural_platform.models.Content;
import it.unicam.cs.agricultural_platform.models.product.Product;
import it.unicam.cs.agricultural_platform.models.product.ProductInPacket;
import it.unicam.cs.agricultural_platform.models.product.ProductPacket;
import it.unicam.cs.agricultural_platform.models.user.User;
import it.unicam.cs.agricultural_platform.repositories.ContentRepository;
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
    private ContentRepository contentRepository;

    //region CONTENT METHODS

    public Content getApprovedContent(long id, String type) {
        return switch (type.toLowerCase()) {
            case "product" -> productService.getApprovedProduct(id);
            case "packet" -> productPacketService.getApprovedProductPacket(id);
            default -> throw new IllegalArgumentException("Tipo non supportato");
        };
    }

    public Content getNotApprovedContent(long id, String type) {
        return switch (type.toLowerCase()) {
            case "product" -> productService.getNotApprovedProduct(id);
            case "packet" -> productPacketService.getNotApprovedProductPacket(id);
            default -> throw new IllegalArgumentException("Tipo non supportato");
        };
    }

    public List<? extends Content> getAllNotApprovedContents(String type) {
        return switch (type.toLowerCase()) {
            case "product" -> productService.getAllNotApprovedProducts();
            case "packet" -> productPacketService.getAllNotApprovedProductPackets();
            default -> throw new IllegalArgumentException("Tipo non supportato");
        };
    }

    public List<? extends Content> getAllApprovedContents(String type) {
        return switch (type.toLowerCase()) {
            case "product" -> productService.getAllApprovedProducts();
            case "packet" -> productPacketService.getAllApprovedProductPackets();
            default -> throw new IllegalArgumentException("Tipo non supportato");
        };
    }

    public List<? extends Content> getAllNotApprovedContentsByUser(long userId, String type) {
        if(!userService.existsUser(userId)) return null;
        var user = userService.getUserById(userId);

        return switch (type.toLowerCase()) {
            case "product" -> productService.getAllNotApprovedProductsByUser(user);
            case "packet" -> productPacketService.getAllNotApprovedProductPacketsByUser(user);
            default -> throw new IllegalArgumentException("Tipo non supportato");
        };
    }

    public List<? extends Content> getAllApprovedContentsByUser(long userId, String type) {
        if(!userService.existsUser(userId)) return null;
        var user = userService.getUserById(userId);

        return switch (type.toLowerCase()) {
            case "product" -> productService.getAllApprovedProductsByUser(user);
            case "packet" -> productPacketService.getAllApprovedProductPacketsByUser(user);
            default -> throw new IllegalArgumentException("Tipo non supportato");
        };
    }

    public List<? extends Content> getAllReviewNeededContents(String type) {
        return switch (type.toLowerCase()) {
            case "product" -> productService.getAllReviewNeededProducts();
            case "packet" -> productPacketService.getAllReviewNeededProductPackets();
            default -> throw new IllegalArgumentException("Tipo non supportato");
        };
    }

    public List<? extends Content> getAllReviewNeededContentsByUser(long userId, String type) {
        if(!userService.existsUser(userId)) return null;
        var user = userService.getUserById(userId);

        return switch (type.toLowerCase()) {
            case "product" -> productService.getAllReviewNeededProductsByUser(user);
            case "packet" -> productPacketService.getAllReviewNeededProductPacketsByUser(user);
            default -> throw new IllegalArgumentException("Tipo non supportato");
        };
    }

    public void setContentApprovedStatus(long id, String type, boolean approvedStatus) {
        switch (type.toLowerCase()) {
            case "product" -> productService.setProductApproveStatus(id, approvedStatus);
            case "packet" -> productPacketService.setProductPacketApproveStatus(id, approvedStatus);
            default -> throw new IllegalArgumentException("Tipo non supportato");
        }
    }

    //endregion

    //region PRODUCT METHODS

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

    public boolean addProduct(ProductDTO productDTO) {
        var author = userService.getUserById(productDTO.getAuthorId());
        var product = ProductDTO.fromDTO(productDTO, author);
        return productService.addProduct(product);
    }

    public boolean updateProduct(long id, ProductDTO productDTO) {
        var original = productService.getProduct(id);
        var author = userService.getUserById(productDTO.getAuthorId());
        var updatedProduct = ProductDTO.fromDTO(productDTO, author);

        return productService.updateProduct(original, updatedProduct);
    }

    public boolean deleteProduct(long id) {
        return productService.deleteProduct(id);
    }

    public List<Product> getAllApprovedProducts(String filter) {
        if(filter != null && !filter.isBlank()) {
            return productService.getAllApprovedProducts(filter);
        }
        return new ArrayList<>();
    }

    //endregion

    //region PRODUCT PACKET METHODS

    public List<ProductPacket> getProductPackets() {return productPacketService.getProductPackets();}

    public ProductPacket getProductPacket(long id) {return productPacketService.getProductPacket(id);}

    public List<ProductPacket> getProductPacketsByUser(long userId) {
        if(userService.existsUser(userId)){
            User user = userService.getUserById(userId);
            return productPacketService.getProductPacketsByUser(user);
        }
        return new ArrayList<>();
    }

    public boolean addProductPacket(ProductPacketDTO productPacketDTO) {
        var author = userService.getUserById(productPacketDTO.getAuthorId());
        var productPacket = ProductPacketDTO.fromDTO(productPacketDTO, author);

        var productsInPacket = new ArrayList<ProductInPacket>();

        // Cerco i prodotti da mettere nel pacchetto tramite gli id nel DTO e li aggiungo alla lista
        for(var productInPacketDto : productPacketDTO.getProductsInPacket()) {
            var product_id = productInPacketDto.getProductId();

            if (!productService.existsProduct(product_id)) continue;
            var product = productService.getProduct(product_id);
            var productInPacket = new ProductInPacket(productPacket, product, productInPacketDto.getQuantity());
            productsInPacket.add(productInPacket);
        }

        productPacket.setProductsInPacket(productsInPacket);

        return productPacketService.addProductPacket(productPacket);
    }

    public boolean deleteProductPacket(long id) { return productPacketService.deleteProductPacket(id); }

    public boolean updateProductPacket(long id, ProductPacketDTO productPacketDTO) {
        var original = productPacketService.getProductPacket(id);
        var author = userService.getUserById(productPacketDTO.getAuthorId());
        var updatedProductPacket = ProductPacketDTO.fromDTO(productPacketDTO, author);

        var productsInPacket = new ArrayList<ProductInPacket>();

        // Cerco i prodotti da mettere nel pacchetto tramite gli id nel DTO e li aggiungo alla lista
        for(var productInPacketDto : productPacketDTO.getProductsInPacket()) {
            var product_id = productInPacketDto.getProductId();

            if (!productService.existsProduct(product_id)) continue;
            var product = productService.getProduct(product_id);
            var productInPacket = new ProductInPacket(original, product, productInPacketDto.getQuantity());
            productsInPacket.add(productInPacket);
        }

        updatedProductPacket.setProductsInPacket(productsInPacket);
        return productPacketService.updateProductPacket(original, updatedProductPacket);
    }

    public List<ProductInPacket> getProductListFromPacket(long id) {
        return productPacketService.getProductInPackets(id);
    }

    //endregion
}
