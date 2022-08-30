package duke;

public class DukeUnknownWordException extends DukeException {
    DukeUnknownWordException() {
        super();
    }
    public String toString() {
        Ui ui = new Ui();
        return ui.getUnknownWordExceptionUi();
    }
}
