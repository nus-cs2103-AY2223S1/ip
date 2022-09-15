package duke.main;

import java.io.IOException;

import duke.command.Command;
import duke.command.CommandSelector;
import duke.exceptions.DukeException;
import duke.inputoutput.DukeCliIo;
import duke.inputoutput.DukeIo;
import duke.util.DataParser;
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

    private static final String FATALEXIT = "Fatal Error! The system will exit abnormally!";

    private DukeIo userInputOutput;
    private TaskList tasks;
    private Storage dukeData;
    private CommandSelector commandSelector;

    private Duke(TaskList tasks, Storage dukeData, DukeIo dukeIo) {
        this.dukeData = dukeData;
        this.tasks = tasks;
        this.userInputOutput = dukeIo;
        commandSelector = new CommandSelector();

        dukeIo.printTask(LOGO);
        dukeIo.printTask(INTRO, 2);
    }

    /**
     * Takes in user input as string and does appropriate commands via the Duke IO
     *
     * @param txt user input given by the user
     * @return
     */
    public boolean handleInput(String txt) {
        Command c = DataParser.parseCommand(txt, commandSelector);
        try {
            c.execute(tasks, userInputOutput, dukeData, commandSelector);
        } catch (DukeException e) {
            userInputOutput.printError(e);
        } catch (IOException e) {
            userInputOutput.printError(e);
            return true;
        }

        return !c.isExit();
    }

    /**
     * Creates a duke cli object using the sepecied filepath.
     *
     * @param filepath
     * @return Duke
     */
    public static Duke createApplication(String filepath) {
        DukeIo userIo = new DukeCliIo();
        return Duke.createApplication(userIo);
    }

    /**
     * Creates a Duke cli object with the default save path.
     *
     * @return Duke a cli duke object
     */
    public static Duke createApplication() {
        DukeIo userIo = new DukeCliIo();
        return Duke.createApplication(userIo);
    }

    /**
     * Creates a standard duke application given an io source
     *
     * @param userIo io object to communicate
     * @return returns an instance of Duke
     */
    public static Duke createApplication(DukeIo userIo) {
        Storage dukeData;
        TaskList tasks;
        try {
            dukeData = Storage.createStorage();
            tasks = new TaskList(dukeData.readFile());
        } catch (IOException e) {
            userIo.printError(e);
            userIo.printTask(FATALEXIT);
            return null;
        }

        return new Duke(tasks, dukeData, userIo);
    }

    /**
     * Creates a standard duke application given an io source while loading from a
     * save file
     *
     * @param userIo   io object to communicate
     * @param filePath path to the save file
     * @return
     */
    public static Duke createApplication(DukeIo userIo, String filePath) {
        Storage dukeData;
        TaskList tasks;
        try {
            dukeData = Storage.createStorage(filePath);
            tasks = new TaskList(dukeData.readFile());
        } catch (IOException e) {
            userIo.printError(e);
            userIo.printTask("Fatal Error! The system will exit abnormally!");
            return null;
        }

        return new Duke(tasks, dukeData, userIo);
    }

    /**
     * returns a reference to the io method
     */
    public DukeIo getIo() {
        return userInputOutput;
    }

    /**
     * Initiates the running loop. Will exit when <code>handleInput</code> returns
     * false.
     */
    public void run() {
        String txt;
        do {
            txt = userInputOutput.readLine();
        } while (handleInput(txt));
    }

}
