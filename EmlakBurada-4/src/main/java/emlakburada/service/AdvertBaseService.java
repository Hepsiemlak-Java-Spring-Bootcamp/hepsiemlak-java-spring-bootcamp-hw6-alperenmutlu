package emlakburada.service;

import emlakburada.model.Advert;
import emlakburada.model.User;
import emlakburada.model.dto.reponse.AdvertResponse;
import emlakburada.model.dto.request.AdvertRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;


@Slf4j
public class AdvertBaseService {

    private static int advertNo = 38164784;

    protected Advert convertToAdvertEntity(AdvertRequest advertRequest, Optional<User> foundUser){
        Advert advert = null;

        if(foundUser.isPresent()){
            advert = new Advert();
            advert.setCreatorUser(foundUser.get());
            advertNo++;

            advert.setAdvertNo(advertNo);
            advert.setTitle(advertRequest.getTitle());
            advert.setPrice(advertRequest.getPrice());
            advert.setDescription(advertRequest.getDescription());
            advert.setActive(advertRequest.isActive());
            advert.setRealEstateType(advertRequest.getRealEstateType());

        }else{
            log.info("Kullanıcı bulunamadı!");
        }
        return advert;
    }

    protected AdvertResponse convertToAdvertResponse(Advert savedAdvert){
        AdvertResponse advertResponse = new AdvertResponse();
        advertResponse.setAdvertNo(savedAdvert.getAdvertNo());
        advertResponse.setTitle(savedAdvert.getTitle());
        advertResponse.setDescription(savedAdvert.getDescription());
        advertResponse.setUser(savedAdvert.getCreatorUser());
        advertResponse.setRealEstateType(savedAdvert.getRealEstateType());
        advertResponse.setPrice(savedAdvert.getPrice());
        advertResponse.setCreatedDate(savedAdvert.getCreatedDate());
        advertResponse.setActive(savedAdvert.isActive());

        return advertResponse;

    }
}
