package carbon;

import carbon.task.Task;
import carbon.error.CarbonException;
import carbon.error.InvalidInputException;

public class Parser {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public Parser(Ui ui, Storage storage) {
        this.ui = ui;
        this.storage = storage;
        try {
            this.taskList = this.storage.loadSavefile();
        } catch (CarbonException error) {
            this.ui.printError(error);
        }
    }

    public boolean process(String input) {
        String lowerCaseInput = input.toLowerCase();
        switch (lowerCaseInput) {
            case "bye":
                this.ui.exit();
                return false;
            case "list":
                String log = this.taskList.listItems();
                this.ui.printOut(log);
                return true;
            default:
                // unable to process as a simple command, pass to next handler
                this.processAdvanced(input);
                return true;
        }
    }

    private void processAdvanced(String input) {
        String lowerCaseInput = input.toLowerCase();
        try {
            if (lowerCaseInput.startsWith("mark")) {
                String log = this.taskList.validateAndMark(input, true);
                this.ui.printOut(log);
            } else if (lowerCaseInput.startsWith("unmark")) {
                String log = this.taskList.validateAndMark(input, false);
                this.ui.printOut(log);
            } else if (lowerCaseInput.startsWith("delete")) {
                String log = this.taskList.deleteTask(input);
                this.ui.printOut(log);
            } else if (lowerCaseInput.startsWith("todo")) {
                String log = this.taskList.addTask(input, Task.Type.TODO);
                this.ui.printOut(log);
            } else if (lowerCaseInput.startsWith("deadline")) {
                String log = this.taskList.addTask(input, Task.Type.DEADLINE);
                this.ui.printOut(log);
            } else if (lowerCaseInput.startsWith("event")) {
                String log = this.taskList.addTask(input, Task.Type.EVENT);
                this.ui.printOut(log);
            } else {
                // not a command, return invalid input
                CarbonException invalidInput = new InvalidInputException(input);
                throw invalidInput;
            }
            this.storage.saveTasks(this.taskList);
        } catch (CarbonException error) {
            this.ui.printError(error);
        }
    }
}
