package com.store.Web.Controller;

import com.store.Domain.Purchase;
import com.store.Domain.Service.PurchaseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    /*Inyectar el servicio*/
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/all")
    @ApiOperation("Select all Purchases")
    public ResponseEntity<List<Purchase>> getAll(){
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/cliente/{idClient}")
    @ApiOperation("Select Specific Purchase")
    public ResponseEntity<List<Purchase>> getByClient(@PathVariable("idClient") String ClientId){
        return purchaseService.getByClient(ClientId)
                .map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/Save")
    @ApiOperation("Save Purchase")
    public ResponseEntity<Purchase> save(Purchase purchase){
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }
}
