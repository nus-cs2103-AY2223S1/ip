package duke;

public class DukeTodoEmptyException extends DukeException {
    DukeTodoEmptyException() {
        super();
    }
    public String toString() {
        Ui ui = new Ui();
        return ui.getEmptyTodoExceptionUi();
    }
}
