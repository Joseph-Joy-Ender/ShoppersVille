package africa.semicolon.shoppersDelight.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddToCartRequest {
    private Long productId;
    private Long CartId;

}
