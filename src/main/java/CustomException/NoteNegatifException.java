package CustomException;

public class NoteNegatifException extends RuntimeException{
    public NoteNegatifException(String message) {
        super(message);
    }
}
