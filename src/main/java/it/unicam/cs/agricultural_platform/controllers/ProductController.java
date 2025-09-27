package it.unicam.cs.agricultural_platform.controllers;

import it.unicam.cs.agricultural_platform.dto.ContentDTO;
import it.unicam.cs.agricultural_platform.dto.ProductDTO;
import it.unicam.cs.agricultural_platform.facades.ContentFacade;
import it.unicam.cs.agricultural_platform.models.Content;
import it.unicam.cs.agricultural_platform.models.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ContentFacade contentFacade;


    // === GENERIC ===

    @GetMapping("/")
    public ResponseEntity<List<ProductDTO>> getProducts(){
        List<Product> productsList = contentFacade.getProducts();
        if(productsList == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<ProductDTO> productDTOList = productsList.stream().map(ProductDTO::fromProduct).collect(Collectors.toList());

        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

    @GetMapping(params = "name")
    public ResponseEntity<List<ProductDTO>> getProducts(@RequestParam String filter){
        List<Product> productsList = contentFacade.getAllApprovedProducts(filter);
        if(productsList == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<ProductDTO> productDTOList = productsList.stream().map(ProductDTO::fromProduct).collect(Collectors.toList());

        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable long id){
        Product product = contentFacade.getProduct(id);
        if(product == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        ProductDTO productDTO = ProductDTO.fromProduct(product);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    //TODO Controllare la route
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProductDTO>> getProductsByUser(@PathVariable long userId){
        List<Product> userProductList = contentFacade.getProductsByUser(userId);
        if(userProductList == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<ProductDTO> userProductDTOList = userProductList.stream().map(ProductDTO::fromProduct).collect(Collectors.toList());
        return new ResponseEntity<>(userProductDTOList, HttpStatus.OK);
    }


    // === APPROVED ===

    @GetMapping("/approved")
    public ResponseEntity<List<? extends ContentDTO>> getAllApprovedProducts(){
        List<? extends Content> productsList = contentFacade.getAllApprovedContents("product");
        if(productsList == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<? extends ContentDTO> productDTOList = productsList.stream().map(p -> ProductDTO.fromProduct((Product) p)).collect(Collectors.toList());

        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

    @GetMapping("/approved/{id}")
    public ResponseEntity<ProductDTO> getApprovedProduct(@PathVariable long id){
        Product product = (Product) contentFacade.getApprovedContent(id, "product");
        if(product == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        ProductDTO productDTO = ProductDTO.fromProduct(product);

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @GetMapping("/approved/user/{userId}")
    public ResponseEntity<List<? extends ContentDTO>>  getAllApprovedProductsByUser(@PathVariable long userId){
        List<? extends Content> userProductsList = contentFacade.getAllApprovedContentsByUser(userId, "product");
        if(userProductsList == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<? extends ContentDTO> userProductDTOList = userProductsList.stream().map(p -> ProductDTO.fromProduct((Product) p)).collect(Collectors.toList());

        return new ResponseEntity<>(userProductDTOList, HttpStatus.OK);
    }

    @PutMapping("/{id}/setApproveStatus")
    public ResponseEntity<Object> setProductApprovedStatus(@PathVariable long id, @RequestBody boolean approvedStatus){
        contentFacade.setContentApprovedStatus(id, "product", approvedStatus);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // === NOT APPROVED ===

    @GetMapping("/notApproved")
    public ResponseEntity<List<? extends ContentDTO>>  getAllNotApprovedProducts(){
        List<? extends Content> productsList = contentFacade.getAllNotApprovedContents("product");
        if(productsList == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<? extends ContentDTO> productDTOList = productsList.stream().map(p -> ProductDTO.fromProduct((Product) p)).collect(Collectors.toList());

        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

    @GetMapping("/notApproved/{id}")
    public ResponseEntity<ProductDTO> getNotApprovedProduct(@PathVariable long id){
        Product product = (Product) contentFacade.getNotApprovedContent(id, "product");
        if(product == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        ProductDTO productDTO = ProductDTO.fromProduct(product);

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @GetMapping("/notApproved/user/{userId}")
    public ResponseEntity<List<? extends ContentDTO>> getAllNotApprovedProductsByUser(@PathVariable long userId){
        List<? extends Content> userProductList = contentFacade.getAllNotApprovedContentsByUser(userId, "product");
        if(userProductList == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<? extends ContentDTO> userProductDTOList = userProductList.stream().map(p -> ProductDTO.fromProduct((Product) p)).collect(Collectors.toList());


        return new ResponseEntity<>(userProductDTOList, HttpStatus.OK);
    }


    // === REVIEW NEEDED ===

    @GetMapping("/reviewNeeded")
    public ResponseEntity<List<? extends ContentDTO>> getAllReviewNeededProducts(){
        List<? extends Content> productsList = contentFacade.getAllReviewNeededContents("product");
        if(productsList == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<? extends ContentDTO> productDTOList = productsList.stream().map(p -> ProductDTO.fromProduct((Product) p)).collect(Collectors.toList());

        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

    @GetMapping("/reviewNeeded/user/{userId}")
    public ResponseEntity<List<? extends ContentDTO>> getAllReviewNeededProductsByUser(@PathVariable long userId){
        List<? extends Content> userProductList = contentFacade.getAllReviewNeededContentsByUser(userId, "product");
        if(userProductList == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<? extends ContentDTO> userProductDTOList = userProductList.stream().map(p -> ProductDTO.fromProduct((Product) p)).collect(Collectors.toList());

        return new ResponseEntity<>(userProductDTOList, HttpStatus.OK);
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
    public ResponseEntity<Object> updateProduct(@PathVariable long id, @RequestBody ProductDTO productDTO){
        if(!contentFacade.updateProduct(id, productDTO)){
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
