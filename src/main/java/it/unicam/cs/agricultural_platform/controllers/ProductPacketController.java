package it.unicam.cs.agricultural_platform.controllers;

import it.unicam.cs.agricultural_platform.dto.ProductPacketDTO;
import it.unicam.cs.agricultural_platform.facades.ContentFacade;
import it.unicam.cs.agricultural_platform.models.Content;
import it.unicam.cs.agricultural_platform.models.product.Product;
import it.unicam.cs.agricultural_platform.models.product.ProductPacket;
import it.unicam.cs.agricultural_platform.repositories.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productpackets")
public class ProductPacketController {
    @Autowired
    private ContentFacade contentFacade;

    @GetMapping("/")
    public ResponseEntity<List<ProductPacket>> getProductPackets(){
        List<ProductPacket> productPacketsList = contentFacade.getProductPackets();
        return new ResponseEntity<>(productPacketsList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductPacket> getProductPacket(@PathVariable long id){
        ProductPacket productPacket = contentFacade.getProductPacket(id);
        if(productPacket == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productPacket, HttpStatus.OK);
    }

    @GetMapping("/{userId}/get")
    public ResponseEntity<List<ProductPacket>> getAllUserProductPackets(@PathVariable long userId){
        List<ProductPacket> productPacketsList = contentFacade.getUserProductPackets(userId);
        return new ResponseEntity<>(productPacketsList, HttpStatus.OK);
    }

    @GetMapping("/{id}/getProducts")
    public ResponseEntity<List<Product>> getProductListFromPacket(@PathVariable long id){
        List<Product> productList = contentFacade.getProductListFromPacket(id);
        if(productList == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    // CRUD

    @PostMapping("/add")
    public ResponseEntity<Object> addProductPacket(@RequestBody ProductPacketDTO productPacketDTO){
        if(!contentFacade.addProductPacket(productPacketDTO)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Object> deleteProductPacket(@PathVariable long id){
        if(!contentFacade.deleteProductPacket(id)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Object> updateProductPacket(@PathVariable long id, @RequestBody ProductPacketDTO productPacketDTO){
        if(!contentFacade.updateProductPacket(id, productPacketDTO)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/setApproveStatus")
    public ResponseEntity<Object> setProductPacketApprovedStatus(@PathVariable long id, @RequestBody boolean approvedStatus){
        contentFacade.setContentApprovedStatus(id, "packet", approvedStatus);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // NOT APPROVED

    @GetMapping("/notApproved")
    public ResponseEntity<List<? extends Content>> getNotApprovedProductPackets(){
        List<? extends Content> productPacketsList = contentFacade.getAllNotApprovedContents("packet");
        return new ResponseEntity<>(productPacketsList, HttpStatus.OK);
    }

    @GetMapping("/notApproved/{id}")
    public ResponseEntity<Content> getNotApprovedProductPacket(@PathVariable long id) {
        Content productPacket = contentFacade.getNotApprovedContent(id, "packet");
        return new ResponseEntity<>(productPacket, HttpStatus.OK);
    }

    @GetMapping("/notApproved/user/{userId}/")
    public ResponseEntity<List<? extends Content>> getUserNotApprovedProductPackets(@PathVariable long userId){
        List<? extends Content> userProductPacketsList = contentFacade.getAllNotApprovedContentsByUser(userId, "packet");
        if(userProductPacketsList == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userProductPacketsList, HttpStatus.OK);
    }

    // APPROVED

    @GetMapping("/approved")
    public ResponseEntity<List<? extends Content>> getApprovedProductPackets(){
        List<? extends Content> productPacketsList = contentFacade.getAllApprovedContents("packet");
        return new ResponseEntity<>(productPacketsList, HttpStatus.OK);
    }

    @GetMapping("/approved/{filter}")
    public ResponseEntity<List<? extends Content>> getApprovedProductPackets(@PathVariable String filter){
        List<? extends Content> productPacketsList = contentFacade.getAllApprovedContents(filter);
        return new ResponseEntity<>(productPacketsList, HttpStatus.OK);
    }

    @GetMapping("/approved/{id}")
    public ResponseEntity<Content> getApprovedProductPacket(@PathVariable long id) {
        Content productPacket = contentFacade.getApprovedContent(id, "packet");
        return new ResponseEntity<>(productPacket, HttpStatus.OK);
    }

    @GetMapping("/approved/user/{userId}")
    public ResponseEntity<List<? extends Content>> getUserApprovedProductPackets(@PathVariable long userId){
        List<? extends Content> userProductPacketsList = contentFacade.getAllApprovedContentsByUser(userId, "packet");
        if(userProductPacketsList == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userProductPacketsList, HttpStatus.OK);
    }

}
