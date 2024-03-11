package africa.semicolon.shoppersDelight.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRegisterRequest {

    private String email;
    private String password;
}
