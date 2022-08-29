package duke;

import java.util.Scanner;

/**
 * This class handles interfacing with the user.
 *
 * @author Andrew Lo Zhi Sheng
 * @version CS2103T AY22/23 Semester 1
 */
public class Ui {
    // Fields
    private Scanner scanner;
    private Parser parser;
    private TaskList taskList;
    private Duke duke;

    /**
     * Constructor for a Ui instance
     */
    public Ui(Duke duke) {
        this.scanner = new Scanner(System.in);
        this.duke = duke;
    }

    /**
     * Initialises the taskList field.
     *
     * @param taskList an instance of TaskList representing the current list of
     *                 tasks in the chatbot.
     */
    public void updateTaskList(TaskList taskList) {
        this.taskList = taskList;
        this.parser = new Parser(this.taskList);
    }

    /**
     * Gets the initial greeting message for when the chatbot starts up.
     */
    public String getGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n"
                + logo
                + "\nWhat can I do for you?";
    }

    /**
     * Reads and handles the user input from the command line.
     *
     * @param input A String representing the user's input text
     *
     * @return true if the program needs to exit
     *         false otherwise
     *
     * @throws DukeException if the input is invalid
     */
    public boolean handleInput(String input) throws DukeException {

        Command command = this.parser.getCommand(input);
        return this.parser.executeCommand(command, input);
    }

    /**
     * TODO javadocs
     *
     * @param output
     */
    public void handleOutput(String output) {
        this.duke.updateResponse(output);
    }

}
