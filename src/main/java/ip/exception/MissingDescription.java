package ip.exception;

public class MissingDescription extends DukeException {
    @Override
    public String toString() {
        return "The commands todo, deadline, and event need a task description to be specified.\n" +
                "For example, entering `todo Feed fishes` will add a task called \"Feed fishes\".";
    }
}
