package duke;


/**
 * Represents the Duke class to run the Duke program
 * @author Reuben Chay
 */
public class Duke {


    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private StringReplacer stringReplacer;
    private Parser parser;

    public Duke() {
        this.taskList = new TaskList();
        this.storage = new Storage("./tasks.txt");
        this.ui = new Ui();
        this.stringReplacer = new StringReplacer();
        this.parser = new Parser(this.storage, this.taskList);
        storage.openFile();
        storage.listInit(this.taskList.getList());
    }

    /**
     * Main function
     * @param args arguments supplied
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * runs the Duke program
     */
    public void run() {

        // attempt to open file within same folder as src code, create file if file doesn't exist
        storage.openFile();

        // at this point file will exist, init taskList
        storage.listInit(taskList.getList());

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeMsg = "Hello! I'm\n" + logo + "\nWhat can I do for you?\n";
        System.out.println(welcomeMsg);

        String input = "";

        while (true) {
            input = ui.scan();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                parser.parse(input);
            }
        }
        ui.close();
    }

    public String getResponse(String input) {
        return this.parser.parse(input);
    }
}
