package lei.ibam.library.user.service;

import lei.ibam.library.GlobalExeptionHandler.UserAlreadyExistsExeption;
import lei.ibam.library.GlobalExeptionHandler.UserNotExistsExeption;
import lei.ibam.library.user.dto.UserInputDto;
import lei.ibam.library.user.model.Role;
import lei.ibam.library.user.model.UserEntity;
import lei.ibam.library.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //creation d'un user
    public UserEntity createUser(UserInputDto userInputDto){
       boolean userAlreadyExists = userRepository.findUserEntityByUserName( userInputDto.getUserInputName()).isPresent();

       if(userAlreadyExists){
           throw new UserAlreadyExistsExeption("Cet utilisateur existe déjà");
       }
       UserEntity user = new UserEntity();
       user.setUserName(userInputDto.getUserInputName());
       user.setPassword(passwordEncoder.encode(userInputDto.getPasswordInput()));
       user.setRole(Role.USER);

       return userRepository.save(user);


    }

    //Voir les users
    public List<UserEntity> getUser(){
        return userRepository.findAll();
    }

    //Voir un user en connaissant son id
    public UserEntity getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotExistsExeption("Cet user n'existe pas !!!"));
    }

    //Mettre les infos d'un utilisateurs à jour
    public UserEntity updateUser(Long id,UserInputDto userInputDto){

        return userRepository.findById(id)
                .map(updatedUser->{
                        updatedUser.setUserName(userInputDto.getUserInputName());
                        updatedUser.setPassword(userInputDto.getPasswordInput());


                        boolean userAlreadyExists = userRepository.findUserEntityByUserName(updatedUser.getUserName()).isPresent();

                        if (userAlreadyExists){
                            throw new UserAlreadyExistsExeption("Cet user existe déjà");
                        }

                        return userRepository.save(updatedUser);
                })
                .orElseThrow(()->new UserNotExistsExeption("Cet user n'existe pas"));

    }

    //Supprimer un user
    public boolean deleteUser(Long id){
        if(userRepository.findById(id).isPresent()){
            UserEntity deletedUser = userRepository.findById(id).get();
            userRepository.delete(deletedUser);
            return true;
        }
        return false;

    }
}
