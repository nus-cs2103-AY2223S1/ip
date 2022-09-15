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
            System.out.println(stor.retrieveData());
            return stor.retrieveData();
        }
        String arr[];
        arr = userText.split(" ", 2);
        if (arr.length < 0) {
            throw new NeoException("Sorry I don't know what that means");
        }
        if (arr.length == 1) {
            throw new NeoException("sorry task cannot be empty");
        }
        else {
            String command = arr[0];
            String tempi = arr[1];
            switch (command) {
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
