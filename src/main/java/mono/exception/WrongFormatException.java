package mono.exception;

/**
 * Indicates that a task command does not follow one of the supported formats.
 */
public class WrongFormatException extends MonoException {
    public WrongFormatException(String message) {
        super(message);
    }

    /**
     * Creates a format exception while preserving the underlying cause.
     *
     * @param message user-facing explanation of the format error
     * @param cause exception that caused the format error
     */
    public WrongFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
