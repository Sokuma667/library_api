package lei.ibam.library.GlobalExeptionHandler;

public class HttpMessageNotReadableException extends RuntimeException{
    public HttpMessageNotReadableException(String message){
        super(message);
    }
}
