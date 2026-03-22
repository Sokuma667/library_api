package lei.ibam.library.GlobalExeptionHandler;

public class BookCategoryNotExistsException extends RuntimeException{
    public BookCategoryNotExistsException(String message){
        super(message);
    }
}
