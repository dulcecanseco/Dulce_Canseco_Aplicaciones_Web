package mx.edu.utez.proyecto4D.exception.customExceptions;

public class CustomBadRequestException extends RuntimeException{
    public CustomBadRequestException(String mensaje){
        super(mensaje);
    }
}
