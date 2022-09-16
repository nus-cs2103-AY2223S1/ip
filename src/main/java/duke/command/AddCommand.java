package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.common.Messages;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.util.TaskList;

/**
 * Represents a command to add a task to the tasklist; subclass of Command.
 * @author Huang Yuchen
 * @author hyuchen@u.nus.edu
 */
public class AddCommand extends Command {
    public static final String TASK_ADDED = "I've added this task for you! :>\n";
    public static final String TASK_FORMAT_ERROR = "Please enter a task following the format:\n %s\n"
            + "Then I'll know how to add it into your list. T^T";
    private final ArrayList<String> words;
    private final String firstWord;

    /**
     * Constructor for AddCommand.
     *
     * @param words the remaining user input after the first keyword
     * @param firstWord the first word in the user input
     */
    public AddCommand(ArrayList<String> words, String firstWord) {
        this.words = words;
        this.firstWord = firstWord;
    }


    /**
     * Executes the command for "todo", "deadline" and "event" keywords.
     * This is the main way for outputting bot replies.
     *
     * @param storage        the storage object
     * @param tasklist       the task list object
     * @return               the bot reply
     * @throws DukeException if the user input is unrecognised
     */
    @Override
    public String execute(Storage storage, TaskList tasklist) throws DukeException {
        StringBuilder output = new StringBuilder();
        switch (firstWord) {
        case "todo":
            addTodo(tasklist, output);
            break;
        case "deadline":
            addDeadline(tasklist, output);
            break;
        case "event":
            addEvent(tasklist, output);
            break;
        default:
            // Defensive coding for default statement.
            output.append(Messages.UNKNOWN_COMMAND);
        }
        return output.toString();
    }

    private void addTodo(TaskList tasklist, StringBuilder output) throws DukeException {
        if (words.size() == 0) {
            throw new DukeException(String.format(TASK_FORMAT_ERROR, "todo <task description>"));
        }
        String input = String.join(" ", words);
        Todo todo = new Todo(input);
        tasklist.addTask(todo);
        output.append(TASK_ADDED)
                .append(todo).append("\n")
                .append("You have ").append(tasklist.tasks.size())
                .append((tasklist.tasks.size() == 1 ? " task! :D" : " tasks! :D"));
    }

    private void addDeadline(TaskList tasklist, StringBuilder output) throws DukeException {
        if (!words.contains("/by")) {
            throw new DukeException(String.format(TASK_FORMAT_ERROR,
                    "deadline <task description> /by <deadline>"));
        }
        String remainingDdlWords = String.join(" ", words.subList(0, words.indexOf("/by")));
        String ddl = String.join(" ", words.subList(words.indexOf("/by") + 1, words.size()));
        Deadline deadline;
        if (ddl.matches("\\d{4}-\\d{2}-\\d{2}")) {
            LocalDate ddlDate = LocalDate.parse(ddl);
            String newDdl = ddlDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
            deadline = new Deadline(remainingDdlWords, newDdl);
        } else {
            deadline = new Deadline(remainingDdlWords, ddl);
        }
        tasklist.addTask(deadline);
        output.append(TASK_ADDED)
                .append(deadline).append("\n")
                .append("You have ").append(tasklist.tasks.size())
                .append((tasklist.tasks.size() == 1 ? " task! :D" : " tasks! :D"));
    }

    private void addEvent(TaskList tasklist, StringBuilder output) throws DukeException {
        if (!words.contains("/at")) {
            throw new DukeException(String.format(TASK_FORMAT_ERROR,
                    "event <task description> /at <location or time>"));
        }
        String remainingEventWords = String.join(" ", words.subList(0, words.indexOf("/at")));
        String evt = String.join(" ", words.subList(words.indexOf("/at") + 1, words.size()));
        Event event;
        if (evt.matches("\\d{4}-\\d{2}-\\d{2}")) {
            LocalDate evtDate = LocalDate.parse(evt);
            String newEvt = evtDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
            event = new Event(remainingEventWords, newEvt);
        } else {
            event = new Event(remainingEventWords, evt);
        }
        tasklist.addTask(event);
        output.append(TASK_ADDED)
                .append(event).append("\n")
                .append("You have ").append(tasklist.tasks.size())
                .append((tasklist.tasks.size() == 1 ? " task! :D" : " tasks! :D"));
    }
}
