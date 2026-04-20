package org.example.module3.web;

import org.example.module3.domain.Person;
import org.example.module3.domain.Product;
import org.example.module3.repository.ProductRepository;
import org.example.module3.service.PersonService;
import org.example.module3.util.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @GetMapping("/product")
    public ApiResponse<List<Product>> getAll() {

        //return ApiResponse.error("Invalid JSON format", 400);
        //return personService.findAll();
        return ApiResponse.success(productRepository.getProductsByType("asd"));
    }

    @GetMapping("/product/search")
    public ApiResponse<List<Product>> search(@RequestParam String search) {
        return ApiResponse.success(productRepository.findByNameContainingIgnoreCase(search));
    }

}
