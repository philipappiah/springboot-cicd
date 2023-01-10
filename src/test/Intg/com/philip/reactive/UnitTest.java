package com.philip.reactive;


import com.philip.reactive.controller.ProductController;
import com.philip.reactive.entity.Product;
import com.philip.reactive.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;
import java.util.List;

@WebFluxTest(controllers = ProductController.class)
@AutoConfigureWebTestClient
public class UnitTest {

    final static String PRODUCT_URL = "/products";

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ProductService productService;

    @Test
    void getAllMoviesInfo() {
        webTestClient.get()
                .uri(PRODUCT_URL)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }

    }
