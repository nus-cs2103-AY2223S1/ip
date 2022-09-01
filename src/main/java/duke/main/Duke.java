package duke.main;

import java.io.IOException;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.util.DukeIo;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Main class for cli version of Duke
 */
public class Duke {
    private static final String LOGO = "Welcome to\n"
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n"
            + "      Chatbot!\n";

    private static final String INTRO = "Hello! I'm Duke\n"
            + "What can I do for you?";

    private DukeIo userInputOutput;
    private TaskList tasks;
    private Storage dukeData;

    private Duke(TaskList tasks, Storage dukeData) {
        userInputOutput = new DukeIo();
        this.dukeData = dukeData;
        this.tasks = tasks;
    }

    private boolean handleInput() {
        String txt = userInputOutput.readLine();
        Command c = Parser.parseCommand(txt);
        try {
            c.execute(tasks, userInputOutput, dukeData);
        } catch (DukeException e) {
            userInputOutput.printError(e);
        } catch (IOException e) {
            userInputOutput.printError(e);
            return true;
        }

        return !c.isExit();
    }

    /**
     * Creates a duke object using the sepecied filepath.
     * 
     * @param filepath
     * @return Duke
     */
    public static Duke createApplication(String filepath) {
        DukeIo userIO = new DukeIo();
        userIO.printTask(LOGO, 2);
        userIO.printTask(INTRO, 3);
        Storage dukeData;
        TaskList tasks;
        try {
            dukeData = Storage.createStorage(filepath);
            tasks = new TaskList(dukeData.readFile());
        } catch (IOException e) {
            userIO.printError(e);
            userIO.printTask("Fatal Error! The system will exit abnormally!");
            return null;
        }

        return new Duke(tasks, dukeData);
    }

    /**
     * Creates a Duke object with the default save path.
     * 
     * @return Duke
     */
    public static Duke createApplication() {
        DukeIo userIO = new DukeIo();
        userIO.printTask(LOGO, 2);
        userIO.printTask(INTRO, 3);
        Storage dukeData;
        TaskList tasks;
        try {
            dukeData = Storage.createStorage();
            tasks = new TaskList(dukeData.readFile());
        } catch (IOException e) {
            userIO.printError(e);
            userIO.printTask("Fatal Error! The system will exit abnormally!");
            return null;
        }

        return new Duke(tasks, dukeData);
    }

    /**
     * Initiates the running loop. Will exit when <code>handleInput</code> returns false.
     */
    public void run() {
        while (handleInput()) {
        }
    }

    /**
     * Runs the Duke Program
     * 
     * @param args
     */
    public static void main(String[] args) {
        Duke duke = createApplication();
        duke.run();
    }
}
