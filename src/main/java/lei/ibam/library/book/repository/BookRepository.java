package lei.ibam.library.book.repository;

import lei.ibam.library.book.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity,Long> {

    Optional <BookEntity> findByNameAndPages(String name,int pages);

 
}
