package Duke.Handler;
import Duke.Duke;
import Duke.Exception.DukeException;

/**
 * Parser that parses input into the program
 */
public class Parser {
    private final Duke duke;
    private final Ui ui;
    private final Commands commands;

    public Parser(Duke duke, Ui ui) {
        this.duke = duke;
        this.ui = ui;
        this.commands = new Commands();
    }

    /**
     * @param input that is being read
     * @throws DukeException when input is invalid
     */
    public void parse(String input) throws DukeException {
        String[] inputCmd = input.split(" ", 2);

        switch (inputCmd[0]) {
        case "list":
            commands.printList();
        case "mark":
            commands.markDone(input);
        case "unmark":
            commands.unmark(input);
        case "todo":
            if (input.length() <= 5) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            commands.todo(input);
        case "deadline":
            if (input.length() <= 9) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            commands.deadline(input);
        case "event":
            if (input.length() <= 6) {
                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
            }
            commands.event(input);
        case "delete":
            commands.delete(input);
        case "find":
            commands.searchName(input);
        case "bye":
            ui.bye();
        case "dateFilter":
            commands.searchDate(input);
        case "tag":
            commands.tagTask(input);
        }
    }
}
