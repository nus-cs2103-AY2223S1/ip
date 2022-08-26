package duke;

public class DukeDeadlineEmptyException extends DukeException {
    DukeDeadlineEmptyException() {
        super();
    }
    public String toString() {
        Ui ui = new Ui();
        return ui.getEmptyDeadlineExceptionUi();
    }
    public static void main(String[] args) {
        System.out.println("h");
    }
}
