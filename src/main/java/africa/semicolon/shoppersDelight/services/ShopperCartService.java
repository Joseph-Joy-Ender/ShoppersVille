package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.data.models.Cart;
import africa.semicolon.shoppersDelight.data.repositories.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShopperCartService implements CartService{
    private final CartRepository cartRepository;
    @Override
    public Cart createCart() {
        Cart cart = new Cart();
        return cartRepository.save(cart);
    }
}
