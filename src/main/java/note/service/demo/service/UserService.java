package note.service.demo.service;

import note.service.demo.model.User;

import java.util.Optional;

public interface UserService {

    boolean userExist(String email);

    User add(User user);

    Optional<User> get(String email, String password);
}
