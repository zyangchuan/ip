package mono.exception;

/**
 * Indicates that the user submitted an empty input line.
 */
public class EmptyInputException extends MonoException {
    public EmptyInputException(String message) {
        super(message);
    }
}
