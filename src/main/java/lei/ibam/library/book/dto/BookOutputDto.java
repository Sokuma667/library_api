package lei.ibam.library.book.dto;

import lei.ibam.library.book.model.Category;

public class BookOutputDto {


    private String bookOutputName;
    private int bookOutputPage;
    private String bookOutputAuthor;
    private Category bookOutputCategory;
    public int bookOutputQuantity;


    public String getBookOutputName() {
        return bookOutputName;
    }

    public void setBookOutputName(String bookOutputName) {
        this.bookOutputName = bookOutputName;
    }

    public int getBookOutputPage() {
        return bookOutputPage;
    }

    public void setBookOutputPage(int bookOutputPage) {
        this.bookOutputPage = bookOutputPage;
    }

    public String getBookOutputAuthor() {
        return bookOutputAuthor;
    }

    public void setBookOutputAuthor(String bookOutputAuthor) {
        this.bookOutputAuthor = bookOutputAuthor;
    }

    public Category getBookOutputCategory() {
        return bookOutputCategory;
    }

    public void setBookOutputCategory(Category bookOutputCategory) {
        this.bookOutputCategory = bookOutputCategory;
    }

    public int getBookOutputQuantity() {
        return bookOutputQuantity;
    }

    public void setBookOutputQuantity(int bookOutputQuantity) {
        this.bookOutputQuantity = bookOutputQuantity;
    }


}
