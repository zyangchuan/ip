package mono.exception;

/**
 * Indicates that a requested task ID does not refer to an existing task.
 */
public class NonExistentException extends MonoException {
    public NonExistentException(String message) {
        super(message);
    }
}
