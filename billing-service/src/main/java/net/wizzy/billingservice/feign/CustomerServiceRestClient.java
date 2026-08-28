package net.wizzy.billingservice.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import net.wizzy.billingservice.models.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "customer-service")
// Cette interface permet de communiquer avec Customer service
@FeignClient(
        name = "customer-service",
        url = "${customer.service.url}"
)
public interface CustomerServiceRestClient {
    @GetMapping("/customers/{id}")
    @CircuitBreaker(name = "customerService", fallbackMethod = "findCustomerByIdFallback")
    Customer findCustomerById(@PathVariable Long id);

    default Customer findCustomerByIdFallback(Long id, Exception e) { // le circuirBreaker permet que lorqu'un microservice fait appel a un autre microservice et que celui ci n'est pas disponible que sa renvoie des informations au client qui peuvent provenir du cache pour que l'application ne s'arrete pas
        e.printStackTrace();
        // Return a default Customer object or null
        Customer defaultCustomer = new Customer();
        defaultCustomer.setId(id);
        defaultCustomer.setName("Unknown Customer");
        defaultCustomer.setEmail("unknown@gmail.com");
        return defaultCustomer;
    }
}
