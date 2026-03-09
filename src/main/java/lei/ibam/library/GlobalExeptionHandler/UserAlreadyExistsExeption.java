package lei.ibam.library.GlobalExeptionHandler;

public class UserAlreadyExistsExeption extends RuntimeException {

    public UserAlreadyExistsExeption(String message){
        super(message);
    }
}
