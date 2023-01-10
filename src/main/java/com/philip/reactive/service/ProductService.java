package com.philip.reactive.service;



import com.philip.reactive.entity.Product;
import com.philip.reactive.repository.ProductRepository;
import com.philip.reactive.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;


    public Flux<Product> getProducts(){
        return repository.findAll();
    }

    public Mono<Product> getProduct(String id){
        return  repository.findById(id);
    }


    public Flux<Product> getProductInRange(double min, double max){
        return repository.findByPriceBetween(Range.closed(min, max));
    }

    public Mono<Product> saveProduct(Product product){
        return repository.save(product);
                //productDtoMono.map(AppUtils::DtoEntity).flatMap(repository::insert).map(AppUtils::entityDto);
    }

    public Mono<Product> updateProduct(Product productDto,String id){


        return repository.findById(id)
                .map(e -> {
                    e.setId(id);
                    if(productDto.getPrice() != null) e.setPrice(productDto.getPrice());
                    if(productDto.getName() != null) e.setName(productDto.getName());
                    if(productDto.getQty() != null) e.setQty(productDto.getQty());
                    return e ;

                })
                .flatMap(repository::save);
    }

    public Mono<Void> deleteProduct(String id){
        return repository.deleteById(id);
    }
}
