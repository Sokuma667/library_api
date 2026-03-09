package lei.ibam.library.book.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class BookInputDto {

    @NotNull(message = "veuillez renseigner le nom du livre!!!")
    @NotBlank(message = "le nom du livre est incorrect!!!")
    public String bookName;



    //Pour le int les contraintes @Null et @Blank ne sont pas acceptés, pour les utiliser il faudra changer le int en Integer
    @Positive(message = "le nombre de page est doit etre > 0")
    public int bookPages;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookPages() {
        return bookPages;
    }

    public void setBookPages(int bookPages) {
        this.bookPages = bookPages;
    }



}
