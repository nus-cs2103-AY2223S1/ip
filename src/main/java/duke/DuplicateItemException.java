package duke;

public class DuplicateItemException extends DukeException{
    public DuplicateItemException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return String.format("%s '%s' is in the list already!", super.toString(), this.getMessage());
    }
}

