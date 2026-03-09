package lei.ibam.library.user.controller;

import jakarta.validation.Valid;
import lei.ibam.library.user.dto.UserInputDto;
import lei.ibam.library.user.model.UserEntity;
import lei.ibam.library.user.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userSerice;

    public UserController(UserService userSerice) {
        this.userSerice = userSerice;
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUsers(@Valid @RequestBody UserInputDto userInputDto){
        UserEntity createdUser = userSerice.createUser(userInputDto);
        return  new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getUsers(){
        return new ResponseEntity<>(userSerice.getUser(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUsersById(@PathVariable Long id){
        return new ResponseEntity<>(userSerice.getUserById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUsers(@PathVariable Long id,@Valid @RequestBody UserInputDto userInputDto){
        return new ResponseEntity<>(userSerice.updateUser(id,userInputDto),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Long id){
        if(userSerice.deleteUser(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
