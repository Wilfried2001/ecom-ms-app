package net.wizzy.billingservice.entities;

import jakarta.persistence.*;
import lombok.*;
import net.wizzy.billingservice.models.Customer;

import java.util.Date;
import java.util.List;

@Entity @NoArgsConstructor @AllArgsConstructor @Getter @Setter
@Builder
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date billingDate;
    private Long customerId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bill")
    private List<ProductItem> productItems;
    @Transient
    private Customer customer;

    public Long getId() {
        return id;
    }

    public Date getBillingDate() {
        return billingDate;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public List<ProductItem> getProductItems() {
        return productItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBillingDate(Date billingDate) {
        this.billingDate = billingDate;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setProductItems(List<ProductItem> productItems) {
        this.productItems = productItems;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", billingDate=" + billingDate +
                ", customerId=" + customerId +
                ", productItems=" + productItems +
                ", customer=" + customer +
                '}';
    }
}
