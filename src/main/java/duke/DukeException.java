package duke;

public class DukeException extends Exception{
    String description;

    public DukeException(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
