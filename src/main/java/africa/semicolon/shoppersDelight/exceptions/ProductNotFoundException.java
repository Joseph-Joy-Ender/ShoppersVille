package africa.semicolon.shoppersDelight.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String productNotFound) {
        super(productNotFound);
    }
}
