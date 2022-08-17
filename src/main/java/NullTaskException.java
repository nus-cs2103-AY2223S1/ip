public class NullTaskException extends DukeException {

    private String t;

    public NullTaskException(String message, String t) {
        super(message);
        this.t = t;
    }

    @Override
    public String toString() {
        return super.toString() + "The description of a "
                + t + " cannot be empty.";
    }
}
