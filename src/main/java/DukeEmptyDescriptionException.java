public class DukeEmptyDescriptionException extends DukeException {

    public DukeEmptyDescriptionException() {
        super("Commands todo, deadline and event should have a description");
    }

}
