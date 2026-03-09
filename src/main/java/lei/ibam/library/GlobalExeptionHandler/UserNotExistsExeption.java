package lei.ibam.library.GlobalExeptionHandler;

public class UserNotExistsExeption extends RuntimeException{
    public UserNotExistsExeption(String message){
        super(message);
    }
}
