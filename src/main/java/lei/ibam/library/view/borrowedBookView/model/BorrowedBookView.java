package lei.ibam.library.view.borrowedBookView.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "borrowed_books_view")
@Immutable
public class BorrowedBookView {

    @Id
    @Column(name = "borrowedBookId")
    private Long borrowedBookId;

    private String userName;

    private String bookName;

    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private boolean returned;

    public Long getBorrowedBookId() {
        return borrowedBookId;
    }

    public void setBorrowedBookId(Long borrowedBookId) {
        this.borrowedBookId = borrowedBookId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDateTime borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}
