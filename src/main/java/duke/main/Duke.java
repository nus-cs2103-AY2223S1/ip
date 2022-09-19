package duke.main;

import java.io.IOException;
import java.util.Optional;

import duke.command.Command;
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
    private static final String LOGO =
        "Welcome to\n" + " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n" + "      Chatbot!\n";

    private static final String INTRO = "Hey hey hey! I'm Duke\n" + "What can I do for you?";

    private static final String FATAL_EXIT = "Fatal Error! The system will exit abnormally!";

    private DukeIo userInputOutput;
    private TaskList tasks;
    private Storage dukeData;

    private Duke(TaskList tasks, Storage dukeData, DukeIo dukeIo) {
        this.dukeData = dukeData;
        this.tasks = tasks;
        this.userInputOutput = dukeIo;

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
        Command c = DataParser.parseCommand(txt);
        try {
            c.execute(tasks, userInputOutput, dukeData);
        } catch (DukeException e) {
            userInputOutput.printError(e);
            return true;
        } catch (IOException e) {
            userInputOutput.printError(e);
            userInputOutput.printError(FATAL_EXIT);
            return false;
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
        return Duke.createApplication(userIo, filepath);
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
        return createApplication(userIo, "");
    }

    /**
     * Creates a standard duke application given an io source while loading from a save file
     *
     * @param userIo io object to communicate
     * @param filePath path to the save file
     * @return
     */
    public static Duke createApplication(DukeIo userIo, String filePath) {
        Storage dukeData;
        TaskList tasks;
        try {
            dukeData = Storage.createStorage(filePath);
            tasks = new TaskList(dukeData.readFile(userIo));
        } catch (IOException e) {
            userIo.printError(e);
            userIo.printError(FATAL_EXIT);
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
     * Initiates the running loop. Will exit when <code>handleInput</code> returns false.
     */
    private void run() {
        String txt;
        do {
            txt = userInputOutput.readLine();
        } while (handleInput(txt));
    }

    /**
     * Launches the duke package. <br>
     * flags: <br>
     * --no-gui/-ng => launch without GUI <br>
     * --save-file/-s [filepath] => specifies save path
     *
     * @param args flags if any
     */
    public static void main(String[] args) {
        Optional<Boolean> launchWithGui = Optional.empty();
        Optional<String> saveFile = Optional.empty();
        int i = -1;
        while (++i < args.length) {
            switch (LaunchFlagEnum.getFlag(args[i])) {
            case NO_GUI:
                if (launchWithGui.isPresent()) {
                    System.out.println(LaunchFlagErrMsg.CONFLICTING_LAUNCH);
                    return;
                }
                launchWithGui = Optional.of(false);
                break;
            case GUI:
                if (launchWithGui.isPresent()) {
                    System.out.println(LaunchFlagErrMsg.CONFLICTING_LAUNCH);
                    return;
                }
                launchWithGui = Optional.of(true);
                break;
            case SAVE_FILE:
                if (++i == args.length) {
                    System.out.println(LaunchFlagErrMsg.UNSPECIFIED_SAVE_FILE);
                    return;
                }
                saveFile = Optional.of(args[i]);
                break;
            case NULL_FLAG:
                System.out.printf(LaunchFlagErrMsg.UNRECOGNISED_FLAG.toString(), args[i]);
                return;
            case HELP:
                System.out.println(LaunchFlagEnum.getHelp());
                return;
            default:
                System.out.printf(LaunchFlagErrMsg.UNIMPLEMENTED_FLAG.toString(), args[i]);
                return;
            }
        }
        if (launchWithGui.orElse(true)) {
            Launcher.main(saveFile.map(x -> new String[] {x}).orElse(new String[] {}));
            return;
        }
        Duke duke = saveFile.map(x -> createApplication(x)).orElseGet(() -> createApplication());
        duke.run();
    }

}
