package lei.ibam.library.borrowBook.model;

import jakarta.persistence.*;
import lei.ibam.library.book.model.BookEntity;
import lei.ibam.library.user.model.UserEntity;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "BorrowedBooks")
public class BorrowedBookEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long borrowedBook_id;

    @ManyToOne
    private BookEntity book;

    @ManyToOne
    private UserEntity user;

    private LocalDateTime borrowDate;

    private LocalDateTime returnDate;

    public Long getBorrowedBook_id() {
        return borrowedBook_id;
    }

    public void setBorrowedBook_id(Long borrowedBook_id) {
        this.borrowedBook_id = borrowedBook_id;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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

    public boolean returned;
}
