package delivery.stork.exception;

public class EmailAlreadyExist extends RuntimeException{
    public EmailAlreadyExist(){

    }
    public EmailAlreadyExist(String message){
        super(message);
    }
}
