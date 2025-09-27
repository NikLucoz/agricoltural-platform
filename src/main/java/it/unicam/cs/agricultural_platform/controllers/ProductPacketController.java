package it.unicam.cs.agricultural_platform.controllers;

import it.unicam.cs.agricultural_platform.dto.ContentDTO;
import it.unicam.cs.agricultural_platform.dto.ProductDTO;
import it.unicam.cs.agricultural_platform.dto.ProductInPacketDTO;
import it.unicam.cs.agricultural_platform.dto.ProductPacketDTO;
import it.unicam.cs.agricultural_platform.facades.ContentFacade;
import it.unicam.cs.agricultural_platform.models.Content;
import it.unicam.cs.agricultural_platform.models.product.Product;
import it.unicam.cs.agricultural_platform.models.product.ProductInPacket;
import it.unicam.cs.agricultural_platform.models.product.ProductPacket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/productpackets")
public class ProductPacketController {
    @Autowired
    private ContentFacade contentFacade;


    // === GENERIC ===

    @GetMapping("/")
    public ResponseEntity<List<ProductPacketDTO>> getProductPackets(){
        List<ProductPacket> productPacketsList = contentFacade.getProductPackets();
        if(productPacketsList == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<ProductPacketDTO> productPacketDTOList = productPacketsList.stream().map(ProductPacketDTO::fromProductPacket).collect(Collectors.toList());

        return new ResponseEntity<>(productPacketDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductPacketDTO> getProductPacket(@PathVariable long id){
        ProductPacket productPacket = contentFacade.getProductPacket(id);
        if(productPacket == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        ProductPacketDTO productPacketDTO = ProductPacketDTO.fromProductPacket(productPacket);

        return new ResponseEntity<>(productPacketDTO, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProductPacketDTO>> getAllProductPacketsByUser(@PathVariable long userId){
        List<ProductPacket> productPacketsList = contentFacade.getUserProductPackets(userId);
        if(productPacketsList == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<ProductPacketDTO> productPacketDTOList = productPacketsList.stream().map(ProductPacketDTO::fromProductPacket).collect(Collectors.toList());

        return new ResponseEntity<>(productPacketDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}/getProducts")
    public ResponseEntity<List<ProductInPacketDTO>> getProductsListFromPacket(@PathVariable long id){
        List<ProductInPacket> productsList = contentFacade.getProductListFromPacket(id);
        List<ProductInPacketDTO> productInPacketDTOS = productsList.stream().map(ProductInPacketDTO::fromProductInPacket).collect(Collectors.toList());

        return new ResponseEntity<>(productInPacketDTOS, HttpStatus.OK);
    }

    @PutMapping("/{id}/setApproveStatus")
    public ResponseEntity<Object> setProductPacketApprovedStatus(@PathVariable long id, @RequestBody boolean approvedStatus){
        contentFacade.setContentApprovedStatus(id, "packet", approvedStatus);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // === APPROVED ===

    @GetMapping("/approved")
    public ResponseEntity<List<? extends ContentDTO>> getAllApprovedProductPackets(){
        List<? extends Content> productPacketsList = contentFacade.getAllApprovedContents("packet");
        if(productPacketsList == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<? extends ContentDTO> productPacketDTOList = productPacketsList.stream()
                .map(p -> ProductPacketDTO.fromProductPacket((ProductPacket) p)).collect(Collectors.toList());

        return new ResponseEntity<>(productPacketDTOList, HttpStatus.OK);
    }

    @GetMapping("/approved/{filter}")
    public ResponseEntity<List<? extends ContentDTO>> getAllApprovedProductPackets(@PathVariable String filter){
        List<? extends Content> productPacketsList = contentFacade.getAllApprovedContents(filter);
        if(productPacketsList == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<? extends ContentDTO> productPacketDTOList = productPacketsList.stream()
                .map(p -> ProductPacketDTO.fromProductPacket((ProductPacket) p)).collect(Collectors.toList());

        return new ResponseEntity<>(productPacketDTOList, HttpStatus.OK);
    }

    @GetMapping("/approved/{id}")
    public ResponseEntity<ProductPacketDTO> getApprovedProductPacket(@PathVariable long id) {
        ProductPacket productPacket = (ProductPacket) contentFacade.getApprovedContent(id, "packet");
        if(productPacket == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        ProductPacketDTO productPacketDTO = ProductPacketDTO.fromProductPacket(productPacket);

        return new ResponseEntity<>(productPacketDTO, HttpStatus.OK);
    }

    @GetMapping("/approved/user/{userId}")
    public ResponseEntity<List<? extends ContentDTO>> getAllApprovedProductPacketsByUser(@PathVariable long userId){
        List<? extends Content> userProductPacketsList = contentFacade.getAllApprovedContentsByUser(userId, "packet");
        if(userProductPacketsList == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<? extends ContentDTO> userProductPacketDTOList = userProductPacketsList.stream()
                .map(p -> ProductPacketDTO.fromProductPacket((ProductPacket) p)).collect(Collectors.toList());

        return new ResponseEntity<>(userProductPacketDTOList, HttpStatus.OK);
    }


    // === NOT APPROVED ===

    @GetMapping("/notApproved")
    public ResponseEntity<List<? extends ContentDTO>> getAllNotApprovedProductPackets(){
        List<? extends Content> productPacketsList = contentFacade.getAllNotApprovedContents("packet");
        if(productPacketsList == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<? extends ContentDTO> productPacketDTOList = productPacketsList.stream()
                .map(p -> ProductPacketDTO.fromProductPacket((ProductPacket) p)).collect(Collectors.toList());

        return new ResponseEntity<>(productPacketDTOList, HttpStatus.OK);
    }

    @GetMapping("/notApproved/{id}")
    public ResponseEntity<ProductPacketDTO> getNotApprovedProductPackets(@PathVariable long id) {
        ProductPacket productPacket = (ProductPacket) contentFacade.getNotApprovedContent(id, "packet");
        if(productPacket == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        ProductPacketDTO productPacketDTO = ProductPacketDTO.fromProductPacket(productPacket);

        return new ResponseEntity<>(productPacketDTO, HttpStatus.OK);
    }

    @GetMapping("/notApproved/user/{userId}/")
    public ResponseEntity<List<? extends ContentDTO>> getAllNotApprovedProductPacketsByUser(@PathVariable long userId){
        List<? extends Content> userProductPacketsList = contentFacade.getAllNotApprovedContentsByUser(userId, "packet");
        if(userProductPacketsList == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<? extends ContentDTO> userProductPacketDTOList = userProductPacketsList.stream()
                .map(p -> ProductPacketDTO.fromProductPacket((ProductPacket) p)).collect(Collectors.toList());

        return new ResponseEntity<>(userProductPacketDTOList, HttpStatus.OK);
    }

    // === REVIEW NEEDED ===

    @GetMapping("/reviewNeeded")
    public ResponseEntity<List<? extends ContentDTO>> getReviewNeededProducts(){
        List<? extends Content> userProductPacketList = contentFacade.getAllReviewNeededContents("productpacket");
        if(userProductPacketList == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<? extends ContentDTO> userProductDTOList = userProductPacketList.stream().map(p -> ProductPacketDTO.fromProductPacket((ProductPacket) p)).collect(Collectors.toList());

        return new ResponseEntity<>(userProductDTOList, HttpStatus.OK);
    }

    @GetMapping("/reviewNeeded/user/{userId}")
    public ResponseEntity<List<? extends ContentDTO>> getAllReviewNeededProductsByUser(@PathVariable long userId){
        List<? extends Content> userProductPacketList = contentFacade.getAllReviewNeededContentsByUser(userId, "productpacket");
        if(userProductPacketList == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<? extends ContentDTO> userProductDTOList = userProductPacketList.stream().map(p -> ProductPacketDTO.fromProductPacket((ProductPacket) p)).collect(Collectors.toList());

        return new ResponseEntity<>(userProductDTOList, HttpStatus.OK);
    }

    // === CRUD ===

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
}
