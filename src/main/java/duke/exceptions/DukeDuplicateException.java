package duke.exceptions;

public class DukeDuplicateException extends DukeException{

    public DukeDuplicateException() {
        super("Similar task is already in Tasklist");
    }
}
