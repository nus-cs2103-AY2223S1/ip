package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A chatbot program that manages tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor method for a Duke.
     *
     * @param dirName name of the directory for tasks to be saved in
     * @param fileName name of the file for tasks to be saved in
     * @throws FileNotFoundException if attempt to open file has failed
     * @throws DukeException if a Duke error occurs when loading tasks from disk
     */
    public Duke(String dirName, String fileName) throws FileNotFoundException, DukeException {
        this.ui = new Ui();
        this.storage = new Storage(dirName, fileName);
        this.tasks = new TaskList(storage.getTasksFromDisk());
    }

    /**
     * Runs the Duke program.
     *
     * @throws IOException if an I/O error occurs when saving tasks to disk
     * @throws DukeException if a Duke error occurs
     */
    public void start() throws IOException, DukeException {
        this.ui.doGreeting();
        this.ui.giveInput(this.tasks);
        this.storage.saveTasks(this.tasks.getList());
        this.ui.doBye();
    }

    public static void main(String[] args) {
        try {
            Duke duke = new Duke("data", "duke.txt");
            duke.start();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Oops, something went wrong: " + e.getMessage());
        } catch (DukeException e) {
        }
    }
}
