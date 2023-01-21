package bobby;

/**
 * Main class for bobby application
 */
public class Bobby {
    private TaskList taskList;
    private Parser parser;
    private Ui ui;
    private Storage storage;

    /**
     * Enum list of commands
     */
    public enum Commands {
        LIST,
        BYE,
        TODO,
        MARK,
        UNMARK,
        EVENT,
        DEADLINE,
        DELETE,
        FIND,
        SWITCH
    }

    /**
     * Constructor for Bobby
     * @param parser
     * @param ui
     * @param taskList
     * @param storage
     */
    protected Bobby(Parser parser, Ui ui, TaskList taskList, Storage storage) {
        this.parser = parser;
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }
    public String getResponse(String input) {
        String response = parser.executeInput(this.ui, input, this.storage, this.taskList);
        return response;
    }


}
