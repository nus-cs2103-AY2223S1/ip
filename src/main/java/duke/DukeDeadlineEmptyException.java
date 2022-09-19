package duke;

public class DukeDeadlineEmptyException extends DukeException {
    DukeDeadlineEmptyException() {
        super();
    }
    public String toString() {
        Ui ui = new Ui();
        return ui.getEmptyDeadlineExceptionUi();
    }
}
