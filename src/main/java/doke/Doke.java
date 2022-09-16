package doke;

import java.util.Scanner;

/**
 * Doke is a personal chatbot.
 *
 * All Refactoring and improvements throughout the code
 * related to gui is referenced from Farrel D.S.'s code
 * (github username:  <a href="https://github.com/sugiyem">sugiyem</a>)
 * which can be seen <a href="https://github.com/sugiyem/ip">here</a>
 *
 * @author Stevan Gerard Gunawan
 */
public class Doke {

    protected static final String DOKE_FILE_PATH = "src/main/java/data/doke.txt";
    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList taskList;

    /**
     * Constructs a Doke object
     */
    public Doke() {
        this.ui = new Ui();
        this.parser = new Parser();
        try {
            this.storage = new Storage();
            this.taskList = new TaskList(ui, storage);
        } catch (DokeException error) {
            this.ui.printMiscErrorMessage();
        }
    }

    /**
     * Returns the input string in the given format
     *
     * @param input the input string
     */
    protected String getResponse(String input) {
        return parser.processString(taskList, storage, input, ui).getValue();
    }

    /**
     * Runs the main Doke program.
     *
     * @param args
     */
    public static void main(String[] args) {
        Doke doke = new Doke();
        doke.runDoke();
    }

    /**
     * Runs the doke program
     */
    public void runDoke() {

        Scanner sc = new Scanner(System.in);

        ui.printOut("full path: " + Storage.DOKE_FILE.getAbsolutePath());

        this.ui.printOut("hello");

        this.ui.printOut("The following is your stored task: \n");
        taskList.listOut(ui);

        String str = sc.nextLine();
        while (true) {
            if (!parser.processString(taskList, storage, str, ui).getKey()) {
                break;
            }
            str = sc.nextLine();
        }

        sc.close();
        ui.printOut("bye");
    }

}
