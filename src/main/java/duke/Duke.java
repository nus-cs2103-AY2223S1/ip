package duke;

/**
 * The Duke program implements an application that
 * acts as your to-do list. It can add, delete, mark/unmark
 * and search for tasks.
 *
 * @author Gerald Teo Jin Wei
 * @version 0.1
 * @since 2022-08-28
 */

public class Duke {
    private static final String filePath = "./tasks.txt";
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for Duke program
     */
    public Duke() {
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
        ui = new Ui();
        assert storage != null;
        assert taskList != null;
        assert ui != null;
    }

    /**
     * Parses the raw input from the user, executes
     * the command, saves the updated tasklist and
     * returns Ui string response.
     * @param input Raw input from user
     * @return string that is printed to user by Ui
     */
    protected String getResponse(String input) {
        String str = Parser.parseCommand(input, this.taskList, this.ui);
        this.storage.save(this.taskList.getList());
        return str;
    }
}
