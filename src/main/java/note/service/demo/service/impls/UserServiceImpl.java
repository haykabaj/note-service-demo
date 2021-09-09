package note.service.demo.service.impls;

import note.service.demo.model.User;
import note.service.demo.repository.UserRepository;
import note.service.demo.service.UserService;
import note.service.demo.util.ValidateEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public boolean userExist(String email) {
        return this.userRepository.existsByEmail(email);
    }

    @Override
    public User add(User user) {
        this.userRepository.save(user);
        return user;
    }

    @Override
    public Optional<User> get(String email, String password) {
        return this.userRepository.findByEmailAndPassword(email, password);
    }
}
