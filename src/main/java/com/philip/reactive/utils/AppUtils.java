package com.philip.reactive.utils;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.philip.reactive.dto.ProductDto;
import com.philip.reactive.entity.Product;
import org.springframework.beans.BeanUtils;
import reactor.core.publisher.Mono;

public class AppUtils {

    public static ProductDto entityDto(Product product){
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return  productDto;
    }



    public  static Product DtoEntity(ProductDto productDto){
        Product product = new Product();

        BeanUtils.copyProperties(productDto, product);
        return product;
    }
}
