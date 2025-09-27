package it.unicam.cs.agricultural_platform.dto;

import it.unicam.cs.agricultural_platform.models.product.Product;
import it.unicam.cs.agricultural_platform.models.product.ProductInPacket;
import it.unicam.cs.agricultural_platform.models.product.ProductPacket;
import it.unicam.cs.agricultural_platform.models.user.User;

import java.util.ArrayList;
import java.util.List;


public class ProductPacketDTO extends ContentDTO {
    private List<ProductInPacketDTO> productsInPacket;

    public ProductPacketDTO() {
    }

    public ProductPacketDTO(Long id, String name, String description, boolean approved, boolean reviewNeeded, double price, int stockQuantity, Long authorId, List<ProductInPacketDTO> productsInPacket) {
        super(id, name, description, approved, reviewNeeded, price, stockQuantity, authorId);
        this.productsInPacket = productsInPacket;
    }

    public List<ProductInPacketDTO> getProductsInPacket() { return productsInPacket; }
    public void setProductsInPacket(List<ProductInPacketDTO> productsInPacket) {
        this.productsInPacket = productsInPacket;
    }

    public static ProductPacket fromDTO(ProductPacketDTO productPacketDTO, User author) {
        ProductPacket productPacket = new ProductPacket();
        productPacket.setAuthor(author);
        productPacket.setName(productPacketDTO.getName());
        productPacket.setDescription(productPacketDTO.getDescription());
        productPacket.setApproved(productPacketDTO.isApproved());
        productPacket.setReviewNeeded(productPacketDTO.isReviewNeeded());
        productPacket.setPrice(productPacketDTO.getPrice());
        productPacket.setStockQuantity(productPacketDTO.getStockQuantity());

        return productPacket;
    }

    public static ProductPacketDTO fromProductPacket(ProductPacket productPacket) {
        ProductPacketDTO dto = new ProductPacketDTO();
        dto.setId(productPacket.getId());
        dto.setName(productPacket.getName());
        dto.setDescription(productPacket.getDescription());
        dto.setApproved(productPacket.isApproved());
        dto.setReviewNeeded(productPacket.isReviewNeeded());
        dto.setPrice(productPacket.getPrice());
        dto.setStockQuantity(productPacket.getStockQuantity());
        dto.setAuthorId(productPacket.getAuthor().getId());

        List<ProductInPacketDTO> productInPacketsIds = new ArrayList<>();
        for(var productInPacket : productPacket.getProductsInPacket()) {
            productInPacketsIds.add(ProductInPacketDTO.fromProductInPacket(productInPacket));
        }

        dto.setProductsInPacket(productInPacketsIds);
        return dto;
    }


}
