package mono.exception;

public class MonoException extends Exception {
    public MonoException(String message) {
        super(message);
    }

    /**
     * Creates an application exception while preserving its underlying cause.
     *
     * @param message user-facing explanation of the error
     * @param cause underlying exception
     */
    public MonoException(String message, Throwable cause) {
        super(message, cause);
    }
}
