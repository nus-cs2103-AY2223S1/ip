package seedu.duke;

import seedu.duke.Task.Task;
import seedu.duke.Task.TaskSorter;
import seedu.duke.Task.Todo;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Represents a Parser, makes sense of user input
 */
public class Parser {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Instantiates a new Parser object
     */
    public Parser(TaskList taskL, Ui ui, Storage storage) {
        this.tasks = taskL;
        this.ui = ui;
        this.storage = storage;
    }
    public static class DukeException extends Exception {
        public DukeException(String msg) {
            super(msg);
        }
    }
    public static class EmptyMessageException extends DukeException {
        public EmptyMessageException() {
            super("OOPS!!! The description of a todo cannot be empty.");
        }
    }
    public static class InvalidCommandException extends DukeException {
        public InvalidCommandException() {
            super("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private final String[] commandWords = new String[]{"list", "mark",
            "unmark", "todo", "event", "deadline", "delete", "bye", "find", "update"};

    /**
     * Makes sense of the user input text
     *
     * @param text String to be parsed
     */
    public String parse(String text) throws EmptyMessageException, InvalidCommandException {
        String msg = "";
        if (checkCommand(text, 0)) {
            msg = this.ui.displayList(this.tasks.getTasks());

        } else if (checkCommand(text, 1)) {
            int i = Integer.parseInt(text.split(" ")[1].strip());
            Task task = this.tasks.mark(i);
            msg = ui.msg("Nice! I've marked this task as done:\n" + "\t" + task);

        } else if (checkCommand(text, 2)) {
            int i = Integer.parseInt(text.split(" ")[1].strip());
            Task task = this.tasks.unmark(i);
            msg = ui.msg("OK, I've marked this task as not done yet:\n" + "\t" + task);

        } else if (checkCommand(text, 6)) {
            int i = Integer.parseInt(text.split(" ")[1].strip());

            assert i >= 0 : "index should be non-negative";
            assert i < this.tasks.size() : "index should be valid list item";

            msg = this.ui.msg("Noted. I've removed this task:\n\t" + this.tasks.get(i)
                    + "\nNow you have " + (this.tasks.size() - 1) + " task(s) in the list.");
            this.tasks.delete(i);

        } else if (checkCommand(text, 7)) {
            msg = this.ui.end();

        } else if (checkCommand(text, 8)) {
            text = text.substring(commandWords[8].length() + 1);
            msg = this.ui.displayList(this.tasks.filter(text));

        } else if (checkCommand(text, 9)) { //update {index} {category} {detail}, e.g update 1 date 2019-12-23
            String[] temp = text.split(" ", 4);
            int i = Integer.parseInt(temp[1]);
            Task task = this.tasks.update(i, temp[2], temp[3]);
            msg = ui.msg("OK, I've updated this task:\n" + "\t" + task);

        } else { // is a task, commandwords index 3-5 inclusive
            Task task = TaskSorter.sortTaskFromInput(text);
            if (task == null) {
                throw new InvalidCommandException();
            } else {
                msg = this.ui.msg("Got it. I've added this task:\n " + "\t" + task + "\n" + "Now you have "
                        + (this.tasks.size()+1) + " task(s) in the list.");
            }
            this.tasks.add(task);
        }

        try {
            this.storage.writeToFile(this.tasks);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return msg;
    }

    /**
     * Determines if given string contains specified command
     *
     * @param s String to be checked for command
     * @param i index of command to be checked
     * @return whether command is present in string
     */
    private boolean checkCommand(String s, int i) {
        return s.length() >= commandWords[i].length() && commandWords[i].equals(s.substring(0,
                commandWords[i].length()));
    }
}
