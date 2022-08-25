import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.io.File;

/**
 * A task-keeping chatbot with a command line interface.
 *
 * @author Conrad
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        } catch (FileNotFoundException e) {
            ui.showLoadingError(e);
        }
    }

    public void run() {
        this.ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                String userResponse = sc.nextLine();
                Command command = Parser.parseUserResponse(userResponse);
                switch (command) {
                    case BYE:
                        this.storage.saveTasks(this.tasks);
                        this.ui.showGoodbye();
                        isExit = true;
                        break;
                    case LIST:
                        this.ui.showTasks(this.tasks);
                        break;
                    case MARK:
                        this.tasks.markTask(Parser.parseTaskNumber(userResponse));
                        this.ui.showMarkSuccess(Parser.parseTaskNumber(userResponse));
                        break;
                    case UNMARK:
                        this.tasks.unmarkTask(Parser.parseTaskNumber(userResponse));
                        this.ui.showUnmarkSuccess(Parser.parseTaskNumber(userResponse));
                        break;
                    case TODO:
                        this.tasks.addTask(Parser.parseTodoTask(userResponse));
                        this.ui.showAddTaskSuccess(this.tasks);
                        break;
                    case EVENT:
                        this.tasks.addTask(Parser.parseEventTask(userResponse));
                        this.ui.showAddTaskSuccess(this.tasks);
                        break;
                    case DEADLINE:
                        this.tasks.addTask(Parser.parseDeadlineTask(userResponse));
                        this.ui.showAddTaskSuccess(this.tasks);
                        break;
                    case DELETE:
                        this.tasks.deleteTask(Parser.parseTaskNumber(userResponse));
                        this.ui.showRemoveTaskSuccess(Parser.parseTaskNumber(userResponse), this.tasks);
                        break;
                }
            } catch (DukeException e) {
                this.ui.showError(e);
            }
        }

    }

    public static void main(String[] args) {
        new Duke("src/main/java/tasks.txt").run();
    }
}
