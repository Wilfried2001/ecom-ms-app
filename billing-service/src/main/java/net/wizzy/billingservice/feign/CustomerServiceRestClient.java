package net.wizzy.billingservice.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import net.wizzy.billingservice.models.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
public interface CustomerServiceRestClient {
    @GetMapping("/customers/{id}")
    @CircuitBreaker(name = "customerService", fallbackMethod = "findCustomerByIdFallback")
    Customer findCustomerById(@PathVariable Long id);

    default Customer findCustomerByIdFallback(Long id, Exception e) {
        e.printStackTrace();
        // Return a default Customer object or null
        Customer defaultCustomer = new Customer();
        defaultCustomer.setId(id);
        defaultCustomer.setName("Unknown Customer");
        defaultCustomer.setEmail("unknown@gmail.com");
        return defaultCustomer;
    }
}
