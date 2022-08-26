package duke;
public class DukeEventEmptyException extends DukeException {
     DukeEventEmptyException() {
        super();
    }
    public String toString() {
         Ui ui = new Ui();
         return ui.getEmptyEventExceptionUi();
    }
}
