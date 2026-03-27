package lei.ibam.library.view.userView.repository;

import lei.ibam.library.view.userView.model.UserView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserViewRepo extends JpaRepository<UserView,Long> {
    Optional<UserView> findByUserName(String username);
}
