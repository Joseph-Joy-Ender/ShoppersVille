package africa.semicolon.shoppersDelight.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {
    private T data;
}
