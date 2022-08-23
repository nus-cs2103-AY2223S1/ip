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

    /**
     * Constructor for a Ui instance
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
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
     * Prints the initial greeting message when the chatbot starts up.
     */
    public void printGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n"
                + logo
                + "\nWhat can I do for you?");
    }

    /**
     * Reads and handles the user input from the command line.
     *
     * @return true if the program needs to exit
     *         false otherwise
     *
     * @throws DukeException if the input is invalid
     */
    public boolean handleInput() throws DukeException {
        String input = this.scanner.nextLine();

        Command command = this.parser.getCommand(input);
        return this.parser.executeCommand(command, input);
    }


}
