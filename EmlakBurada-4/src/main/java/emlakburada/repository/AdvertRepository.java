package emlakburada.repository;

import emlakburada.model.Advert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdvertRepository extends JpaRepository<Advert,Integer> {
    public Advert getAdvertById(Integer id);

}
