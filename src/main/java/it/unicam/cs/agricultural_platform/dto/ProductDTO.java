package it.unicam.cs.agricultural_platform.dto;

import it.unicam.cs.agricultural_platform.models.product.Product;
import it.unicam.cs.agricultural_platform.models.user.User;

public class ProductDTO extends ContentDTO {
    private String certificates;
    private String processes;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, String description, boolean approved, boolean reviewNeeded, double price, int stockQuantity, Long authorId, String certificates, String processes) {
        super(id, name, description, approved, reviewNeeded, price, stockQuantity, authorId);
        this.certificates = certificates;
        this.processes = processes;
    }

    public static Product fromDTO(ProductDTO dto, User author) {
        Product product = new Product();
        product.setAuthor(author);
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setApproved(dto.isApproved());
        product.setReviewNeeded(dto.isReviewNeeded());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setCertificates(dto.getCertificates());
        product.setProcesses(dto.getProcesses());
        return product;
    }

    public static ProductDTO fromProduct(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setApproved(product.isApproved());
        dto.setReviewNeeded(product.isReviewNeeded());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());
        dto.setCertificates(product.getCertificates());
        dto.setAuthorId(product.getAuthor().getId());
        dto.setProcesses(product.getProcesses());
        return dto;
    }

    public String getCertificates() { return certificates; }
    public void setCertificates(String certificates) { this.certificates = certificates; }

    public String getProcesses() { return processes; }
    public void setProcesses(String processes) { this.processes = processes; }
}
