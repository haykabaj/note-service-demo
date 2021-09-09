package note.service.demo.controller.users;

import note.service.demo.model.User;
import note.service.demo.service.impls.UserServiceImpl;
import note.service.demo.util.ValidateEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

@RestController
public class UserController {

    private final UserServiceImpl userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userService = userServiceImpl;
    }


    @GetMapping("api/users/_search")
    public ResponseEntity<User> getUsersByEmailAndPassword(@RequestParam("email")
                                                           @NotEmpty @Size(max = 30) String email,
                                                           @RequestParam("password")
                                                           @NotEmpty @Size(max = 30) String password) {
        try {
            Optional<User> userEntity = this.userService.get(email, password);
            return userEntity.map(body -> ResponseEntity.ok(body)).orElseGet(()
                    -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            logger.error("Error", e);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @PostMapping("/api/users")
    public ResponseEntity<User> addUser(@RequestBody @NotNull User user) {
        if (!this.userService.userExist(user.getEmail())) {
            if (ValidateEmail.validateEmail(user.getEmail())){
                 this.userService.add(user);
                return ResponseEntity.ok(user);
            }
            logger.warn("Email should be valid");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


}
