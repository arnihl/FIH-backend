package is.tonlistarskolifih.TonlistarskoliFIH.Service.Implementation;

import is.tonlistarskolifih.TonlistarskoliFIH.Configuration.Md5Hashing;
import is.tonlistarskolifih.TonlistarskoliFIH.Entity.User;
import is.tonlistarskolifih.TonlistarskoliFIH.Repository.UserRepository;
import is.tonlistarskolifih.TonlistarskoliFIH.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

    UserRepository repository;

    @Autowired
    public UserServiceImplementation(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        String password = Md5Hashing.md5(user.getPassword());
        user.setPassword(password);
        return repository.save(user);
    }

    @Override
    public User findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public User login(User user) {
        User exists = repository.findByName(user.getName());
        if(exists != null){
            String existsPassword = exists.getPassword();
            String userPassword = Md5Hashing.md5(user.getPassword());
            if(existsPassword.equals(userPassword)){
                return exists;
            }
        }
        return null;
    }
}
