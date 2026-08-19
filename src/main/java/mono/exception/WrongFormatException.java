package mono.exception;

/**
 * Indicates that a task command does not follow one of the supported formats.
 */
public class WrongFormatException extends MonoException {
    public WrongFormatException(String message) {
        super(message);
    }
}
