package exceptions;

/**
 * @author Kapil
 * @created 09/06/2020 - 5:13 PM
 * @project mock
 */
public class MissingTemplateException extends RuntimeException implements ErrorCode {
    public MissingTemplateException(final String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return "NE-001";
    }
}
