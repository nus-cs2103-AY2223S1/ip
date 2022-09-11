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
static final String filePath = "tasks.txt";
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

    protected String getResponse(String input) {
        String str = Parser.parseCommand(input,this.taskList,this.ui);
        this.storage.save(this.taskList.getList());
        return str;
    }
}
