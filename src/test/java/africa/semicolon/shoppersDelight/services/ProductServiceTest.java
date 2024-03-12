package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.request.AddProductRequest;
import africa.semicolon.shoppersDelight.dtos.response.AddProductResponse;
import africa.semicolon.shoppersDelight.dtos.response.ProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.processing.SQL;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void addProductTest(){
        AddProductRequest request = new AddProductRequest();
        request.setName("Chocolate");
        request.setPrice(BigDecimal.TEN);
        request.setDescription("Yummy, yum, yum!");
        request.setQuantity(10);

        AddProductResponse response = productService.addProduct(request);
        log.info("Product added :: {}", response);
        assertThat(response).isNotNull();
    }

    @Test
    @Sql(scripts = {"/scripts/insert.sql"})
     public void getProduct(){
        ProductResponse product = productService.getProductBy(200L);

        log.info("found product ->{}", product);
        assertThat(product).isNotNull();

    }

    @Test
    @Sql(scripts = {"/scripts/insert.sql"})
    public void getProductsTest(){
        List<ProductResponse> products = productService.getProducts(1, 5);
        assertThat(products).hasSize(2);
    }
}
