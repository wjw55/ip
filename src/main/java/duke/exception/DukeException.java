package duke.exception;

/**
 * Represents exceptions specific to the Duke application.
 *
 * This exception is thrown when the user input is invalid
 * or when other Duke-related errors occur.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException with the specified detail message.
     *
     * @param s The error message explaining the cause of the exception.
     */
    public DukeException(String s) {
        super(s);
    }
}
