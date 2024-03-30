package africa.semicolon.shoppersDelight.data.repositories;

import africa.semicolon.shoppersDelight.data.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
