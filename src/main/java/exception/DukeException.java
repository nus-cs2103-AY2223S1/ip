package exception;

/**
 * Represents exceptions that occur from Duke operations. Uses an error code
 * to represent and differentiate between different types of exception.
 *
 * @author Bryan Lim Jing Xiang
 */
public class DukeException extends Exception {
    private final ErrorCode errorCode;

    /**
     * @param errorCode Represents the type of exception that occurred
     */
    public DukeException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return Error message pertaining to the exception
     */
    public String errorMessage() {
        String errorMsg;
        switch (errorCode) {
        case UNKNOWN_CMD:
            errorMsg = "OOPS!!! I'm sorry, but I don't know what that means :-(";
            break;
        case MISSING_TODO_ITEM:
            errorMsg = "OOPS!!! The description of a todo cannot be empty.";
            break;
        case UNKNOWN_TASK_ENCODING:
            errorMsg = "OOPS!!! Unable to parse one of the saved tasks. This task will be skipped.";
            break;
        case INVALID_DEADLINE_DATETIME_FORMAT:
            String dateFormat = "\t Please ensure date time is of the "
                    + "following format: yyyy-mm-dd hh:mm:ss";
            errorMsg = "OOPS!!! Unable to parse date time for deadline.\n" + dateFormat;
            break;
        case INVALID_EVENT_DATETIME_FORMAT:
            dateFormat = "\t Please ensure date time is of the "
                    + "following format: yyyy-mm-dd hh:mm:ss hh:mm:ss";
            errorMsg = "OOPS!!! Unable to parse date time for event.\n" + dateFormat;
            break;
        default:
            errorMsg = "OOPS!!! Unknown duke error occurred. :-(";
        }
        return errorMsg;
    }

    /**
     * A representation of possible exceptions that occur from Duke operations
     */
    public enum ErrorCode {
        UNKNOWN_CMD,
        MISSING_TODO_ITEM,
        UNKNOWN_TASK_ENCODING,
        INVALID_DEADLINE_DATETIME_FORMAT,
        INVALID_EVENT_DATETIME_FORMAT
    }
}
