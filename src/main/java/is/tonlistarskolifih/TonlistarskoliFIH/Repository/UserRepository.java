package is.tonlistarskolifih.TonlistarskoliFIH.Repository;

import is.tonlistarskolifih.TonlistarskoliFIH.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository <User, Long> {
    User save(User user);
    User findByName(String name);

}
