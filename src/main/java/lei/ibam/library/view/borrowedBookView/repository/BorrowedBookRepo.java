package lei.ibam.library.view.borrowedBookView.repository;

import lei.ibam.library.view.borrowedBookView.model.BorrowedBookView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowedBookRepo extends JpaRepository<BorrowedBookView,Long> {

}
