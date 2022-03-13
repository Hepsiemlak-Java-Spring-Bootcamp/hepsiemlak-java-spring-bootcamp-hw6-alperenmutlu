package emlakburada;

import emlakburada.dto.AuthRequest;
import emlakburada.dto.AuthResponse;
import emlakburada.entity.User;
import emlakburada.entity.enums.UserType;
import emlakburada.repository.AuthRepository;
import emlakburada.service.AuthService;
import emlakburada.util.JwtUtil;
import emlakburada.util.UserUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private AuthRepository authRepository;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private UserUtil userUtil;


    @Test
    @DisplayName("Kullanıcı yokken token oluşturmayı deniyoruz.")
    void getTokenWithoutUser(){

        Exception thrown = assertThrows(Exception.class, () ->{
            authService.getToken(prepareAuthRequest1());
        },"User not found");
        assertEquals("User not found", thrown.getMessage());

    }

    @Test
    @DisplayName("Kullanıcı var fakat şifreler eşleşmiyor.")
    void getTokenWithoutValidPassword(){

        User user = prepareUser1();

        Mockito
                .when(authRepository.findByEmail(prepareAuthRequest1().getEmail()))
                .thenReturn(user);

        //Mockito
        //        .when(UserUtil.isValidPassword(prepareAuthRequest().getPassword(), prepareUser().getPassword()))
        //        .thenReturn(any());

        boolean result = UserUtil.isValidPassword(prepareAuthRequest1().getPassword(), prepareUser1().getPassword());

        Exception thrown = assertThrows(Exception.class, () ->{
            authService.getToken(prepareAuthRequest1());
            assertEquals(false,result);
        },"User's password not valid");
        assertEquals("User's password not valid", thrown.getMessage());

    }

    @Test
    @DisplayName("Kullanıcı var ve şifreler eşleşiyor.")
    void getTokenWithUserAndValidPassword(){

        User user = prepareUser2();


        Mockito
                .when(authRepository.findByEmail(prepareAuthRequest2().getEmail()))
                .thenReturn(user);


        Mockito
                .when(jwtUtil.generateToken(prepareUser2()))
                .thenReturn(prepareMockToken());


        boolean result = UserUtil.isValidPassword(prepareAuthRequest2().getPassword(), prepareUser2().getPassword());

        assertDoesNotThrow(() ->{

            assertEquals(true,result);
            AuthResponse authResponse = authService.getToken(prepareAuthRequest2());
            assertNotNull(authResponse);
            //assertNotNull(authResponse);

        });


    }

    private AuthResponse prepareMockAuthResponse(){
        AuthResponse authResponse = new AuthResponse("alperen");
        return authResponse;
    }

    private String prepareMockToken() {
        return "alperen";
    }

    private User prepareUser1() {
        User user = new User();
        user.setUserType(UserType.INDIVIDUAL);
        user.setEmail("alperen@alperen.com");
        user.setPassword("12345");
        return user;
    }

    private AuthRequest prepareAuthRequest1() {
        AuthRequest authRequest = new AuthRequest("alperen@alperen.com","123456");
        return authRequest;
    }

    private User prepareUser2(){
        User user = new User();
        user.setUserType(UserType.INDIVIDUAL);
        user.setEmail("alperen@alperen.com");
        user.setPassword("123456");
        return user;
    }


    private AuthRequest prepareAuthRequest2() {
        AuthRequest authRequest = new AuthRequest("alperen@alperen.com","123456");
        return authRequest;
    }



}
