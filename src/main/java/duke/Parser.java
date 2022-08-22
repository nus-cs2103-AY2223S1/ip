package duke;

import duke.exceptions.DukeException;
import duke.exceptions.DukeMissingInputException;
import duke.exceptions.DukeUnknownInputException;

public class Parser {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Parser(Ui ui, Storage sto, TaskList taskList) {
        this.ui = ui;
        this.storage = sto;
        this.tasks = taskList;
    }

    public boolean handler(String input) throws DukeException {
        String[] args = input.split(" ", 2);
        boolean isEnded = false;

        switch (args[0]) {
        case "list":
            tasks.list();
            break;
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            try {
                tasks.listAdd(args[0], args[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeMissingInputException(args[0]);
            }
            break;
        case "delete":
            try {
                tasks.listDelete(args[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeMissingInputException(args[0]);
            }
            break;
        case "mark":
            // mark is implemented as a toggle. note this.
            try {
                tasks.listToggle(args[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeMissingInputException(args[0]);
            }
            break;
        case "bye":
            isEnded = true;
            ui.exit();
            break;
        default:
            throw new DukeUnknownInputException(args[0]);
        }
        return isEnded;
    }
}
