package com.example.shoppingCart.service;
import com.example.shoppingCart.model.ProductRequest;
import com.example.shoppingCart.repository.ProductRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ProductRequestService {

    @Autowired
    private ProductRequestRepository productRequestRepository;

    public ProductRequest getProductRequestById(String cartId) {
        return productRequestRepository.findById(cartId).orElse(null);
    }

    public void addProductRequest(ProductRequest productRequest) {
        productRequestRepository.save(productRequest);
    }

    public void removeProductRequest(String cartId) {
        productRequestRepository.deleteById(cartId);
    }

    public void updateProductRequestQuantity(ProductRequest productRequest) {
        productRequestRepository.save(productRequest);
    }

}