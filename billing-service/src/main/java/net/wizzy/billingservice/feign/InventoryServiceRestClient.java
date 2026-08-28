package net.wizzy.billingservice.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import net.wizzy.billingservice.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "inventory-service")
@FeignClient(
        name = "inventory-service",
        url = "${inventory.service.url}"
)
public interface InventoryServiceRestClient {
    @GetMapping("/products/{id}")
    @CircuitBreaker(name = "inventory-Service", fallbackMethod = "findProductByIdFallback")
    Product findProductById(@PathVariable Long id);

    default Product findProductByIdFallback(Long id, Exception e) {
        e.printStackTrace();
        // Return a default Product object or null
        Product defaultProduct = new Product();
        defaultProduct.setId(id);
        defaultProduct.setName("Unknown Product");
        defaultProduct.setPrice(0.0);
        defaultProduct.setQuantity(500);
        return defaultProduct;
    }
}
