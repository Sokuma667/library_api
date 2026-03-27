package lei.ibam.library.view.bookView.repository;

import lei.ibam.library.view.bookView.model.BookView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookViewRepo extends JpaRepository<BookView,Long> {
    Optional<BookView> findByNameAndAuthor(String name,String author);

    @Override
    Optional<BookView> findById(Long id);
}
