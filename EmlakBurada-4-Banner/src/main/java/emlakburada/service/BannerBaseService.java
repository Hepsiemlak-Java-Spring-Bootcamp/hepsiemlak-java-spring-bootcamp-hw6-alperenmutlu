package emlakburada.service;


import emlakburada.dto.request.AddressRequest;
import emlakburada.dto.request.BannerRequest;
import emlakburada.dto.response.BannerResponse;
import emlakburada.model.Address;
import emlakburada.model.Banner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BannerBaseService {

    protected Banner convertToBannerEntity(BannerRequest bannerRequest){
        Banner banner = new Banner();
        banner.setAdvertNo(bannerRequest.getAdvertNo());
        banner.setPhone(bannerRequest.getPhone());
        banner.setTotal(bannerRequest.getTotal());
        return banner;
    }

   protected BannerResponse convertToBannerResponse(Banner banner){
        BannerResponse bannerResponse = new BannerResponse();
        bannerResponse.setAdvertNo(banner.getAdvertNo());
        bannerResponse.setPhone(banner.getPhone());
        bannerResponse.setTotal(banner.getTotal());

        return bannerResponse;

   }

}
