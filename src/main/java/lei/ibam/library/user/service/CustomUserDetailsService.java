package lei.ibam.library.user.service;

import lei.ibam.library.user.model.UserEntity;
import lei.ibam.library.user.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1️⃣ Chercher l'utilisateur dans la base
        UserEntity userEntity = userRepository.findUserEntityByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Cet utilisateur n'existe pas"));

        // con⃣vertir le rôle en GrantedAuthority
        // Ici, on suppose que ton UserEntity a un champ "role" de type String (ex: "USER" ou "ADMIN")
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + userEntity.getRole());

        // 3️⃣ Créer un UserDetails avec username, password encodé et rôle
        return new org.springframework.security.core.userdetails.User(
                userEntity.getUserName(),     // username
                userEntity.getPassword(),     // password déjà encodé avec BCrypt
                Collections.singleton(authority) // collection de rôles
        );
    }
}
