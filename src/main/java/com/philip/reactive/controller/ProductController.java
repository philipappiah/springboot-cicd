package com.philip.reactive.controller;


import com.philip.reactive.entity.Product;
import com.philip.reactive.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {


    @Autowired
    private ProductService productService;

    @GetMapping
    public Flux<Product> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Mono<Product> getProduct(@PathVariable String id){
      return productService.getProduct(id);
    }

    @GetMapping("/product-range")
    public Flux<Product> getProductBetweenRange(@RequestParam("min") double min,@RequestParam("max") double max){
    return productService.getProductInRange(min, max);
    }

    @PostMapping
    public Mono<Product> saveProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    @PutMapping("/{id}")
    public Mono<Product> saveProduct(@RequestBody Product product, @PathVariable String id ){


        return productService.updateProduct(product, id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id){
        return productService.deleteProduct(id);
    }
}
