package emlakburada.service;

import emlakburada.model.Advert;
import emlakburada.model.FavoriteAdvert;
import emlakburada.model.User;
import emlakburada.repository.AdvertRepository;
import emlakburada.repository.FavoriteAdvertRepository;
import emlakburada.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FavoriteAdvertService  extends FavoriteAdvertBaseService{

    @Autowired
    private AdvertRepository advertRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavoriteAdvertRepository favoriteAdvertRepository;

    public void save(Integer id){
       Advert findaAdvert = advertRepository.getAdvertById(id);
       favoriteAdvertRepository.save(convertToFavoriteAdvertEntity(findaAdvert));

    }

    public List<FavoriteAdvert> getAllFavorites(){
        return favoriteAdvertRepository.findAll();
    }

    public List<Advert> getFavoritesByUserId(Integer userId){
        User user = userRepository.getById(userId);

        List<FavoriteAdvert> favoriteAdvertsByUser = user.getFavoriteAdverts();

        List<Advert> adverts = new ArrayList<>();
        for(FavoriteAdvert favoriteAdvert : favoriteAdvertsByUser){
            adverts.add(convertToAdvert(favoriteAdvert));
        }

        return adverts;

    }

}
