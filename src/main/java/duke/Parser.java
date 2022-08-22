package duke;

import duke.exceptions.DukeException;
import duke.exceptions.DukeMissingInputException;
import duke.exceptions.DukeUnknownInputException;

public class Parser {
    private Ui ui;
    private Storage sto;
    private TaskList tasks;

    /**
     * Creates an instance of a Parser object
     * @param ui
     * @param sto
     * @param tl
     */
    public Parser(Ui ui, Storage sto, TaskList tl) {
        this.ui = ui;
        this.sto = sto;
        this.tasks = tl;
    }

    /**
     * Accepts user input and generates duke output accordingly
     * @param input
     * @return boolean end to determine execution finish
     * @throws DukeException when runtime errors occur
     */
    public boolean handler(String input) throws DukeException {
        String[] args = input.split(" ", 2);
        boolean end = false;

        switch (args[0]) {
            case "list":
                tasks.list();
                break;
            case "todo":
            case "deadline":
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
            // mark is implemented as a toggle. note this.
            case "mark":
                try {
                    tasks.listToggle(args[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeMissingInputException(args[0]);
                }
                break;
            case "bye":
                end = true;
                ui.exit();
                break;
            default:
                throw new DukeUnknownInputException(args[0]);
        }
        return end;
    }
}
