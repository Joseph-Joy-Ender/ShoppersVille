package africa.semicolon.shoppersDelight.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
public class Notification {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String message;
}
