package emlakburada.services;

import emlakburada.client.BannerClient;
import emlakburada.enums.UserType;
import emlakburada.model.Advert;
import emlakburada.model.User;
import emlakburada.model.dto.reponse.AdvertResponse;
import emlakburada.model.dto.request.AdvertRequest;
import emlakburada.queue.QueueService;
import emlakburada.repository.AdvertRepository;
import emlakburada.repository.UserRepository;
import emlakburada.service.AdvertService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;

@SpringBootTest
class AdvertServiceTest {

    @InjectMocks
    private AdvertService advertService;

    @Mock
    private AdvertRepository advertRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BannerClient bannerClient;

    @Mock
    private QueueService queueService;



    @Test
    void saveAdvertWithoutUser() {

        Exception thrown = assertThrows(Exception.class, () ->{
            advertService.saveAdvert(prepareAdvertRequest());
        },"İlan kaydedilemedi");

        assertEquals("İlan kaydedilemedi", thrown.getMessage());

    }

    @Test
    void saveAdvertWithUser() throws Exception {

        AdvertRequest advertRequest = prepareAdvertRequest();
        Optional<User> user = Optional.of(prepareUser());

        Mockito
                .when(userRepository.findById(advertRequest.getUserId()))
                .thenReturn(user);

        Mockito
                .when(advertRepository.save(any()))
                .thenReturn(prepareAdvert("selam"));


        
        assertDoesNotThrow(() ->{
            advertService.saveAdvert(advertRequest);
            assertEquals("selam",advertRequest.getTitle());
            verify(queueService).sendMessage(any());
            verify(bannerClient).saveBanner(any());
        });

    }

    @Test
    void getAllAdvertTest(){

        Mockito
                .when(advertRepository.findAll())
                .thenReturn(prepareMockAdvertList());

        List<AdvertResponse> advertList = advertService.getAllAdvert();

        assertNotEquals(0,advertList.size());
        assertThat(advertList.size()).isNotZero();

        for (AdvertResponse response : advertList) {
            assertThat(response.getAdvertNo()).isEqualTo(0);

            assertEquals(new BigDecimal(12345), response.getPrice());

        }


    }

    private List<Advert> prepareMockAdvertList() {
        List<Advert> advertList = new ArrayList<>();

        advertList.add(prepareAdvert("selam"));
        advertList.add(prepareAdvert("selam2"));
        advertList.add(prepareAdvert("selam3"));
        return advertList;

    }

    private Advert prepareAdvert(String title) {
        Advert advert = new Advert();
        advert.setAdvertNo(0);
        advert.setTitle("selam");
        advert.setPrice(new BigDecimal(12345));
        return advert;
    }

    private User prepareUser() {
        User user = new User(UserType.INDIVIDUAL,"alperen","alperen@alperen.com");
        return user;
    }


    private AdvertRequest prepareAdvertRequest() {
        AdvertRequest advertRequest = new AdvertRequest();
        advertRequest.setUserId(5);
        advertRequest.setTitle("selam");
        advertRequest.setPrice(new BigDecimal(150000));
        advertRequest.setDescription("sa");
        return advertRequest;
    }
}