package mx.edu.utez.proyecto4D.exception.customExceptions;

public class CustomNotFoundException extends RuntimeException{
    public CustomNotFoundException(String mensaje){
        super(mensaje);
    }
}
