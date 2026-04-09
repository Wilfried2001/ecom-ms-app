package net.wizzy.billingservice.controllers;

import net.wizzy.billingservice.entities.Bill;
import net.wizzy.billingservice.feign.CustomerServiceRestClient;
import net.wizzy.billingservice.feign.InventoryServiceRestClient;
import net.wizzy.billingservice.repositorys.BillRepository;
import net.wizzy.billingservice.repositorys.ProductItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bills")
public class BillRestController {
    private final  BillRepository billRepository;
    private final ProductItemRepository productItemRepository;
    private final CustomerServiceRestClient customerServiceRestClient;
    private final InventoryServiceRestClient inventoryServiceRestClient;

    public BillRestController(BillRepository billRepository, ProductItemRepository productItemRepository, CustomerServiceRestClient customerServiceRestClient, InventoryServiceRestClient inventoryServiceRestClient) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerServiceRestClient = customerServiceRestClient;
        this.inventoryServiceRestClient = inventoryServiceRestClient;
    }

    @GetMapping("/{id}")
    public Bill getBill(@PathVariable Long id){
        Bill bill = billRepository.findById(id).get();
        bill.setCustomer(customerServiceRestClient.findCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(pi->{
            pi.setProduct(inventoryServiceRestClient.findProductById(pi.getProductId()));
        });
        return bill;
    }

}
