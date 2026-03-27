package lei.ibam.library.view.bookView.model;

import jakarta.persistence.*;
import lei.ibam.library.book.model.Category;
import org.hibernate.annotations.Immutable;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "books_view")
@Immutable
public class BookView {

    @Id
    @Column(name = "bookId")
    private Long bookId;

    private String name;
    private String author;
    private int pages;

    @Enumerated(EnumType.STRING)
    private Category category;

    private int quantity;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Enumerated(EnumType.STRING)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
