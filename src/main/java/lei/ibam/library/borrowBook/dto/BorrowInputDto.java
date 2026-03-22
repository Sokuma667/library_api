package lei.ibam.library.borrowBook.dto;

import jakarta.validation.constraints.NotNull;

public class BorrowInputDto {

    @NotNull
    private Long bookID;

    public Long getBookID() {
        return bookID;
    }

    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }



}
