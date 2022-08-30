package ekud.main;

import ekud.exception.EkudException;
import ekud.task.TaskList;
import ekud.util.ParseResult;
import ekud.util.Parser;
import ekud.util.Storage;
import ekud.util.Ui;

public class Ekud {

    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructor that instantiates new Ekud instance.
     */
    public Ekud() {
        this.storage = new Storage();
        this.ui = new Ui();
        this.parser = new Parser();
        TaskList taskList = null;
        try {
            taskList = new TaskList(this.storage.getTasksFromFile());
        } catch (EkudException exception) {
            ui.showErrorMessage(exception);
            taskList = new TaskList();
        }
        this.taskList = taskList;
    }

    public String getResponse(String input) {
        try {
            ParseResult result = this.parser.parseCommand(input, this.taskList);
            if (result.terminate) {
                return "Bye!";
            }
            if (result.saveStorage) {
                storage.writeTasksToFile(this.taskList.getTaskList());
            }
            return result.message;
        } catch (EkudException exception) {
            return exception.getMessage();
        }
    }

}
