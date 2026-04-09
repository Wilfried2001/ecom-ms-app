package net.wizzy.customerservice.repositorys;

import net.wizzy.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource // Cette annotation permet de créer automatiquement des points de terminaison REST pour le dépôt. Elle vous permet d'effectuer des opérations CRUD sur l'entité Customer via des requêtes HTTP sans avoir à écrire de code de contrôleur supplémentaire.
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
