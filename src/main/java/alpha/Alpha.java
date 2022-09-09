package alpha;

import java.util.Scanner;

import alpha.command.Command;
import alpha.command.Exit;

/**
 * Entry point of the program.
 */
public class Alpha {

    /** Object of the Ui class to take user inputs and display messages */
    private Ui uI = new Ui();

    /** Object of the Parser class to help interpret the user input messages */
    private Parser parser = new Parser();

    /** Object of the TaskList class to help operate on the list of tasks stored */
    private TaskList taskList;

    /** File directory that stores task list data locally */
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private final String FILE_PATH;

    /** Object of the FileOperations class that helps read and write on file */
    private FileOperations fileOperations;

    /**
      * Constructor to initialise the global variable and create and read file.
      *
      * @param filePath Directory of the file.
      */
    public Alpha(String filePath) {
        FILE_PATH = filePath;
        fileOperations = new FileOperations(FILE_PATH);
        try {
            fileOperations.createFile();
            taskList = new TaskList(fileOperations.readFile());
        } catch (AlphaException e) {
            uI.colouredPrint(uI.getAnsiCode("ANSI_RED"), e.getMessage());
        }
    }

    /**
     * Runs the entire program by taking user inputs and executing the relevant commands.
     * Handles Exceptions.
     */
    public void run() {
        Scanner in = new Scanner(System.in);
        uI.welcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            String userInput = uI.takeUserInput(in);
            try {
                Command c = parser.interpretMessage(userInput);
                c.execute(taskList, uI, fileOperations);
                if (c instanceof Exit) {
                    isExit = true;
                }
            } catch (AlphaException e) {
                uI.colouredPrint(uI.getAnsiCode("ANSI_RED"), e.getMessage());
            }
        }
        uI.colouredPrint(uI.getAnsiCode("ANSI_BLUE"), ">> Bye, see you soon!");
        System.exit(0);
    }

    /** Entry point of the program */
    public static void main(String[] args) {
        new Alpha("./alpha.Alpha.txt").run();
    }
}
