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
    private ToDoList toDoList;
    private Ui ui;

    /**
     * Initiates duke task with pathname to saved todolist and runs duke.Duke
     * @param args
     */
    public static void main(String[] args) {
        new Duke("/Users/shaune/Desktop/NUS/CS2103T/duke/Duke.txt").run();
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
            toDoList = new ToDoList(storage.loadFile());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            toDoList = new ToDoList();
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
                c.execute(toDoList, ui, storage);
                isExit = c.isExit();


            } catch (InputMismatchException e) {
                System.out.println("\tError: please only input String commands.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\tIndex specified out of range, please try again...");
            } catch (Exception e) {
                System.out.println("\t" + e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}

