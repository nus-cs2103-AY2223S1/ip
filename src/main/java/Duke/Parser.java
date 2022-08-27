package Duke;

import java.util.Scanner;

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
     *
     * @param input that is being read
     * @throws DukeException when input is invalid
     */
    public void parse(String input) throws DukeException{
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
            } else {
                commands.searchDate(input);
            }
        }
}