package neo;
import java.io.IOException;

/**
 * Parser class.
 */
public class Parser {
    private AddCommand add;
    private DeleteCommand delete;
    private MarkCommand mark;
    private UnMarkCommand unmark;
    private FindCommand find;
    private Ui ui;
    private Storage store;
    private TaskList arrayLL;
    private PriorityCommand priority;
    private int type;

    /**
     * Constructor for parser class.
     *
     * @param ui User interface.
     * @param store Instance of storage class.
     * @param arrayLL Arraylist to store tasks.
     */
    public Parser(Ui ui, Storage store, TaskList arrayLL) {
        this.ui = ui;
        this.store = store;
        this.arrayLL = arrayLL;
    }

    /**
     * Makes sense of user input.
     *
     * @param userText The string containing user input.
     * @throws NeoException Exception neo.
     * @throws IOException Input output exception.
     */
    public String checkText(String userText) throws NeoException, IOException {

        if (userText.equals("list") || userText.equals("List")) {
            return store.retrieveData();
        }
        if (userText.equals("help")) {
            return ui.help();
        }
        String arr[];
        arr = userText.split(" ", 2);
        if (arr.length == 1 && (arr[0].equals("high") || arr[0].equals("medium") || arr[0].equals("low") || arr[0].equals("event")
                || arr[0].equals("todo") || arr[0].equals("find") || arr[0].equals("mark") || arr[0].equals("unmark") || arr[0].equals("delete") || arr[0].equals("deadline"))) {
            throw new NeoException("sorry task cannot be empty");
        }
        if (arr.length == 1 && !arr[0].equals("high") && !arr[0].equals("medium") && !arr[0].equals("low") && !arr[0].equals("event")
                && !arr[0].equals("todo") && !arr[0].equals("find") && !arr[0].equals("mark") && !arr[0].equals("unmark") && !arr[0].equals("delete") && !arr[0].equals("deadline")) {
            throw new NeoException("Sorry I don't know what that means");
        } else {
            String command = arr[0];
            String taskNumber = arr[1];
            switch (command) {
                case "high":
                case "medium":
                case "low":
                    this.priority = new PriorityCommand(ui, store, arrayLL, type);
                    return priority.complete(userText);
                case "event":
                    type = 1;
                    this.add = new AddCommand(ui, store, arrayLL, type);
                    return add.complete(taskNumber);
                case "todo":
                    type = 2;
                    this.add = new AddCommand(ui, store, arrayLL, type);
                    return add.complete(taskNumber);
                case "find":
                    this.find = new FindCommand(ui, store, arrayLL);
                    return find.complete(arr[1]);
                case "mark":
                    this.mark = new MarkCommand(ui, store, arrayLL);
                    return mark.complete(arr[1]);
                case "unmark":
                    this.unmark = new UnMarkCommand(ui, store, arrayLL);
                    return unmark.complete(arr[1]);
                case "delete":
                    this.delete = new DeleteCommand(ui, store, arrayLL);
                    return delete.complete(arr[1]);
                case "deadline":
                    type = 0;
                    this.add = new AddCommand(ui, store, arrayLL, type);
                    return add.complete(taskNumber);
                default:
                    throw new NeoException("Sorry I don't know what that means");
            }
        }
    }
}
