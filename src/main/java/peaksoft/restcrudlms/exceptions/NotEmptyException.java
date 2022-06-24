package peaksoft.restcrudlms.exceptions;

public class NotEmptyException extends RuntimeException {
    public NotEmptyException() {
    }

    public NotEmptyException(String message) {
        super(message);
    }
}
