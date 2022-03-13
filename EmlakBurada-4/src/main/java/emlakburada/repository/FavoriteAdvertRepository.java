package emlakburada.repository;

import emlakburada.model.FavoriteAdvert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteAdvertRepository extends JpaRepository<FavoriteAdvert, Integer> {
}
