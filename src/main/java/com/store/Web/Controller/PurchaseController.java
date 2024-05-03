package com.store.Web.Controller;

import com.store.Domain.Purchase;
import com.store.Domain.Service.PurchaseService;
import com.store.crosscutting.constants.Constants;
import com.store.crosscutting.constants.ResourceEndpoint;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = ResourceEndpoint.PURCHASE)
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping(value = ResourceEndpoint.HEALTH)
    public String health(){
        return String.format("up Collection = %s", ResourceEndpoint.PURCHASE);
    }

    @GetMapping(value = ResourceEndpoint.GET_ALL)
    @ApiOperation("Select all Purchases")
    public ResponseEntity<List<Purchase>> getAll(){
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = ResourceEndpoint.CLIENT)
    @ApiOperation("Select Specific Purchase")
    public ResponseEntity<List<Purchase>> getByClient(
            @RequestParam(value = Constants.PRODUCT_ID) String ClientId){
        return purchaseService.getByClient(ClientId)
                .map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = ResourceEndpoint.SAVE)
    @ApiOperation("Save Purchase")
    public ResponseEntity<Purchase> save(Purchase purchase){
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }
}
