package lei.ibam.library.book.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lei.ibam.library.book.model.Category;

public class BookInputDto {

    @NotNull(message = "veuillez renseigner le nom du livre!!!")
    @NotBlank(message = "le nom du livre est incorrect!!!")
    public String bookName;



    //Pour le int les contraintes @Null et @Blank ne sont pas acceptés, pour les utiliser il faudra changer le int en Integer
    @Positive(message = "le nombre de page est doit etre > 0")
    public int bookPages;

    @NotNull(message = "veuillez renseigner l'auteur de ce livre!!!")
    @NotBlank(message = "le nom de l'auteur est incorrect!!!")
    public String bookAuthor;


   
    @NotNull(message = "veuillez renseigner la categorie de ce livre!!!")
    @Enumerated(EnumType.STRING)
    public Category bookCategory;

    public Category getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(Category bookCategory) {
        this.bookCategory = bookCategory;
    }





    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }


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
