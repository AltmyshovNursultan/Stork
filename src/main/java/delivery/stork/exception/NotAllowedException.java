package delivery.stork.exception;

public class NotAllowedException extends RuntimeException{
    public NotAllowedException(){}
    public NotAllowedException(String message){super(message);}
}
