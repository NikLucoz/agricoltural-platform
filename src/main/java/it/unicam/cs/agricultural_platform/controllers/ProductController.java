package it.unicam.cs.agricultural_platform.controllers;

import it.unicam.cs.agricultural_platform.dto.ProductDTO;
import it.unicam.cs.agricultural_platform.facades.ContentFacade;
import it.unicam.cs.agricultural_platform.models.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ContentFacade contentFacade;


    // === GENERIC ===

    @GetMapping("/")
    public ResponseEntity<List<Product>> getProducts(){
        List<Product> productList = contentFacade.getProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/{filter}")
    public ResponseEntity<List<Product>> getProducts(@PathVariable String filter){
        List<Product> productList = contentFacade.getAllApprovedProducts(filter);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable long id){
        Product product = contentFacade.getProduct(id);
        if(product == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    //TODO Come mai qui ci va il get e in "getProduct", ad esempio, no?
    @GetMapping("/{userId}/get")
    public ResponseEntity<List<Product>> getProductsByUser(@PathVariable long userId){
        List<Product> userProductsList = contentFacade.getProductsByUser(userId);
        if(userProductsList == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userProductsList, HttpStatus.OK);
    }


    // === APPROVED ===

    @GetMapping("/approved")
    public ResponseEntity<List<Product>> getApprovedProducts(){
        List<Product> productList = contentFacade.getAllApprovedProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/approved/{id}")
    public ResponseEntity<Product> getApprovedProduct(@PathVariable long id){
        Product product = contentFacade.getApprovedProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    //TODO Capire come mai la route va scritta in questo modo
    @GetMapping("/approved/user/{userId}")
    public ResponseEntity<List<Product>> getAllApprovedProductsByUser(@PathVariable long userId){
        List<Product> products = contentFacade.getAllApprovedProductsByUser(userId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    //TODO Controllare
    @PutMapping("/{id}/setApproveStatus")
    public ResponseEntity<Object> setProductApprovedStatus(@PathVariable long id, @RequestBody boolean approvedStatus){
        contentFacade.setProductApprovedStatus(id, approvedStatus);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // === NOT APPROVED ===

    @GetMapping("/notApproved")
    public ResponseEntity<List<Product>> getNotApprovedProducts(){
        List<Product> productList = contentFacade.getAllNotApprovedProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/notApproved/{id}")
    public ResponseEntity<Product> getNotApprovedProduct(@PathVariable long id){
        Product product = contentFacade.getNotApprovedProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/notApproved/user/{userId}")
    public ResponseEntity<List<Product>> getNotApprovedProductsByUser(@PathVariable long userId){
        List<Product> products = contentFacade.getAllNotApprovedProductsByUser(userId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    // === REVIEW NEEDED ===

    @GetMapping("/reviewNeeded")
    public ResponseEntity<List<Product>> getReviewNeededProducts(){
        List<Product> productList = contentFacade.getAllReviewNeededProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/reviewNeeded/user/{userId}")
    public ResponseEntity<List<Product>> getAllReviewNeededProductsByUser(@PathVariable long userId){
        List<Product> products = contentFacade.getAllReviewNeededProductsByUser(userId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }







































    // === CRUD ===

    @PostMapping("/add")
    public ResponseEntity<Object> addProduct(@RequestBody ProductDTO product){
        if(!contentFacade.addProduct(product)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Object> updateProduct(@PathVariable long id, @RequestBody Product product){
        if(!contentFacade.updateProduct(id, product)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Object> deleteProduct(@PathVariable long id){
        if(!contentFacade.deleteProduct(id)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
