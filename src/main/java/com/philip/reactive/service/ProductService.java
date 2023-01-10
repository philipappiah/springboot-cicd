package com.philip.reactive.service;


import com.philip.reactive.dto.ProductDto;
import com.philip.reactive.entity.Product;
import com.philip.reactive.repository.ProductRepository;
import com.philip.reactive.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;


    public Flux<ProductDto> getProducts(){
        return repository.findAll().map(AppUtils::entityDto);
    }

    public Mono<ProductDto> getProduct(String id){
        return  repository.findById(id).map(AppUtils::entityDto);
    }


    public Flux<ProductDto> getProductInRange(double min, double max){
        return repository.findByPriceBetween(Range.closed(min, max));
    }

    public Mono<ProductDto> saveProduct(ProductDto productDtoMono){
        return repository.save(AppUtils.DtoEntity(productDtoMono)).map(AppUtils::entityDto);
                //productDtoMono.map(AppUtils::DtoEntity).flatMap(repository::insert).map(AppUtils::entityDto);
    }

    public Mono<ProductDto> updateProduct(ProductDto productDto,String id){



        return repository.findById(id)
                .map(e -> {
                    e.setId(id);
                    if(productDto.getPrice() > 0) e.setPrice(productDto.getPrice());
                    if(productDto.getName() != null) e.setName(productDto.getName());
                    if(productDto.getQty() > 0) e.setQty(productDto.getQty());
                    return e ;

                })
                .flatMap(repository::save)
                .map(AppUtils::entityDto);
    }

    public Mono<Void> deleteProduct(String id){
        return repository.deleteById(id);
    }
}
