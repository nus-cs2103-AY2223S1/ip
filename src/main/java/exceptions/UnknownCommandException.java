package exceptions;

public class UnknownCommandException extends Exception {
        public UnknownCommandException() {
            super("Unknown command. Please Try Again");
        }
}
