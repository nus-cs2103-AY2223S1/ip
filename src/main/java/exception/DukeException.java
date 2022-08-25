package exception;

public class DukeException extends Exception {
    private final ErrorCode errorCode;

    public DukeException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String errorMessage() {
        String errorMsg;
        switch(errorCode) {
            case UNKNOWN_CMD:
                errorMsg = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
                break;
            case MISSING_TODO_ITEM:
                errorMsg = "☹ OOPS!!! The description of a todo cannot be empty.";
                break;
            case UNKNOWN_TASK_ENCODING:
                errorMsg = "☹ OOPS!!! Unable to parse one of the saved tasks. This task will be skipped.";
                break;
            default:
                errorMsg = "☹ OOPS!!! Unknown duke error occurred. :-(";
        }
        return errorMsg;
    }

    public enum ErrorCode {
        UNKNOWN_CMD,
        MISSING_TODO_ITEM,
        UNKNOWN_TASK_ENCODING
    }
}
