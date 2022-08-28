package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String dirName, String fileName) throws FileNotFoundException, DukeException {
        this.ui = new Ui();
        this.storage = new Storage(dirName, fileName);
        this.tasks = new TaskList(storage.getTasksFromDisk());
    }


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
