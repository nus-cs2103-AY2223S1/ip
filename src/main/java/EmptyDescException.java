public class EmptyDescException extends Exception {
    String desc;
    public EmptyDescException(String message) {
        super(message);
        this.desc = message;
    }

    @Override
    public String toString() {
        return "The description of a " + desc + " cannot be empty.";
    }
}
