package CustomException;

public class NoteNegatifException extends Exception{
    public NoteNegatifException(String message) {
        super(message);
    }
}
