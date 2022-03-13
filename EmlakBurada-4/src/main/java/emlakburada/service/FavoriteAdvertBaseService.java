package emlakburada.service;


import emlakburada.model.Advert;
import emlakburada.model.FavoriteAdvert;

public class FavoriteAdvertBaseService {

    public FavoriteAdvert convertToFavoriteAdvertEntity(Advert advert){
        FavoriteAdvert favoriteAdvert = new FavoriteAdvert();
        favoriteAdvert.setId(advert.getId());
        favoriteAdvert.setUser(advert.getCreatorUser());
        favoriteAdvert.setAdvert(advert);
        return  favoriteAdvert;
    }

    public Advert convertToAdvert(FavoriteAdvert favoriteAdvert){
        Advert advert = favoriteAdvert.getAdvert();

        advert.setId(favoriteAdvert.getId());
        advert.setCreatorUser(favoriteAdvert.getUser());

        return advert;

    }

}
