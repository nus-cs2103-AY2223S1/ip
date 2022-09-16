package duke;

import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * A chatbot program that manages tasks.
 */
public class Duke  {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() throws FileNotFoundException, DukeException {
        this.ui = new Ui();
        this.storage = new Storage("data", "duke.txt");
        this.tasks = new TaskList(storage.getTasksFromDisk());
    }

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


    public String getResponse(String input) throws DukeException {
        String response = this.ui.giveInput(this.tasks, input);
        return response;
    }

    public void save() throws IOException {
        this.storage.saveTasks(this.tasks.getList());
    }
}
