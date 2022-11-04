package duke;

import java.io.StringWriter;
import java.util.Scanner;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates a Duke chatbot instance.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private StringWriter stringWriter;
    private boolean hasTerminated;

    /**
     * Constructs a Duke chatbot instance.
     *
     * @param filePath the specified file path parameter.
     */
    public Duke(String filePath) {
        stringWriter = new StringWriter();
        ui = new Ui(stringWriter);
        storage = new Storage(filePath);
        hasTerminated = false;

    }

    /**
     * Returns the String stored in the output buffer and clears the output buffer afterwards.
     *
     * @return a String.
     */
    private String readOutput() {
        String returnValue = stringWriter.toString();
        stringWriter.getBuffer().setLength(0);
        return returnValue;
    }

    /**
     * Initializes the chatbot by loading the TaskList stored in
     * the file pointed by Storage. Then prints out a welcome message.
     *
     * @return The chatbot's feedback during initialization.
     * @throws IllegalStateException if this chatbot has been initialized.
     */
    public String init() {
        if (taskList != null) {
            throw new IllegalStateException("init method called to an initialized Duke object");
        }

        try {
            ui.showStorageLoadingMessage();
            taskList = storage.load();
            ui.showReply(String.format("Save file loaded. You currently have %d tasks.\n", taskList.getLength()));
        } catch (DukeException e) {
            ui.showException(e);
            taskList = new TaskList();
        }
        ui.showWelcome();

        return readOutput();
    }

    /**
     * Parses and executes the provided user command and returns
     * a String containing Duke's feedback.
     *
     * @param userCommand the provided user command.
     * @return The chatbot's feedback during command execution.
     * @throws IllegalStateException if this chatbot has not been initialized or has been terminated.
     */
    public String execCommand(String userCommand) {
        if (taskList == null) {
            throw new IllegalStateException("An uninitialized Duke object can't execute commands");
        } else if (this.isTerminated()) {
            throw new IllegalStateException("A terminated Duke object can't execute commands");
        }

        try {
            Command runCommand = Parser.parse(userCommand);
            runCommand.exec(taskList, ui, storage);
            hasTerminated = runCommand.isTerminator();
        } catch (DukeException e) {
            ui.showException(e);
        }

        return readOutput();
    }

    /**
     * Returns true if and only if this Duke chatbot has terminated, i.e.
     * by calling a terminating Command.
     *
     * @return true if and only if this chatbot has terminated.
     */
    public boolean isTerminated() {
        return hasTerminated;
    }

    /**
     * Runs Duke on the terminal.
     *
     * @param args The provided arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("data/save.txt");
        Scanner sc = new Scanner(System.in);

        System.out.println(duke.init());

        while (!duke.isTerminated()) {
            System.out.print("<< ");
            String userCommand = sc.nextLine();
            System.out.println(duke.execCommand(userCommand));
        }
    }
}
