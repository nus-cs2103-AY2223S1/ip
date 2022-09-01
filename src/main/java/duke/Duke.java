package duke;

import java.io.IOException;
import java.util.Scanner;

/**
 * A task-keeping chatbot with a command line interface.
 *
 * @author Conrad
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the <code>Duke</code> chatbot.
     *
     * @param filePath The URL location of the local storage text file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        } catch (IOException e) {
            ui.showLoadingError(e);
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getWelcome() {
        return this.ui.showWelcome();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String userResponse) {
        try {
            Command command = Parser.parseUserResponse(userResponse);
            switch (command) {
            case BYE:
                this.storage.saveTasks(this.tasks);
                return this.ui.showGoodbye();
            case LIST:
                return this.ui.showTasks(this.tasks);
            case MARK:
                this.tasks.markTask(Parser.parseTaskNumber(userResponse));
                return this.ui.showMarkSuccess(Parser.parseTaskNumber(userResponse));
            case UNMARK:
                this.tasks.unmarkTask(Parser.parseTaskNumber(userResponse));
                return this.ui.showUnmarkSuccess(Parser.parseTaskNumber(userResponse));
            case TODO:
                this.tasks.addTasks(Parser.parseTodoTask(userResponse));
                return this.ui.showAddTaskSuccess(this.tasks);
            case EVENT:
                this.tasks.addTasks(Parser.parseEventTask(userResponse));
                return this.ui.showAddTaskSuccess(this.tasks);
            case DEADLINE:
                this.tasks.addTasks(Parser.parseDeadlineTask(userResponse));
                return this.ui.showAddTaskSuccess(this.tasks);
            case DELETE:
                this.tasks.deleteTask(Parser.parseTaskNumber(userResponse));
                return this.ui.showRemoveTaskSuccess(Parser.parseTaskNumber(userResponse), this.tasks);
            case FIND:
                return this.ui.showMatchingTasks(Parser.parseSearchInput(userResponse), this.tasks);
            default:
                break;
            }
        } catch (DukeException e) {
            return this.ui.showError(e);
        }
        return "";
    }
}
