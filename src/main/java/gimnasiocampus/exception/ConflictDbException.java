package gimnasiocampus.exception;

public class ConflictDbException extends RuntimeException {

    public ConflictDbException(String message) {
        super(message);
    }

    public ConflictDbException(String message, Throwable cause) {
        super(message, cause);
    }
}
