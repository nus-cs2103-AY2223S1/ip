package duke.main;

public class DukeException extends Exception {
    private String description;

    public DukeException(String description) {
        this.description = description;
    }

    @Override
    public String getMessage() {
        return "OOPS!!! " + this.description;
    }
}
