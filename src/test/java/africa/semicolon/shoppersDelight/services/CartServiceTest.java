package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.data.models.Cart;
import africa.semicolon.shoppersDelight.data.models.Item;
import africa.semicolon.shoppersDelight.data.repositories.CartRepository;
import africa.semicolon.shoppersDelight.dtos.request.AddToCartRequest;
import africa.semicolon.shoppersDelight.dtos.response.ProductResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CartServiceTest {
    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;


    @Test
    public void testThatCartIsCreated(){
        Cart cart = cartService.createCart();
        assertNotNull(cart);
    }


    @Test
    public void testThatCartHasAListOfProduct(){
        Cart cart = cartService.createCart();
        List<Item> productList = cart.getItems();
        assertThat(productList.size()).isEqualTo(0);
    }

    @Test
    public void testThatCreateTwoCartAndCartRepositoryCountIsTwo(){
        Long count = cartRepository.count();
        Cart cart = cartService.createCart();
        Cart cart1 = cartService.createCart();
        assertEquals(count+ 2, cartRepository.count());
    }

    @Test
    public void testThatItemCanBeAddedToCart(){
        AddToCartRequest request = new AddToCartRequest();
        Cart cart = cartService.createCart();

        List<ProductResponse> products = productService.getProducts(1, 5);
        Long productId = products.get(products.size() -1).getId();


    }
}
