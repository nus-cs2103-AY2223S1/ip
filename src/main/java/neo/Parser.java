package neo;
import java.awt.*;
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
    private Storage stor;
    private TaskList arrayLL;
    private PriorityCommand priority;
    private int type;

    /**
     * Parser constructor.
     *
     * @param ui ui
     * @param stor instance of storage class
     * @param arrayLL arraylist to stor tasks
     */
    public Parser(Ui ui, Storage stor, TaskList arrayLL) {
        this.ui = ui;
        this.stor = stor;
        this.arrayLL = arrayLL;
    }

    /**
     * Function to make sense of user input.
     *
     * @param userText string containing user input
     * @throws NeoException excpetion neo
     * @throws IOException input output exception
     */
    public String checkText(String userText) throws NeoException, IOException {

        if (userText.equals("list") || userText.equals("List")) {
            return stor.retrieveData();
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
            String tempi = arr[1];
            switch (command) {
                case "high":
                case "medium":
                case "low":
                    this.priority = new PriorityCommand(ui, stor, arrayLL, type);
                    return priority.complete(userText);
                case "event":
                    type = 1;
                    this.add = new AddCommand(ui, stor, arrayLL, type);
                    return add.complete(tempi);
                case "todo":
                    type = 2;
                    this.add = new AddCommand(ui, stor, arrayLL, type);
                    return add.complete(tempi);
                case "find":
                    this.find = new FindCommand(ui, stor, arrayLL);
                    return find.complete(arr[1]);
                case "mark":
                    this.mark = new MarkCommand(ui, stor, arrayLL);
                    return mark.complete(arr[1]);
                case "unmark":
                    this.unmark = new UnMarkCommand(ui, stor, arrayLL);
                    return unmark.complete(arr[1]);
                case "delete":
                    this.delete = new DeleteCommand(ui, stor, arrayLL);
                    return delete.complete(arr[1]);
                case "deadline":
                    type = 0;
                    this.add = new AddCommand(ui, stor, arrayLL, type);
                    return add.complete(tempi);
                default:
                    throw new NeoException("Sorry I don't know what that means");
            }
        }
    }
}
