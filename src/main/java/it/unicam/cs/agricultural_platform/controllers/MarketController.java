package it.unicam.cs.agricultural_platform.controllers;

import it.unicam.cs.agricultural_platform.dto.ItemCartOperationDTO;
import it.unicam.cs.agricultural_platform.dto.UpdateMarketDataDTO;
import it.unicam.cs.agricultural_platform.facades.MarketFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/market")
public class MarketController {

    @Autowired
    private MarketFacade marketFacade;

    @PostMapping("/addToCart")
    public ResponseEntity<Object> addItemToUserCart(@RequestBody ItemCartOperationDTO operationDTO) {
        if(!marketFacade.addItemToUserCart(operationDTO.getUserId(), operationDTO.getContentId(), operationDTO.getQuantity())) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/removeFromCart")
    public ResponseEntity<Object> removeItemFromUserCart(@RequestBody ItemCartOperationDTO operationDTO) {
        if(!marketFacade.removeItemFromUserCart(operationDTO.getUserId(), operationDTO.getContentId(), operationDTO.getQuantity())) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/updateContentMarketData")
    public ResponseEntity<Object> updateContentMarketData(@RequestBody UpdateMarketDataDTO updateDTO) {
        if(!marketFacade.updateContentMarketData(updateDTO.getContentId(), updateDTO.getPrice(), updateDTO.getStockQuantity())) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
