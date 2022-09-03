package duke;

import duke.command.Command;

import java.io.IOException;
import java.util.Scanner;

/**
 * A program that keeps track of a task list defined by the user.
 * This task list is stored in an output file, and loaded on program start.
 */
public class Duke {
    public static final String FILE_NAME = "data.txt";
    public static final String[] DIRECTORIES = new String[]{"data"};
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for Duke that initialises Storage, TaskList and Ui.
     *
     * @param fileName name of output file for task list.
     * @param directories output file path's directory names in order.
     */
    public Duke(String fileName, String... directories) {
        this.ui = new Ui();
        this.storage = new Storage(ui, fileName, directories);
        this.taskList = new TaskList();
        ui.showWelcome();
        try {
            storage.load(taskList, ui);
        } catch (IOException e) {
            ui.showLoadingError();
            this.taskList = new TaskList();     // if error loading, use an empty task list
        }
    }

    /**
     * Starts parsing user input as commands from System.in, line by line.
     * Returns when a Command's isExit field is true.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit && scanner.hasNext()) {
            String commandString = scanner.nextLine();
            Command command;
            try {
                command = Parser.parse(commandString, taskList);
            } catch (IllegalArgumentException e) {
                ui.println(e.getMessage());
                continue;
            }
            command.execute(this.taskList, this.storage, this.ui);
            isExit = command.getIsExit();
        }
    }

    /**
     * Initialises and runs Duke.
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        new Duke(FILE_NAME, DIRECTORIES).run();
    }

    public static Duke initialiseForGui() {
        return new Duke(FILE_NAME, DIRECTORIES);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
