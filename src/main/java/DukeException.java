public class DukeException extends Exception{
    String description;

    DukeException(String message) {
        super(message);
    }

    DukeException(String description, String message) {
        super(message);
        this.description = description;
    }

    @Override
    public String toString() {
        return "DukeException detected.";
    }
}
