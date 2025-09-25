package it.unicam.cs.agricultural_platform.services;

import it.unicam.cs.agricultural_platform.models.product.Product;
import it.unicam.cs.agricultural_platform.models.user.User;
import it.unicam.cs.agricultural_platform.repositories.ProductRepository;
import it.unicam.cs.agricultural_platform.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    // === GENERIC ===
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(long id) {
        return productRepository.findProductById(id);
    }

    public List<Product> getProductsByUser(User user){
        return productRepository.findAllProductsByAuthor(user);
    }

    public boolean existsProduct(long id) {
        return productRepository.existsById(id);
    }


    // === APPROVED ===

    public List<Product> getAllApprovedProducts() {
        return productRepository.findAllApprovedProducts();
    }

    public List<Product> getAllApprovedProducts(String filter) {
        return productRepository.findAllApprovedProductsByFilter(filter);
    }

    public Product getApprovedProduct(long id) {
        return productRepository.findApprovedProductById(id);
    }

    public List<Product> getAllApprovedProductsByUser(User user) {
        return productRepository.findAllApprovedProductsByUser(user);
    }

    public void setProductApproveStatus(long id, boolean approvedStatus) {
        var product = productRepository.findProductById(id);
        product.setApproved(approvedStatus);

        product.setReviewNeeded(!approvedStatus);
        productRepository.save(product);
    }


    // === NOT APPROVED ===

    public List<Product> getAllNotApprovedProducts() {
        return productRepository.findAllNotApprovedProducts();
    }

    public Product getNotApprovedProduct(long id) {
        return productRepository.findNotApprovedProductById(id);
    }

    public List<Product> getAllNotApprovedProductsByUser(User user) {
        return productRepository.findAllNotApprovedProductsByUser(user);
    }



    // === REVIEW NEEDED ===

    public List<Product> getAllReviewNeededProducts() {
        return productRepository.findAllReviewNeededProducts();
    }

    public List<Product> getAllReviewNeededProductsByUser(User user) {
        return productRepository.findAllReviewNeededProductsByUser(user);
    }


    // === CRUD ===

    public boolean addProduct(Product product) {
        try {
            if(product.getAuthor() == null) return false;
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateProduct(long id, Product updatedProduct) {
        var product = productRepository.findProductById(id);
        if(product == null) return false;

        if(updatedProduct == null) return false;

        if (updatedProduct.getName() != null && !updatedProduct.getName().isBlank()) {
            product.setName(updatedProduct.getName());
        }

        if (updatedProduct.getDescription() != null && !updatedProduct.getDescription().isBlank()) {
            product.setDescription(updatedProduct.getDescription());
        }

        if (updatedProduct.getCertificates() != null && !updatedProduct.getCertificates().isEmpty()) {
            product.setCertificates(updatedProduct.getCertificates());
        }

        if (updatedProduct.getProcesses() != null && !updatedProduct.getProcesses().isEmpty()) {
            product.setProcesses(updatedProduct.getProcesses());
        }

        productRepository.save(product);
        return true;
    }

    public boolean deleteProduct(long id) {
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
