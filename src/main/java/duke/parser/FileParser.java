package duke.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.ToDo;


public class FileParser {
    enum Symbol {
        T,
        D,
        E
    }

    private static final Pattern TASK_PATTERN =
            Pattern.compile("^([0-9]).\\[(T|D|E)\\]\\[(✓|✘)\\] (.[^\\(]*)(?: (.*: (.*?)))?\\)?$");
    private final TaskList taskList;

    /**
     * Constructor specifying tasklist to store read tasks into.
     *
     * @param tasks tasklist to store read tasks.
     */
    public FileParser(TaskList tasks) {
        this.taskList = tasks;
    }

    private Symbol generator(String action) throws InvalidCommandException {
        try {
            return Symbol.valueOf(action.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException();
        }
    }

    /**
     * Parses lines from tasklist.txt and stores them in tasklist.
     *
     * @param line each line in tasklist.txt file
     */
    public void handle(String line) {
        try {
            // Identify groups based on task pattern
            Matcher matcher = TASK_PATTERN.matcher(line);
            matcher.find();
            String type = matcher.group(2);
            String done = matcher.group(3);
            String desc = matcher.group(4);
            String time = matcher.group(6);
            Symbol symbol = generator(type);

            // Add task according to task type
            switch (symbol) {
            case T:
                ToDo todo = new ToDo(desc);
                if (done.equals("\u2713")) {
                    todo.markAsDone();
                }
                taskList.addTask(todo);
                break;
            case D:
                Deadline deadline = new Deadline(desc, time);
                if (done.equals("\u2713")) {
                    deadline.markAsDone();
                }
                taskList.addTask(deadline);
                break;
            case E:
                Event event = new Event(desc, time);
                if (done.equals("\u2713")) {
                    event.markAsDone();
                }
                taskList.addTask(event);
                break;
            default:
                break;
            }

        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}
