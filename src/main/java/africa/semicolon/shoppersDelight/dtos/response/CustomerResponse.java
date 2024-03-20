package africa.semicolon.shoppersDelight.dtos.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class CustomerResponse {
    private Long id;
    private String email;
    private String password;
    private List<NotificationResponse> notifications;
    private String address;
    private String phoneNumber;
}
