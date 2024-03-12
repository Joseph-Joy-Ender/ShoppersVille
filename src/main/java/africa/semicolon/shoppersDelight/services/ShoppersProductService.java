package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.data.models.Product;
import africa.semicolon.shoppersDelight.data.repositories.ProductRepository;
import africa.semicolon.shoppersDelight.dtos.request.AddProductRequest;
import africa.semicolon.shoppersDelight.dtos.response.AddProductResponse;
import africa.semicolon.shoppersDelight.dtos.response.ProductResponse;
import africa.semicolon.shoppersDelight.exceptions.ProductNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import static java.math.BigInteger.ONE;

@Service
@AllArgsConstructor
public class ShoppersProductService implements ProductService{
    private final ModelMapper mapper = new ModelMapper();

    private final ProductRepository productRepository;
    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;

    @Override
    public AddProductResponse addProduct(AddProductRequest request) {
        ModelMapper mapper = new ModelMapper();
        Product product = mapper.map(request, Product.class);
        Product savedProduct = productRepository.save(product);

        return mapper.map(savedProduct, AddProductResponse.class);
    }

    @Override
    public ProductResponse getProductBy(Long id) {
        return mapper.map(findById(id), ProductResponse.class);
    }

    private Product findById(Long id){
        return productRepository.findById(id).orElseThrow(
                ()->new ProductNotFoundException("Product not found"));
    }

    @Override
    public List<ProductResponse> getProducts(int page, int size) {
        Page<Product> productPage = productRepository.findAll(createPageRequest(page, size));
        return productPage.getContent()
                .stream()
                .map(product -> mapper.map(product, ProductResponse.class))
                .toList();
    }

    private static PageRequest createPageRequest(int page, int size) {
        if (page < DEFAULT_PAGE_NUMBER) page = DEFAULT_PAGE_NUMBER;
        if (size < ONE.intValue()) size = DEFAULT_PAGE_SIZE;
        page = page - ONE.intValue();
        return PageRequest.of(page, size);
    }
}
