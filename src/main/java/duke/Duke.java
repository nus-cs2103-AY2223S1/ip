package duke;

import java.util.Scanner;

import duke.Exceptions.NoSuchCommandException;

/**
 * Main class for duke application
 */
public class Duke {
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
        FIND
    }

    /**
     * Constructor for Duke
     * @param parser
     * @param ui
     * @param taskList
     * @param storage
     */
    public Duke(Parser parser, Ui ui, TaskList taskList, Storage storage) {
        this.parser = parser;
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Runs the Duke program
     */
    public void run() {
        Scanner scanner = new Scanner((System.in));
        ui.showGreeting();

        loop: while (true) {

            String input = scanner.nextLine();
            try {
                if (parser.analyzeCommand(input).equals(Commands.BYE)) {
                    ui.showBye();
                    break loop;
                }
                parser.executeInput(ui, input, storage, taskList);
            } catch (NoSuchCommandException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Main function of duke
     * @param args
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        Parser parser = new Parser();
        Storage storage = new Storage("duke.txt");
        TaskList taskList = new TaskList(storage.readFile());
        Duke duke = new Duke(parser, ui, taskList, storage);

        duke.run();
    }
}
