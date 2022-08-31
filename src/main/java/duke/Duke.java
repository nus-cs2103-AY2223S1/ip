package duke;

import java.io.StringWriter;
import java.util.Scanner;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private StringWriter stringWriter;

    /**
     * Constructs a Duke chatbot. The TaskList will be loaded from
     * the Storage at the specified file path during this stage.
     *
     * @param filePath the specified file path parameter.
     */
    public Duke(String filePath) {
        stringWriter = new StringWriter();
        ui = new Ui(stringWriter);
        storage = new Storage(filePath);
        try {
            ui.showStorageLoadingMessage();
            taskList = storage.load();
            ui.showReply(String.format("Save file loaded. You currently have %d tasks.\n", taskList.getLength()));
        } catch (DukeException e) {
            ui.showException(e);
            taskList = new TaskList();
        }
    }

    /**
     * Returns the String stored in the output buffer and clears the output buffer afterwards.
     *
     * @return a String.
     */
    public String readOutput() {
        String returnValue = stringWriter.toString();
        stringWriter.getBuffer().setLength(0);
        return returnValue;
    }

    /**
     * Initializes the chatbot. Currently, it only runs the welcome command.
     */
    public void init() {
        ui.showWelcome();
    }

    /**
     * Parses and executes the provided user command and prints the result to the output buffer.
     * Returns true if and only if the executed Command is a terminating Command.
     *
     * @param userCommand the provided user command.
     * @return true if and only if the Command is terminating.
     */
    public boolean execCommand(String userCommand) {
        try {
            Command runCommand = Parser.parse(userCommand);
            runCommand.exec(this.taskList, this.ui, this.storage);
            return runCommand.isTerminator();
        } catch (DukeException e) {
            this.ui.showException(e);
        }
        return false;
    }

    /**
     * Entry point to run Duke on terminal.
     *
     * @param args The provided arguments.
     */
    public static void main(String[] args) {

        Duke duke = new Duke("data/save.txt");
        Scanner sc = new Scanner(System.in);

        System.out.println(duke.readOutput());

        duke.init();
        System.out.println(duke.readOutput());

        boolean exitCalled = false;

        while (!exitCalled) {
            System.out.print("<< ");
            String userCommand = sc.nextLine();
            exitCalled = duke.execCommand(userCommand);
            System.out.println(duke.readOutput());
        }
    }
}
