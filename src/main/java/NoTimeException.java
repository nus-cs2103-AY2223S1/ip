public class NoTimeException extends DukeException{
    String type;

    NoTimeException(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("OOPS!!! The time of a %s cannot be empty.", this.type);
    }
}
