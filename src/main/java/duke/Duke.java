package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.command.Command;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

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
    public Duke(String fileName, Ui ui, String... directories) {
        this.ui = ui;
        this.storage = new Storage(ui, fileName, directories);
        this.taskList = new TaskList();
        ui.showWelcome();
        try {
            storage.load(taskList, ui);
        } catch (IOException e) {
            ui.showLoadingError();
            this.taskList = new TaskList(); // if error loading, use an empty task list
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
     * Initialises and runs Duke in CLI.
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        new Duke(FILE_NAME, new Cli(), DIRECTORIES).run();
    }

    public static Duke initialiseForGui(VBox dialogContainer, Image dukeImage) {
        return new Duke(FILE_NAME, new Gui(dialogContainer, dukeImage), DIRECTORIES);
    }

    /**
     * Runs Duke for a single user's input; used for Duke in GUI.
     *
     * @param userInput string that the user inputted in the GUI.
     * @return true if Gui should terminate after this method call, false if otherwise.
     */
    public boolean runForGui(String userInput) {
        Scanner scanner = new Scanner(userInput);
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
        return isExit;
    }
}
