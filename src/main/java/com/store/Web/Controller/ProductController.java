package com.store.Web.Controller;

import com.store.Domain.Product;
import com.store.Domain.Service.ProductService;
import com.store.crosscutting.constants.Constants;
import com.store.crosscutting.constants.ResourceEndpoint;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = ResourceEndpoint.PATH_PRODUCT)
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = ResourceEndpoint.HEALTH)
    public String health (){
        return String.format("up Collection = %s", ResourceEndpoint.PATH_PRODUCT);
    }

    @GetMapping(value = ResourceEndpoint.GET_ALL, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Select all product")
    @ApiResponse(code = 200, message = "Select all product")
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping()
    @ApiOperation("Select One Specific Product")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    public ResponseEntity<Product> getProduct(
            @ApiParam(value = "Insert id of product", required = true, example = "7")
            @RequestParam(value = Constants.PRODUCT_ID) int productId){
        return productService.getProduct(productId)                             //Este devuelve un Opcional
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));            //En caso de no encontrar niingun producto
    }

    @GetMapping(value = ResourceEndpoint.PATH_CATEGORY, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Of category Select item")
    public ResponseEntity<List<Product>> getByCategory (
            @RequestParam(value = Constants.CATEGORY_ID) int categoryId){
        return productService.getByCategory(categoryId)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = ResourceEndpoint.SAVE)
    @ApiOperation("Save product")
    public ResponseEntity<Product> save(
            @RequestBody Product product){
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping(value = ResourceEndpoint.DELETE)
    @ApiOperation("Delete Specific Product")
    public ResponseEntity delete(
            @RequestParam(value = Constants.PRODUCT_ID) int productId){
        if(productService.delete(productId)){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}


