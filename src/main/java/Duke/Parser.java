package Duke;

import java.util.Scanner;

public class Parser {
    private Duke duke;
    private Ui ui;
    private Commands commands;

    public Parser(Duke duke, Ui ui) {
        this.duke = duke;
        this.ui = ui;
        this.commands = new Commands();
    }

    public void run(String input) {
            if (input.equals("list")) {
                commands.printList();
            } else if (input.startsWith("mark")) {
                commands.markDone(input);
            } else if (input.startsWith("unmark")) {
                commands.unmark(input);
            } else if (input.startsWith("todo")) {
                try {
                    if (input.length() <= 5) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    commands.todo(input);
                } catch (Exception c) {
                    System.out.println(c);
                }
            } else if (input.startsWith("deadline")) {
                try {
                    if (input.length() <= 9) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    commands.deadline(input);
                } catch (Exception c) {
                    System.out.println(c);
                }
            } else if (input.startsWith("event")) {
                try {

                    if (input.length() <= 6) {
                        throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                    }
                    commands.event(input);
                } catch (Exception c) {
                    System.out.println(c);
                }
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