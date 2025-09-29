package it.unicam.cs.agricultural_platform.dto.content;

import it.unicam.cs.agricultural_platform.models.product.Product;
import it.unicam.cs.agricultural_platform.models.product.ProductInPacket;
import it.unicam.cs.agricultural_platform.models.product.ProductPacket;

public class ProductInPacketDTO {
    private Long id;
    private Long productId;
    private long packetId;
    private int quantity;

    public ProductInPacketDTO() {
    }

    public ProductInPacketDTO(Long id, Long productId, int quantity) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public long getPacketId() {
        return packetId;
    }
    public void setPacketId(long packetId) {
        this.packetId = packetId;
    }

    public static ProductInPacket fromDTO(ProductInPacketDTO dto, Product product, ProductPacket productPacket) {
        ProductInPacket productInPacket = new ProductInPacket();
        productInPacket.setPacket(productPacket);
        productInPacket.setProduct(product);
        productInPacket.setQuantity(dto.getQuantity());
        return productInPacket;
    }

    public static ProductInPacketDTO fromProductInPacket(ProductInPacket productInPacket) {
        ProductInPacketDTO productInPacketDto = new ProductInPacketDTO();
        productInPacketDto.setId(productInPacket.getId());
        productInPacketDto.setProductId(productInPacket.getProduct().getId());
        productInPacketDto.setQuantity(productInPacket.getQuantity());
        productInPacketDto.setPacketId(productInPacket.getPacket().getId());

        return productInPacketDto;
    }
}
