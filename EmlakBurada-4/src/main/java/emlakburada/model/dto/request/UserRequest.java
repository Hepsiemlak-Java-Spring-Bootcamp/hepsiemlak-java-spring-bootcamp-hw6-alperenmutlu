package emlakburada.model.dto.request;

import emlakburada.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private UserType userType;
    private String name;
    private String email;
    private String photo;
    private String bio;


}
