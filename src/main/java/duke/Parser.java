package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Parser makes sense of the user inputs.
 *
 * @author Alvin Jiang Min Jun
 * @version v0.2
 */
public class Parser {

    private TaskList tasks;
    final static ArrayList<String> validCommands = new ArrayList<>(List.of("list", "mark", "unmark", "todo", "deadline", "event", "delete", "bye", "find"));

    /**
     * Creates an instance of a Parser object.
     *
     * @param tasks The tasks that will be operated on after parsing user inputs.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Evaluates if the command given by user is bye
     */
    public boolean isBye(String str) {
        return str.equals("bye");
    }

    /**
     * Analyses the user input and returns the command word used.
     *
     * @param str The entire user input.
     * @return String the command that is called by the user.
     * @throws DukeException Exception thrown when the input does not have a valid command.
     */
    public String parseCommand(String str) throws DukeException {
        String command = str.split(" ", 2)[0];
        assert !validCommands.isEmpty() : "possible command list invalid";
        if (validCommands.contains(command)) {
            return command;
        }
        throw new DukeException("OOPS!!! I'm sorry but I don't know what that means :-(");
    }

    /**
     * Analyses the user input, as well as the command and modifies the taskList
     * as necessary.
     *
     * @param command The command word given by the user.
     * @param str     The entire user input.
     * @return String The output specified the operation done.
     * @throws DukeException Exception thrown when the input string is empty.
     */
    public String executeCommand(String command, String str) throws DukeException {
        try {
            switch (command) {
            case "list":
                return this.tasks.displayList();
            case "mark":
                return this.tasks.markTask(taskIndex(str, command));
            case "unmark":
                return this.tasks.unmarkTask(taskIndex(str, command));
            case "todo":
                checkValidString(str, command);
                String s3 = str.substring(5);
                Todo t = new Todo(s3);
                return this.tasks.addTask(t);
            case "deadline":
                checkValidString(str, command);
                String s4 = str.substring(9);
                String[] deadlineResult = s4.split(" /by ");
                Deadline d = new Deadline(deadlineResult[0], deadlineResult[1]);
                return this.tasks.addTask(d);
            case "event":
                checkValidString(str, command);
                String s5 = str.substring(6);
                String[] eventResult = s5.split(" /at ");
                this.tasks.checkClash(eventResult[1]);
                Event e = new Event(eventResult[0], eventResult[1]);
                return this.tasks.addTask(e);
            case "delete":
                return this.tasks.deleteTask(taskIndex(str, command));
            case "find":
                String s7 = str.substring(5);
                return this.tasks.findTask(s7);
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
        return null;
    }
    /**
     * Analyses the user input, as well as the command and returns
     * the index of the task in question
     *
     * @param command The command word given by the user.
     * @param str     The entire user input.
     * @return int The index of the specific task.
     */
    private int taskIndex(String str, String command) {
        String temp = "";
        switch (command) {
        case "mark":
            temp = str.substring(5);
            break;

        case "unmark":
        case "delete":
            temp = str.substring(7);
            break;
        }
        assert !temp.equals("") : "unable to obtain string";
        return Integer.parseInt(temp) - 1;
    }

    /**
     * Analyses the user input, as well as the command and throws
     * an Exception if the description of the input is empty.
     *
     * @param command The command word given by the user.
     * @param str     The entire user input.
     * @throws DukeException Exception thrown when the input string is empty.
     */
    private void checkValidString(String str, String command) throws DukeException {
        switch(command) {
        case "todo":
            if (str.length() == 4) {
                throw new DukeException("OOPS!!! I'm sorry but description of a todo cannot be empty");
            }
        case "deadline":
            if (str.length() == 8) {
                throw new DukeException("OOPS!!! I'm sorry but description of a deadline cannot be empty");
            }
        case "event":
            if (str.length() == 5) {
                throw new DukeException("OOPS!!! I'm sorry but description or time period of an event cannot be empty");
            }
        }

    }

}
