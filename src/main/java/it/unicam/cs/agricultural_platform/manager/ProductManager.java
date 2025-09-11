package it.unicam.cs.agricultural_platform.manager;

import it.unicam.cs.agricultural_platform.builder.ProductBuilder;
import it.unicam.cs.agricultural_platform.content.Certificate;
import it.unicam.cs.agricultural_platform.content.Product;
import it.unicam.cs.agricultural_platform.content.Content;
import it.unicam.cs.agricultural_platform.content.TransformationProcess;
import it.unicam.cs.agricultural_platform.content.user.User;
import it.unicam.cs.agricultural_platform.repository.ItemRepository;
import it.unicam.cs.agricultural_platform.repository.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ProductManager {

    private final Repository<Product> productRepository = new ItemRepository<>();

    public Product createProduct(String name, String description, User author, List<Certificate> certificates, List<TransformationProcess> processes) {
        long id = productRepository.getNextId();
        Product product = new ProductBuilder()
                .setId(id)
                .setName(name)
                .setDescription(description)
                .setCertificates(certificates)
                .setProcesses(processes)
                .createProduct();
        product.setAuthor(author);
        return productRepository.add(product);
    }

    public boolean addCertificate(long id, Certificate certificate) {
        try {
            Product product = productRepository.getItemByID(id);
            product.addCertificate(certificate);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean addProcess(long id, TransformationProcess process){
        try {
            Product product = productRepository.getItemByID(id);
            product.addProcess(process);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public Product getProduct(long id){
        try {
            return productRepository.getItemByID(id);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public List<Product> getAllProducts(){
        return productRepository.getAllItemsList();
    }

    public List<Product> getAllProductsByAuthor(User author){
        return getAllProducts().stream()
                .filter(product -> product.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    public List<Product> getAllApprovedProducts(){
        return getAllProducts().stream()
                .filter(Product::isApproved)
                .collect(Collectors.toList());
    }

    public List<Product> getAllApprovedProductsByAuthor(User author){
        return getAllProducts().stream()
                .filter(Product::isApproved)
                .filter(product -> product.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    public List<Product> getAllPendingProducts(){
        return getAllProducts().stream()
                .filter(product -> !product.isApproved())
                .collect(Collectors.toList());
    }

    public List<Product> getAllPendingProductsByAuthor(User author){
        return getAllPendingProducts().stream()
                .filter(product -> product.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    public List<Product> getAllReviewNeededProducts(){
        return getAllProducts().stream()
                .filter(Content::isReviewNeeded)
                .collect(Collectors.toList());
    }

    public List<Product> getAllReviewNeededProductsByAuthor(User author){
        return getAllProducts().stream()
                .filter(product -> product.getAuthor().equals(author))
                .filter(Content::isReviewNeeded)
                .collect(Collectors.toList());
    }

    public Product updateProduct(long id, String name, String description){
        Product oldProduct = productRepository.getItemByID(id);
        Product newProduct = new ProductBuilder().setId(id).setName(name).setDescription(description).createProduct();

        newProduct.setApproved(oldProduct.isApproved());

        // Copia certificati e processi
        oldProduct.getCertificates().forEach(newProduct::addCertificate);
        oldProduct.getProcesses().forEach(newProduct::addProcess);

        // Aggiorna il prodotto nel repository
        productRepository.remove(oldProduct);
        productRepository.add(newProduct);
        return newProduct;
    }

    public boolean deleteProduct(long id){
        try {
            Product product = productRepository.getItemByID(id);
            return productRepository.remove(product);
        } catch (
                NoSuchElementException e) {
            return false;
        }
    }

    public void approveProduct(long id, boolean isApproved){
        Product product = productRepository.getItemByID(id);
        product.setApproved(isApproved);
    }

    public boolean removeCertificateFromProduct(long id, Certificate certificate){
        try {
            Product product = productRepository.getItemByID(id);
            return product.removeCertificate(certificate);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean removeProcessFromProduct(long id, TransformationProcess process){
        try {
            Product product = productRepository.getItemByID(id);
            return product.removeProcess(process);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void markReviewNeeded(long id, boolean state){
        Product product = productRepository.getItemByID(id);
        product.setReviewNeeded(state);
    }
}
