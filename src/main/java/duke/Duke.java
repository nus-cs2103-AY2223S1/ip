package duke;

/**
 * Duke is a task list manager for tasks, deadlines and events!
 * 
 * @author Kiyan Ang Ping Young (@kynapy)
 * @version v0.1
 * @since 2022-09-10
 */

public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Parser parser;

    /**
     * Constructor for Duke.
     * @param filepath This is the filepath where the data file duke.txt would be stored.
     */

    public Duke(String filepath) {
        this.storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            this.tasks = new TaskList();
        }
        this.parser = new Parser(tasks);
    }

    public String getResponse(String input) {
        String reply = parser.parse(input);
        try {
            storage.store(tasks.getList());
        } catch (Exception e) {
            System.out.println();
        }
        return "Greg says: " + reply;
    }
}
