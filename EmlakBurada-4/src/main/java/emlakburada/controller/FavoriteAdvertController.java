package emlakburada.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import emlakburada.model.Advert;
import emlakburada.model.FavoriteAdvert;
import emlakburada.service.FavoriteAdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FavoriteAdvertController {

    @Autowired
    private FavoriteAdvertService favoriteAdvertService;

    @PostMapping("/favorites/{id}")
    public void save(@PathVariable(required = false) Integer id){
        favoriteAdvertService.save(id);
    }

    @GetMapping("/favorites")
    public List<FavoriteAdvert> getAllFavorites(){
        return favoriteAdvertService.getAllFavorites();
    }

    @GetMapping("/favorites/{userId}")
    public List<Advert> favoriteAdvertsByUser(@PathVariable Integer userId){

        return favoriteAdvertService.getFavoritesByUserId(userId);
    }


}
