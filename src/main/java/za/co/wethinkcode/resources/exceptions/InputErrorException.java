package za.co.wethinkcode.resources.exceptions;

public class InputErrorException extends RuntimeException {
    public InputErrorException(String message){
        super(message);
    }
}
