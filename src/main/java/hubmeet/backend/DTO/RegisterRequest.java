package hubmeet.backend.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String userID;
    private String password;
    private String email;
    private String phone;
}
