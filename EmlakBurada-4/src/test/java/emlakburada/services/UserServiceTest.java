package emlakburada.services;

import emlakburada.enums.UserType;
import emlakburada.model.User;
import emlakburada.model.dto.reponse.UserResponse;
import emlakburada.model.dto.request.UserRequest;
import emlakburada.repository.UserRepository;
import emlakburada.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;


import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setup() {

        Mockito
                .when(userRepository.findAll())
                .thenReturn(prepareMockUserList());

    }

    private List<User> prepareMockUserList() {
        List<User> userList = new ArrayList<User>();
        userList.add(new User(UserType.INDIVIDUAL, "cem", "cem@patika.com"));
        userList.add(new User(UserType.INDIVIDUAL, "emre", "emre@patika.com"));
        return userList;
    }

    @Test
    void getAllUserTest() {

        List<UserResponse> allUser = userService.getAllUser();

        assertNotNull(allUser);

        assertThat(allUser.size()).isNotZero();
    }


    @Test
    void saveUserTest() {

        userService.saveUser(prepareUser());

        Mockito.verify(userRepository).save(any());

    }
    private UserRequest prepareUser() {
        return new UserRequest(UserType.INDIVIDUAL, "cem", "", null, null);
    }

}
