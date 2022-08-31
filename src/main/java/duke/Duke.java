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
     * @return A <code>Duke</code> chatbot instance.
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
     * Starts the bot for the user.
     *
     */
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
                case FIND:
                    this.ui.showMatchingTasks(Parser.parseSearchInput(userResponse), this.tasks);
                    break;
                default:
                    break;
                }
            } catch (DukeException e) {
                this.ui.showError(e);
            }
        }
        sc.close();

    }

    public static void main(String[] args) {
        new Duke("src/main/java/tasks.txt").run();
    }
}
