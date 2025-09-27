package it.unicam.cs.agricultural_platform.repositories;

import it.unicam.cs.agricultural_platform.models.product.Product;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends ContentRepository<Product> {
    @Query("SELECT p FROM Product p WHERE p.isApproved = true AND p.certificates LIKE %:filter% OR p.name LIKE %:filter%")
    List<Product> findAllApprovedProductsByFilter(String filter);
}
