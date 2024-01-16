package duke.command;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;
import javafx.application.Platform;

/**
 * Handles commands.
 *
 * @author ish1506
 */
public class CommandHandler {
    private final Ui ui = new Ui();
    private final TaskList tasks;

    /**
     * Constructs a <code>CommandHandler</code>.
     *
     * @param tasks the <code>TaskList</code> of Duke.
     */
    public CommandHandler(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Executes user commands.
     * For invalid inputs, an error message is returned.
     *
     * @param input The input string.
     * @return The string message to display in the GUI.
     */
    public String execute(String input) {
        try {
            Parser parser = new Parser(input);
            parser.parse();
            Command command = parser.getCommand();
            int taskIndex = parser.getTaskIndex();
            String[] args = parser.getArgs();

            switch (command) {
            case BYE:
                // Write tasks data to storage before terminating program
                Storage.writeData(tasks.toStorageString());
                // @@author ish1506-reused
                // Reused from
                // https://stackoverflow.com/questions/15747277/how-to-make-java-program-exit-after-a-couple-of-seconds
                // with minor modifications
                Timer timer = new Timer();
                TimerTask exitApp = new TimerTask() {
                    @Override
                    public void run() {
                        Platform.exit();
                        System.exit(0);
                    }
                };
                timer.schedule(exitApp, new Date(System.currentTimeMillis() + 1000));
                return ui.getGoodbyeMessage();
            case LIST:
                if (tasks.isEmpty()) {
                    throw new DukeException("You have no tasks in the list!");
                }
                return tasks.toString();
            case MARK:
                tasks.mark(taskIndex);
                return ui.getMarkMessage(tasks.getTask(taskIndex));
            case UNMARK:
                tasks.unmark(taskIndex);
                return ui.getUnmarkMessage(tasks.getTask(taskIndex));
            case DELETE:
                Task temp = tasks.getTask(taskIndex);
                tasks.remove(taskIndex);
                return ui.getDeleteTaskMessage(temp);
            case TODO:
                Task newTodoTask = new Todo(args[0]);
                tasks.add(newTodoTask);
                return ui.getAddTaskMessage(newTodoTask);
            case DEADLINE:
                Task newDeadlineTask = new Deadline(args[0], args[1]);
                tasks.add(newDeadlineTask);
                return ui.getAddTaskMessage(newDeadlineTask);
            case EVENT:
                Task newEventTask = new Event(args[0], args[1]);
                tasks.add(newEventTask);
                return ui.getAddTaskMessage(newEventTask);
            case FIND:
                TaskList newList = tasks.find(args[0]);
                String responseMessage = ui.getFoundTasksListMessage(newList);
                assert responseMessage != null : "Response message should not be null";
                return responseMessage;
            default:
                throw new DukeException("Unrecognized command");
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
