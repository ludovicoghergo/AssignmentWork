package ludovico.ghergo.exceptions;

public class InvalidParamException extends Exception
{
    public InvalidParamException() {
        super("This is a custom exception.");
    }

    public InvalidParamException(String message) {
        super(message);
    }

    public InvalidParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidParamException(Throwable cause) {
        super(cause);
    }
}
