package carbon;

import carbon.task.Task;
import carbon.error.CarbonException;
import carbon.error.InvalidInputException;

/**
 * Parses user inputs, and processes them.
 * This class merges the reading and writing functionality of the chat bot, by
 * parsing user inputs, and calling for the necessary processing to be executed.
 */
public class Parser {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructs an instance of Parser class.
     * 
     * @param ui An instance of the Ui class.
     * @param storage An instance of the Storage class.
     * @return Parser object.
     */
    public Parser(Ui ui, Storage storage) {
        this.ui = ui;
        this.storage = storage;
        try {
            this.taskList = this.storage.loadSavefile();
        } catch (CarbonException error) {
            this.ui.printError(error);
        }
    }

    /**
     * Parses and processes a user input text.
     * If the command is not a basic command, this method will call the processAdvanced method.
     * 
     * @param input User input text.
     * @return Execution log.
     * @throws CarbonException  If an error is encountered during processing or execution.
     */
    public String process(String input) throws CarbonException {
        String lowerCaseInput = input.toLowerCase();
        String log;
        switch (lowerCaseInput) {
        case "bye":
            this.ui.exit();
            log = "";
            break;
        case "list":
            log = this.taskList.listItems();
            break;
        default:
            // unable to process as a simple command, pass to next handler
            log = this.processAdvanced(input);
        }
        return log;
    }

    /**
     * Parses and processes user input text.
     * This method is only for advanced commands that require parameters from the user.
     *
     * @param input User input text.
     * @return Execution log.
     * @throws CarbonException  If an error is encountered during processing or execution.
     */
    private String processAdvanced(String input) throws CarbonException {
        String lowerCaseInput = input.toLowerCase();
        String log;
        try {
            if (lowerCaseInput.startsWith("mark")) {
                log = this.taskList.validateAndMark(input, true);
            } else if (lowerCaseInput.startsWith("unmark")) {
                log = this.taskList.validateAndMark(input, false);
            } else if (lowerCaseInput.startsWith("delete")) {
                log = this.taskList.deleteTask(input);
            } else if (lowerCaseInput.startsWith("todo")) {
                log = this.taskList.addTask(input, Task.Type.TODO);
            } else if (lowerCaseInput.startsWith("deadline")) {
                log = this.taskList.addTask(input, Task.Type.DEADLINE);
            } else if (lowerCaseInput.startsWith("event")) {
                log = this.taskList.addTask(input, Task.Type.EVENT);
            } else {
                // not a command, return invalid input
                CarbonException invalidInput = new InvalidInputException(input);
                throw invalidInput;
            }
            this.storage.saveTasks(this.taskList);
            return log;
        } catch (CarbonException error) {
            throw error;
        }
    }
}
