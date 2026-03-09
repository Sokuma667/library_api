package lei.ibam.library.user.repository;

import lei.ibam.library.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional <UserEntity> findUserEntityByUserName(String userName);
}
