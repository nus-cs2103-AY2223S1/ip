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
        return "Hi! I'm totally not Duke!\n"
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
     * Passes the command response to the GUI in duke.
     *
     * @param output The response to be displayed in the GUI
     */
    public void handleOutput(String output) {
        this.duke.updateResponse(output);
    }

}
