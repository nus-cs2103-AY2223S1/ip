public class UnknownInputException extends Exception {
    public UnknownInputException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "I do not understand what you mean, please accept my apology";
    }

}
