package Cinnamon.Handler;
import Cinnamon.Cinnamon;
import Cinnamon.Exception.DukeException;

/**
 * Parser that parses input into the program
 */
public class Parser {
    private final Cinnamon cinnamon;
    private final Ui ui;
    private final Commands commands;

    public Parser(Cinnamon cinnamon, Ui ui) {
        this.cinnamon = cinnamon;
        this.ui = ui;
        this.commands = new Commands();
    }

    /**
     * @param input that is being read
     * @throws DukeException when input is invalid
     */
    public void parse(String input) throws DukeException {
        if (input.equals("list")) {
            commands.printList();
        } else if (input.startsWith("mark")) {
            commands.markDone(input);
        } else if (input.startsWith("unmark")) {
            commands.unmark(input);
        } else if (input.startsWith("todo")) {
            if (input.length() <= 5) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            commands.todo(input);
        } else if (input.startsWith("deadline")) {
            if (input.length() <= 9) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            commands.deadline(input);
        } else if (input.startsWith("event")) {
            if (input.length() <= 6) {
                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
            }
            commands.event(input);
        } else if (input.startsWith("delete")) {
            commands.delete(input);
        } else if (input.startsWith("find")) {
            commands.searchName(input);
        } else if (input.equals("bye")) {
            ui.bye();
        } else if (input.startsWith("dateFilter")){
            commands.searchDate(input);
        } else if (input.startsWith("tag")) {
            commands.tagTask(input);
        } else if (input.startsWith("print")) {
            commands.printTaskTag(input);
        } else if (input.startsWith("remove")) {
            commands.removeTag(input);
        } else if (input.startsWith("#")) {
            commands.searchTag(input);
        }
    }
}
