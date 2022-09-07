package duke;

/**
 * Represents the chatbot interacting with the user.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Creates new Duke chatbot object.
     *
     * @param filePath String of path to store list data.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(this.storage.loadTasks());
    }

    /**
     * Represents all valid keyword inputs from user.
     */
    public enum Keyword {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND
    }

    public static String getWelcome() {
        return Ui.showWelcome();
    }

    /**
     * Gets response for chatbot from user input.
     *
     * @param input Input from user.
     * @return String representing chatbot's response.
     */
    public String getResponse(String input) {
        String reply = "";
        Keyword keyword;
        String[] sections = input.split(" ", 2);

        try {
            keyword = this.parser.getKeyword(sections[0]);
        } catch (DukeException e) {
            return e.getMessage();
        }

        switch (keyword) {
        case LIST:
            reply = this.tasks.toString();
            break;
        case MARK:
            reply = this.tasks.mark(this.parser.getIndex(sections[1]));
            break;
        case UNMARK:
            reply = this.tasks.unmark(this.parser.getIndex(sections[1]));
            break;
        case TODO:
            try {
                reply = this.tasks.add(this.parser.createTodo(sections[1]));
            } catch (DukeException e) {
                reply = e.getMessage();
            }
            break;
        case DEADLINE:
            reply = this.tasks.add(this.parser.createDeadline(sections[1]));
            break;
        case EVENT:
            reply = this.tasks.add(this.parser.createEvent(sections[1]));
            break;
        case DELETE:
            reply = this.tasks.delete(this.parser.getIndex(sections[1]));
            break;
        case FIND:
            reply = this.ui.showMatchingTasks(this.tasks.findTasks(sections[1]));
            break;
        case BYE:
            reply = this.ui.showBye();
            break;
        default:
            break;
        }

        this.storage.updateTasks(this.tasks.getList());
        return reply;
    }
}
