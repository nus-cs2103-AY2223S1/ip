package duke;
import java.io.FileNotFoundException;

/**
 * A Duke bot.
 */
public class Duke {
    private UI ui;
    private Storage storage;
    private TaskList taskList;

    public Duke() {
        ui = new UI();
        storage = new Storage("data/nuke.txt");
    }

    public UI getUI() {
        return ui;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public Storage getStorage() {
        return storage;
    }

    /**
     * Starts the Duke bot.
     */
    public void run() {
        try {
            taskList = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println("Unable to load storage");
        }

        ui.run(this);
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
