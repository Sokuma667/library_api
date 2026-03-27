package lei.ibam.library.GlobalExeptionHandler;

public class BookAlreadyReturnedException extends RuntimeException{
    public BookAlreadyReturnedException(String message){
        super(message);
    }
}
