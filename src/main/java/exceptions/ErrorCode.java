package exceptions;

/**
 * @author Kapil
 * @created 09/06/2020 - 5:34 PM
 * @project mock
 */
public interface ErrorCode {

    /**
     * Provides an app-specific error code to help find out exactly what happened.
     * It's a human-friendly identifier for a given exception.
     *
     * @return a short text code identifying the error
     */
    String getErrorCode();
}