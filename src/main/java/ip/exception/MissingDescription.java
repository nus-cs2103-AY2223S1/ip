package ip.exception;

/**
 * Exception thrown when task description is not specified.
 */
public class MissingDescription extends DukeException {
    @Override
    public String toString() {
        return "The commands todo, deadline, and event need a task description to be specified.\n"
               + "Example: `todo Feed fishes` will add a task called \"Feed fishes\".";
    }
}
