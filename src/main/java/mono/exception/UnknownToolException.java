package mono.exception;

/**
 * Indicates that no registered tool matches the requested tool name.
 */
public class UnknownToolException extends MonoException {
    public UnknownToolException(String message) {
        super(message);
    }
}
