package ip.exception;

public class IndexNotSpecified extends DukeException {
    @Override
    public String toString() {
        return "The commands mark, unmark, and delete need an index to be specified.\n"
               + "For example, entering `delete 3` will delete the task at index 3.";
    }
}
