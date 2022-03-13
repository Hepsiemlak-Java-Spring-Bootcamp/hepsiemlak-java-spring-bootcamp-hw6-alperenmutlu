package emlakburada.service;

import emlakburada.client.BannerClient;
import emlakburada.client.request.AddressRequest;
import emlakburada.client.request.BannerRequest;
import emlakburada.model.Advert;
import emlakburada.model.User;
import emlakburada.model.dto.reponse.AdvertResponse;
import emlakburada.model.dto.request.AdvertRequest;
import emlakburada.queue.QueueService;
import emlakburada.repository.AdvertRepository;
import emlakburada.repository.FavoriteAdvertRepository;
import emlakburada.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AdvertService extends  AdvertBaseService{

    @Autowired
    private AdvertRepository advertRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BannerClient bannerClient;

    @Autowired
    private QueueService queueService;

    private BannerRequest bannerRequest = new BannerRequest();

    public void saveAdvert(@RequestBody AdvertRequest advertRequest) throws Exception {

        Optional<User> foundUser = userRepository.findById(advertRequest.getUserId());

        Advert advert = convertToAdvertEntity(advertRequest, foundUser);

        if(advert == null){
            throw new Exception("İlan kaydedilemedi");
        }
        bannerRequest.setAdvertNo(1);
        bannerRequest.setPhone("123456789");
        bannerRequest.setTotal(1);
        bannerRequest.setAddress(new AddressRequest("İstanbul","Kadıköy","Tam adres"));
        bannerClient.saveBanner(bannerRequest);

        EmailMessage emailMessage = new EmailMessage("alperenmutlu1903@gmail.com");
        queueService.sendMessage(emailMessage);

        //queueService.sendMessage(emailMessage);
        Advert savedAdvert = advertRepository.save(advert);

    }


    public List<AdvertResponse> getAllAdvert(){
        List<Advert> adverts = advertRepository.findAll();
       return adverts.stream().map(advert -> convertToAdvertResponse(advert)).collect(Collectors.toList());

    }

    public AdvertResponse getAdvertByAdvertId(int id){
        Optional<Advert> advert = advertRepository.findById(id);
        return convertToAdvertResponse(advert.get());
    }




}
