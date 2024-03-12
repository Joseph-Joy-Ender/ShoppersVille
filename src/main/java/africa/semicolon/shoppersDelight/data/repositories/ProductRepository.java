package africa.semicolon.shoppersDelight.data.repositories;

import africa.semicolon.shoppersDelight.data.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
