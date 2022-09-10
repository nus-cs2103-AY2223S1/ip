package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Level;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Defines {@code TaskList} class.
 * <p>
 *     Stores an {@code ArrayList} of {@code Task}.
 * </p>
 */
public class TaskList {
    /** {@code List} to store {@code String} inputs given. */
    private static final List<Task> taskList = new ArrayList<>();

    /**
     * Gets description as {@code String} from input.
     * @param input {@code String} of words given by user as input
     * @return      Return description of input task.
     */
    private String getDescription(String input) {
        if (input.startsWith("deadline")) {
            return input.substring(9, input.indexOf("/by ") - 1);
        } else if (input.startsWith("event")) {
            return input.substring(6, input.indexOf("/at ") - 1);
        } else if (input.startsWith("todo") && input.contains("/p ")) {
            // Case where "todo" command was given with priority.
            return input.substring(5, input.indexOf("/p ") - 1);
        } else if (input.startsWith("todo") && !input.contains("/p ")) {
            // Case where "todo" command was NOT given with priority.
            return input.substring(5);
        }
        assert false : "`getDescription` method should not be called on inputs"
                + "that do not start with 'deadline', 'event' or 'toDo'.";
        return null;
    }

    /**
     * Gets venue as {@code String} from user input.
     * @param input {@code String} given by user as input
     * @return      Return venue of input task as {@code String}.
     */
    private String getVenue(String input) {
        String delimiter = "/at ";
        int startIndex = input.indexOf("/at ") + delimiter.length();
        int endIndex = input.indexOf("/p") - 1;
        if (endIndex < startIndex) {
            // For case where input is given without priority.
            return input.substring(startIndex);
        } else {
            return input.substring(startIndex, endIndex);
        }
    }

    /**
     * Gets date as {@code LocalDate} from user input.
     * @param input {@code String} given by user as input.
     * @return      Return date of input task as {@code LocalDate}.
     */
    private LocalDate getDate(String input) {
        String delimiter = "/by ";
        String dateString;
        int startIndex = input.indexOf("/by ") + delimiter.length();
        int endIndex = input.indexOf("/p") - 1;
        if (endIndex < startIndex) {
            // For case where input is given without priority.
            dateString = input.substring(startIndex);
        } else {
            dateString = input.substring(startIndex, endIndex);
        }
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return LocalDate.parse(dateString, formatter);
    }


    /**
     * Gets priority as {@code Level}from user input.
     * @param input {@code String}given by user as input.
     * @return      Return priority level of input task as {@code Level}.
     */
    public Level getPriority(String input) {
        String delimiter = "/p ";
        int index = input.indexOf(delimiter);
        Boolean hasPriority = (index >= 0);
        if (hasPriority) {
            switch (input.substring(index + delimiter.length()).toLowerCase()) {
            case "low":
                return Level.LOW;
            case "med":
                return Level.MEDIUM;
            case "high":
                return Level.HIGH;
            default:
                assert false : "Priority levels should only be "
                        + "'low', 'med' or 'high'";
                return null;
            }
        } else {
            // Tasks without priority level defined are defaulted
            // to LOW priority.
            return Level.LOW;
        }
    }

    /**
     * Adds {@code Task} from {@code String} input into
     * {@code TaskList}.
     * @param input {@code String} format {@code Task} of to be
     *              added to {@code TaskList}.
     * @return      {@code String} output to be shown to user.
     */
    public String add(String input) {
        String[] inputArr = input.split(" ");

        Task newTask = null;
        switch (inputArr[0]) {
        case "deadline":
            newTask = new Deadline(
                    getDescription(input), getDate(input), getPriority(input));
            break;

        case "event":
            newTask = new Event(
                    getDescription(input), getVenue(input), getPriority(input));
            break;

        case "todo":
            newTask = new ToDo(getDescription(input), getPriority(input));
            break;

        default:
            assert false : "`add` method should not be called on inputs that "
                    + "do not start with 'deadline', 'event' or 'toDo'.";
        }

        taskList.add(newTask);
        int size = this.getSize();
        return String.format("Got it. I've added this task:%n   %s%n"
                + "Now you have %d task%s in the list.%n",
                newTask, size, size == 1 ? "" : "s");
    }


