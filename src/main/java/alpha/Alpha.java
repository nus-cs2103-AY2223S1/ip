package alpha;

import alpha.command.Command;

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
    private final String filePath;

    /** Object of the FileOperations class that helps read and write on file */
    private FileOperations fileOperations;

    /**
     * Constructor to initialise the global variable and create and read file.
     *
     */
    public Alpha() {
        filePath = "./alpha.Alpha.txt";
        fileOperations = new FileOperations(this.filePath);
        try {
            fileOperations.createFile();
            taskList = new TaskList(fileOperations.readFile());
        } catch (AlphaException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        try {
            Command c = parser.interpretMessage(input);
            return c.execute(taskList, uI, fileOperations);
        } catch (AlphaException a) {
            return a.getMessage();
        }
    }
}
