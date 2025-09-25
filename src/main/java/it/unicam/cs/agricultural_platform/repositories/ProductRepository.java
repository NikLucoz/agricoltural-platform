package it.unicam.cs.agricultural_platform.repositories;

import it.unicam.cs.agricultural_platform.models.product.Product;
import it.unicam.cs.agricultural_platform.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductById(long id);

    List<Product> findAllProductsByAuthor(User user);

    @Query("SELECT p FROM Product p WHERE p.isApproved = true")
    List<Product> findAllApprovedProducts();

    @Query("SELECT p FROM Product p WHERE p.isApproved = true AND p.certificates LIKE %:filter% OR p.name LIKE %:filter%")
    List<Product> findAllApprovedProductsByFilter(String filter);

    @Query("SELECT p FROM Product p WHERE p.isApproved = true")
    Product findApprovedProductById(long id);

    @Query ("SELECT p FROM Product p WHERE p.isApproved = true AND p.author = :user")
    List<Product> findAllApprovedProductsByUser(User user);

    @Query("SELECT p FROM Product p WHERE p.isApproved = false")
    List<Product> findAllNotApprovedProducts();

    @Query("SELECT p FROM Product p WHERE p.isApproved = false")
    Product findNotApprovedProductById(long id);

    @Query ("SELECT p FROM Product p WHERE p.isApproved = false AND p.author = :user")
    List<Product> findAllNotApprovedProductsByUser(User user);

    @Query("SELECT p FROM Product p WHERE p.reviewNeeded = true")
    List<Product> findAllReviewNeededProducts();

    @Query ("SELECT p FROM Product p WHERE p.reviewNeeded = false AND p.author = :user")
    List<Product> findAllReviewNeededProductsByUser(User user);
}
