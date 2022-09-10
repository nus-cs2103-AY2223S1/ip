package duke.exceptions;

public class DuplicateTaskException extends DukeException {

    @Override
    public String toString() {
        return "Task already in list!";
    }
}

