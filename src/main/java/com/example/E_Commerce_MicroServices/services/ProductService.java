package com.example.E_Commerce_MicroServices.services;


import com.example.E_Commerce_MicroServices.models.Product;
import com.example.E_Commerce_MicroServices.repositories.ProductRepository;
import com.example.E_Commerce_MicroServices.specification.ProductSpecification;
import com.example.E_Commerce_MicroServices.system.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(ProductNotFoundException::new);
    }
    public void deleteProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        product.ifPresent(productRepository::delete);
    }

    public Optional<Product> updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(updatedProduct.getName());
                    existingProduct.setDescription(updatedProduct.getDescription());
                    existingProduct.setPrice(updatedProduct.getPrice());
                    return productRepository.save(existingProduct);
                });
    }

    public List<Product> getFilteredProducts(String name, String category, BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findAll(ProductSpecification.filterBy(name, category, minPrice, maxPrice));
    }

    public List<Product> getProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }
}


