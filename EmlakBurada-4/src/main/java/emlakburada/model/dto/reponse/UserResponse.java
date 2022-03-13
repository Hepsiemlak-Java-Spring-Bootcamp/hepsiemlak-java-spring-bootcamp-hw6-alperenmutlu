package emlakburada.model.dto.reponse;

import emlakburada.enums.UserType;
import lombok.Data;

@Data
public class UserResponse {
    private UserType userType;
    private String name;
    private String email;
    private String photo;
    private String bio;
}
