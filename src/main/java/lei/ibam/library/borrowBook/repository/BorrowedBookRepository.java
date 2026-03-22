package lei.ibam.library.borrowBook.repository;

import lei.ibam.library.book.model.BookEntity;
import lei.ibam.library.borrowBook.model.BorrowedBookEntity;
import lei.ibam.library.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowedBookRepository extends JpaRepository<BorrowedBookEntity,Long> {

    @Override
    Optional<BorrowedBookEntity> findById(Long id);
     List <BorrowedBookEntity> findByUser(UserEntity user);
}
