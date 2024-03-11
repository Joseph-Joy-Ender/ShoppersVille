package africa.semicolon.shoppersDelight.data.repositories;

import africa.semicolon.shoppersDelight.data.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
