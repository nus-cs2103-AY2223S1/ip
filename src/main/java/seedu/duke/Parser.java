package seedu.duke;

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
            super("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }
    public static class InvalidCommandException extends DukeException {
        public InvalidCommandException() {
            super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private final String[] commandWords = new String[]{"list", "mark",
            "unmark", "todo", "event", "deadline", "delete", "bye"};

    public void parse(String text) throws EmptyMessageException, InvalidCommandException {
        if (checkCommand(text, 0)) {
            this.ui.displayList(this.tasks);

        } else if (checkCommand(text, 1)) {
            int i = Integer.parseInt(text.split(" ")[1].strip());
            Task task = this.tasks.mark(i);
            ui.msg("Nice! I've marked this task as done:\n" + "\t" + task);

        } else if (checkCommand(text, 2)) {
            int i = Integer.parseInt(text.split(" ")[1].strip());
            Task task = this.tasks.unmark(i);
            ui.msg("OK, I've marked this task as not done yet:\n" + "\t" + task);

        } else if (checkCommand(text, 6)) {
            int i = Integer.parseInt(text.split(" ")[1].strip());
            this.ui.msg("Noted. I've removed this task:\n\t" + this.tasks.get(i)
                    + "\nNow you have " + (this.tasks.size() - 1) + " tasks in the list.");
            this.tasks.delete(i);

        } else if (checkCommand(text, 7)) {
            this.ui.end();

        } else { // is a task, commandwords index 3-5 inclusive
            boolean isSent = false;
            LocalDate d = null;
            Task t = null;
            if (checkCommand(text, 3)) {
                isSent = true;
                if (text.length() <= commandWords[3].length() + 1) { //check if description exists
                    throw new EmptyMessageException();
                }

                text = text.substring(commandWords[3].length() + 1);

                t = this.tasks.add(new Task(text, commandWords[3], d));

            } else if (checkCommand(text, 4)) {
                isSent = true;
                String[] temp = text.split("/at");
                text = temp[0].strip().substring(commandWords[4].length() + 1);
                d = LocalDate.parse(temp[1].strip());
                t = this.tasks.add(new Task(text, commandWords[4], d));

            } else if (checkCommand(text, 5)) {
                isSent = true;
                String[] temp = text.split("/by");
                text = temp[0].strip().substring(commandWords[5].length() + 1);
                d = LocalDate.parse(temp[1].strip());
                t = this.tasks.add(new Task(text, commandWords[5], d));
            }

            if (!isSent) {
                throw new InvalidCommandException();
            } else {
                this.ui.msg("Got it. I've added this task:\n " + "\t" + t + "\n" + "Now you have "
                        + this.tasks.size() + " tasks in the list.");
            }
        }

        try {
            this.storage.writeToFile(this.tasks);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private boolean checkCommand(String s, int i) {
        return s.length() >= commandWords[i].length() && commandWords[i].equals(s.substring(0,
                commandWords[i].length()));
    }
}
