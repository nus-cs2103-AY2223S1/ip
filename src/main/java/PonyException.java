import java.lang.RuntimeException;
public class PonyException extends RuntimeException {

    public PonyException(String message) {
        super(message);
    }

    private String one = ":( OOPS!!! I'm sorry, but I don't know what that means...";
    private String two = ":( OOPS!!! Please provide the details!!";
    private String three = ":( OOPS!!! Please provide the correct details!!";
    private String four = ":( OOPS!!! Please provide the details in the following format: ";

    //Invalid command
    public static class inputError extends RuntimeException {
        public inputError() {
            super(":( OOPS!!! I'm sorry, but I don't know what that means...");
        }

    }

    //Task to mark not provided
    public static class taskMissingError extends RuntimeException {
        public taskMissingError() {
            super(":( OOPS!!! Please provide the details!!");
        }
    }

    //Invalid input for task
    public static class taskInputError extends RuntimeException {
        public taskInputError() {
            super(":( OOPS!!! Please provide the correct details!!");
        }
    }

    //Invalid format for task
    public static class taskFormatError extends RuntimeException {
        public taskFormatError(String msg) {
            super(":( OOPS!!! Please provide the details in the following format: " + msg + "!!");
        }
    }

}
