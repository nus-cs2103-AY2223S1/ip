package duke;

import java.io.IOException;
import java.util.InputMismatchException;

/**
 * Main duke.Duke class where duke program is ran
 * @author Shaune Ang
 */
public class Duke {
    private String filePath;
    private FileLoaderSaver storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Initiates duke task with pathname to saved todolist and runs duke.Duke
     * @param args
     */
    public static void main(String[] args) {
        new Duke("/Users/shaune/Desktop/NUS/CS2103T/Duke.txt").run();
    }

    /**
     * duke.Duke Constructor
     *
     * @param filePath path to the saved todolist txt file if available
     */
    public Duke(String filePath) {
        this.filePath = filePath;
        ui = new Ui();
        storage = new FileLoaderSaver(filePath);

        try {
            taskList = new TaskList(storage.loadFile());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Runs duke program
     */
    public void run() {
        Ui.greet(); //duke.Ui
        boolean isExit = false;

        while (!isExit) {
            String fullCommand;
            try {
                fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (InputMismatchException exception) {
                System.out.println("\tError: please only input String commands.");
            } catch (IndexOutOfBoundsException exception) {
                System.out.println("\tIndex specified out of range, please try again...");
            } catch (Exception exception) {
                System.out.println("\t" + exception.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}

