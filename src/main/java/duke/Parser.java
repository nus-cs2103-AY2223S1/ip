package duke;

import commands.*;

import java.util.Scanner;

/**
 * Parser is a class that deals with decoding user inputs
 */
public class Parser {
    private TaskList taskList;
    private Scanner scanner;
    private ConsoleRecorder consoleRecorder;
    private String line;

    /**
     * Constructor for Parser
     *
     * @param taskList The taskList that may be pre-populated from a previous save.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
        this.scanner = new Scanner(System.in);
        this.consoleRecorder = new ConsoleRecorder();
    }

    public void closeScanner() {
        scanner.close();
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public String getLine() {
        return line;
    }

    /**
     * Takes in user input, then executes the appropriate command
     *
     * @throws DukeException When command is unclear or string is given in a wrong format
     */
    public String parse(String... args) throws DukeException {
        if (args.length == 0) {
            line = scanner.nextLine();
        } else {
            line = args[0];
        }

        consoleRecorder.start();

        if (line.equals("bye")) {
            new ByeCommand().execute(this);
        } else if (line.equals("list")) {
            new ListCommand().execute(this);
        } else if (line.startsWith("unmark")) {
            new UnmarkCommand().execute(this);
        } else if (line.startsWith("mark")) {
            new MarkCommand().execute(this);
        } else if (line.startsWith("todo")) {
            new TodoCommand().execute(this);
        } else if (line.startsWith("deadline")) {
            new DeadlineCommand().execute(this);
        } else if (line.startsWith("event")) {
            new EventCommand().execute(this);
        } else if (line.startsWith("delete")) {
            new DeleteCommand().execute(this);
        } else if (line.startsWith("save")) {
            new SaveCommand().execute(this);
        } else if (line.startsWith("find")) {
            new FindCommand().execute(this);
        } else {
            Ui.show("OOPS!!! I'm sorry, but I don't know that that means :(");
        }
        return consoleRecorder.stopAndReturn();
    }

}
