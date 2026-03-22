package lei.ibam.library.GlobalExeptionHandler;

public class BookNotInStockException extends RuntimeException{
    public BookNotInStockException(String message){
        super(message);
    }
}
