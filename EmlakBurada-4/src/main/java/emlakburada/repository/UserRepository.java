package emlakburada.repository;

import emlakburada.model.Advert;
import emlakburada.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    public User getById(Integer id);
}
