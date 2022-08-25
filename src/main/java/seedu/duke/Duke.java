package seedu.duke;

public class Duke {
    private static Storage save;
    private static Ui ui;

    /**
     * A contructor that returns an instance of Duke.
     *
     * @param filePath The path to the save file where data is stored.
     */
    public Duke(String filePath) {
        save = new Storage(filePath);

        TaskList taskList = new TaskList(save);
        ui = new Ui(taskList);
    }

    /**
     * Prints out greeting, initialises the TaskList and opens the scanner to start
     * receiving commands.
     */
    public static void run() {
        ui.introduction();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();

    }
}
