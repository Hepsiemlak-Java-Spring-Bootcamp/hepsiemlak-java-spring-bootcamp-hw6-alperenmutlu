package emlakburada.service;

import emlakburada.dto.request.BannerRequest;
import emlakburada.dto.response.BannerResponse;
import emlakburada.model.Banner;
import emlakburada.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BannerService extends BannerBaseService {

    @Autowired
    private BannerRepository bannerRepository;


    public void saveBanner(BannerRequest bannerRequest){
        Banner banner = bannerRepository.save(convertToBannerEntity(bannerRequest));
    }

    public List<BannerResponse> getAllBanner(){
        List<Banner> banners = bannerRepository.findAll();
        return banners.stream().map(banner -> convertToBannerResponse(banner)).collect(Collectors.toList());

    }




}
