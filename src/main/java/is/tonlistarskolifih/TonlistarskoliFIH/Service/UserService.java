package is.tonlistarskolifih.TonlistarskoliFIH.Service;

import is.tonlistarskolifih.TonlistarskoliFIH.Entity.User;

public interface UserService {
    User save(User user);
    User findByName(String name);
    User login(User user);
}
