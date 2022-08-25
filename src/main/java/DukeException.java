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
            case INVALID_DEADLINE_DATETIME_FORMAT:
                String dateFormat = "\t Please ensure date time is of the " +
                        "following format: yyyy-mm-dd hh:mm:ss";
                errorMsg = "☹ OOPS!!! Unknown to parse date time.\n" + dateFormat;
                break;
            case INVALID_EVENT_DATETIME_FORMAT:
                dateFormat = "\t Please ensure date time is of the " +
                        "following format: yyyy-mm-dd hh:mm:ss hh:mm:ss hh:mm:ss";
                errorMsg = "☹ OOPS!!! Unknown to parse date time.\n" + dateFormat;
                break;
            default:
                errorMsg = "☹ OOPS!!! Unknown duke error occurred. :-(";
        }
        return errorMsg;
    }

    public enum ErrorCode {
        UNKNOWN_CMD,
        MISSING_TODO_ITEM,
        INVALID_DEADLINE_DATETIME_FORMAT,
        INVALID_EVENT_DATETIME_FORMAT
    }
}
