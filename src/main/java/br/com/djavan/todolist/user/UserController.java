package br.com.djavan.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel){
        var user = userRepository.findByUsername(userModel.getUsername());

        if(user != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username já existe!");
        }
        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.ok(userCreated);
    }
}