    /**
     * Deletes a {@code Task} from {@code TaskList}.
     * @param input {@code String} user input command to delete
     *              a {@code Task}.
     * @return      {@code String} output to be shown to user.
     */
    public String delete(String input) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        String taskToDelete = taskList.get(index).toString();
        taskList.remove(index);
        int size = this.getSize();
        return String.format("Got it. I've removed this task:%n   %s%n"
                + "Now you have %d task%s in the list.%n",
                taskToDelete, size, size == 1 ? "" : "s");
    }

    /**
     * Overrides {@code toString} method of {@code TaskList} object.
     * @return {@code Task} output to be shown to user.
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(
                String.format("Here are the tasks in your list:%n"));
        for (int i = 0; i < taskList.toArray().length; i++) {
            res.append(String.format("%d. %s%n", i + 1, taskList.get(i)));
        }
        return res.toString();
    }

    /**
     * Marks {@code Task} with given index as done.
     * @param index Index of task to be done. 1 based indexing.
     * @return      {@code String} output to be shown to user.
     */
    public String markDone(int index) {
        taskList.get(index - 1).markDone();
        return String.format("Nice! I've marked this task as done:%n   %s%n",
                taskList.get(index - 1));
    }

    /**
     * Marks {@code Task} with given index as undone.
     * @param index Index of task to be undone. 1 based indexing.
     * @return      {@code String} output to be shown to user.
     */
    public String markUndone(int index) {
        taskList.get(index - 1).markUnDone();
        return String.format(
                "OK, I've marked this task as not done yet:%n   %s%n",
                taskList.get(index - 1));
    }

    /**
     * Gets number of {@code Task}s in {@code TaskList}.
     * @return Number of tasks in {@code TaskList}.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Returns {@code TaskList} as format to be saved in hard disk.
     * @return String of {@code TaskList} as format to be saved in file.
     */
    public String toFile() {
        StringBuilder res = new StringBuilder();
        for (Task task : taskList) {
            res.append(task.toFileFormat() + "\n");
        }
        return res.toString();
    }

    /**
     * Adds {@code Task} from file to {@code TaskList}.
     * @param dataArgs Array containing details of {@code Task} to be
     *                 added to {@code TaskList}.
     */
    public void addFromFile(String[] dataArgs) {
        String command = dataArgs[0];
        Boolean isDone = dataArgs[1].equals("true");
        String description = dataArgs[2];
        String priority = dataArgs[3];
        Level priorityForConstructor;
        Task newTask = null;
        switch (priority) {
        case "LOW":
            priorityForConstructor = Level.LOW;
            break;
        case "MEDIUM":
            priorityForConstructor = Level.MEDIUM;
            break;
        case "HIGH":
            priorityForConstructor = Level.HIGH;
            break;
        default:
            assert false : "Priority levels should only be "
                    + "'low', 'med' or 'high'";
            priorityForConstructor = null;
        }

        switch (command) {
        case ("deadline"):
            LocalDate deadline = LocalDate.parse(dataArgs[4],
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            newTask = new Deadline(
                    description, deadline, isDone, priorityForConstructor);
            break;
        case ("event"):
            String venue = dataArgs[4];
            newTask = new Event(
                    description, venue, isDone, priorityForConstructor);
            break;
        case ("todo"):
            newTask = new ToDo(description, isDone, priorityForConstructor);
            break;
        default:
            assert false : "`addFromFile` method should not be called on "
                    + "inputs that do not start with 'deadline', 'event' "
                    + "or 'toDo'.";
        }
        taskList.add(newTask);
    }

    /**
     * Finds given word among the {@code Task}s in {@code TaskList}.
     * @param keyword Word to search for.
     * @return        {@code String} with information of {@code Task}s
     *                containing word.
     */
    public String findWord(String keyword) {
        StringBuilder output = new StringBuilder();
        output.append("Here are the matching tasks in your list:\n");
        for (Task task : taskList) {
            if (task.hasWord(keyword)) {
                output.append(task.toString() + "\n");
            }
        }
        return output.toString();
    }
}
