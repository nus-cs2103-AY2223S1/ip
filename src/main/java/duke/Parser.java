package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Parser makes sense of the user inputs.
 *
 * @author Alvin Jiang Min Jun
 * @version v0.1
 */
public class Parser {

    private TaskList tasks;
    final static ArrayList<String> validCommands = new ArrayList<>(List.of("list", "mark", "unmark", "todo", "deadline", "event", "delete", "bye", "find"));

    /**
     * Parser constructor that creates an instance of a Parser object.
     *
     * @param tasks The tasks that will be operated on after parsing user inputs.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }
    /**
     * A method which evaluates if the command given by user is bye
     */
    public boolean isBye(String str) {
        return str.equals("bye");
    }

    /**
     * A method which analyses the user input and returns the command word used.
     *
     * @param str The entire user input.
     * @return String the command that is called by the user.
     * @throws DukeException Exception thrown when the input does not have a valid command.
     */
    public String parseCommand(String str) throws DukeException {
        String command = str.split(" ", 2)[0];
        if (validCommands.contains(command)) return command;
        throw new DukeException("OOPS!!! I'm sorry but I don't know what that means :-(");
    }

    /**
     * A method which analyses the user input, as well as the command and modifies the taskList
     * as necessary.
     *
     * @param command The command word given by the user.
     * @param str The entire user input.
     * @return TaskList The modified taskList as per specified by the given user command.
     * @throws DukeException Exception thrown when the input string is empty.
     */
    public TaskList executeCommand(String command, String str) throws DukeException {
        Ui ui = new Ui();
        try {
            switch (command) {
                case "list":
                    ui.displayList(this.tasks);
                    break;
                case "mark":
                    String s1 = str.substring(5);
                    int i1 = Integer.parseInt(s1);
                    this.tasks.markTask(i1 - 1);
                    break;
                case "unmark":
                    String s2 = str.substring(7);
                    int i2 = Integer.parseInt(s2);
                    this.tasks.unmarkTask(i2 - 1);
                    break;
                case "todo":
                    if (str.length() == 4) throw new DukeException("OOPS!!! I'm sorry but description of a todo cannot be empty");
                    String s3 = str.substring(5);
                    if (s3.equals(" ") || s3.equals("")) throw new DukeException("OOPS!!! I'm sorry but description of a todo cannot be empty");
                    Todo t = new Todo(s3);
                    this.tasks.addTask(t);
                    break;
                case "deadline":
                    if (str.length() == 8) throw new DukeException("OOPS!!! I'm sorry but description of a deadline cannot be empty");
                    String s4 = str.substring(9);
                    String[] deadlineResult = s4.split(" /by ");
                    Deadline d = new Deadline(deadlineResult[0], deadlineResult[1]);
                    this.tasks.addTask(d);
                    break;
                case "event":
                    if (str.length() == 5) throw new DukeException("OOPS!!! I'm sorry but description or time period of an event cannot be empty");
                    String s5 = str.substring(6);
                    String[] eventResult = s5.split(" /at ");
                    Event e = new Event(eventResult[0], eventResult[1]);
                    this.tasks.addTask(e);
                    break;
                case "delete":
                    String s6 = str.substring(7);
                    int i = Integer.parseInt(s6);
                    this.tasks.deleteTask(i - 1);
                    break;
                case "find":
                    String s7 = str.substring(5);
                    this.tasks.findTask(s7);
                    break;

            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return this.tasks;
    }

}
