package emlakburada;

import emlakburada.dto.request.AddressRequest;
import emlakburada.dto.request.BannerRequest;
import emlakburada.dto.response.BannerResponse;
import emlakburada.model.Address;
import emlakburada.model.Banner;
import emlakburada.repository.BannerRepository;
import emlakburada.service.BannerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BannerServiceTest {

    @InjectMocks
    private BannerService bannerService;

    @Mock
    private BannerRepository bannerRepository;


    @Test
    void getAllBanner(){

        Mockito
                .when(bannerRepository.findAll())
                .thenReturn(prepareMockBannerList());

        List<BannerResponse> bannerResponseList =  bannerService.getAllBanner();

        assertNotEquals(0, bannerResponseList.size());
        assertEquals(1, bannerResponseList.get(1).getAdvertNo());

    }

    private List<Banner> prepareMockBannerList() {
        List<Banner> bannerList = new ArrayList<>();
        bannerList.add(prepareBanner(0));
        bannerList.add(prepareBanner(1));
        bannerList.add(prepareBanner(2));
        return bannerList;
    }


    @Test
    void saveBannerTest(){

        Mockito
                .when(bannerRepository.save(any()))
                .thenReturn(prepareBanner(0));

        bannerService.saveBanner(prepareBannerRequest());

        assertEquals(0,prepareBannerRequest().getAdvertNo());
        assertEquals("123456",prepareBannerRequest().getPhone());
        assertEquals(1,prepareBannerRequest().getTotal());
        assertEquals("İstanbul",prepareBannerRequest().getAddress().getProvince());


    }

    private Banner prepareBanner(Integer advertNo) {
        Banner banner = new Banner();
        banner.setAddress(new Address(1,"İstanbul","Kadıköy","addressDesc"));
        banner.setAdvertNo(advertNo);
        banner.setPhone("123456");
        banner.setTotal(1);
        return banner;
    }

    private BannerRequest prepareBannerRequest(){
        BannerRequest bannerRequest = new BannerRequest();
        bannerRequest.setAddress(new AddressRequest("İstanbul","Kadıköy","addressdesc"));
        bannerRequest.setPhone("123456");
        bannerRequest.setAdvertNo(0);
        bannerRequest.setTotal(1);
        return bannerRequest;
    }


}
