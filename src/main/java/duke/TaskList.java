package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Defines <Code>TaskList</Code> class.
 * <p>
 *     Stores an <Code>ArrayList</Code> of <Code>Task</Code>.
 * </p>
 */
public class TaskList {
    /** <Code>List</Code> to store <Code>String</Code> inputs given. */
    private static final List<Task> taskList = new ArrayList<>();

    /**
     * Gets description as <Code>String</Code> from input.
     * @param input <Code>String</Code> of words given by user as input
     * @return      Return description of input task.
     */
    private String getDescription(String input) {
        if (input.startsWith("deadline")) {
            return input.substring(9, input.indexOf("/by ") - 1);
        } else if (input.startsWith("event")) {
            return input.substring(6, input.indexOf("/at ") - 1);
        } else if (input.startsWith("todo")) {
            return input.substring(5);
        }
        return null;
    }

    /**
     * Gets venue as <Code>String</Code> from user input.
     * @param input <Code>String</Code> of words given by user as input
     * @return      Return venue of input task as <Code>String</Code>.
     */
    private String getVenue(String input) {
        return input.substring(input.indexOf("/at ") + 4);
    }

    /**
     * Gets date as <Code>LocalDate</Code> from user input.
     * @param input <Code>String</Code> of words given by user as input.
     * @return      Return date of input task as <Code>LocalDate</Code>.
     */
    private LocalDate getDate(String input) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String dateString = input.substring(input.indexOf("/by ") + 4);
        return LocalDate.parse(dateString, formatter);
    }

    /**
     * Adds <Code>Task</Code> from <Code>String</Code> input into
     * <Code>TaskList</Code>.
     * @param input <Code>String</Code> format <Code>Task</Code> of to be
     *              added to <Code>TaskList</Code>.
     * @return      <Code>String</Code> output to be shown to user.
     */
    public String add(String input) {
        String[] inputArr = input.split(" ");

        Task newTask = null;
        switch (inputArr[0]) {
        case "deadline":
            newTask = new Deadline(getDescription(input), getDate(input));
            break;

        case "event":
            newTask = new Event(getDescription(input), getVenue(input));
            break;

        case "todo":
            newTask = new ToDo(getDescription(input));
            break;

        default:
            return "No task added.";
        }

        taskList.add(newTask);
        int size = this.getSize();
        return String.format("Got it. I've added this task:%n   %s%n"
                + "Now you have %d task%s in the list.%n",
                newTask, size, size == 1 ? "" : "s");
    }


    /**
     * Deletes a <Code>Task</Code> from <Code>TaskList</Code>.
     * @param input <Code>String</Code> user input command to delete
     *              a <Code>Task</Code>.
     * @return      <Code>String</Code> output to be shown to user.
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
     * Overrides <Code>toString</Code> method of <Code>TaskList</Code> object.
     * @return <Code>Task</Code> output to be shown to user.
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
     * Marks <Code>Task</Code> with given index as done.
     * @param index Index of task to be done. 1 based indexing.
     * @return      <Code>String</Code> output to be shown to user.
     */
    public String markDone(int index) {
        taskList.get(index - 1).markDone();
        return String.format("Nice! I've marked this task as done:%n   %s%n",
                taskList.get(index - 1));
    }

    /**
     * Marks <Code>Task</Code> with given index as undone.
     * @param index Index of task to be undone. 1 based indexing.
     * @return      <Code>String</Code> output to be shown to user.
     */
    public String markUndone(int index) {
        taskList.get(index - 1).markUnDone();
        return String.format(
                "OK, I've marked this task as not done yet:%n   %s%n",
                taskList.get(index - 1));
    }

    /**
     * Gets number of <Code>Task</Code>s in <Code>TaskList</Code>.
     * @return Number of tasks in <Code>TaskList</Code>.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Return <Code>TaskList</Code> as format to be saved in hard disk.
     * @return String of <Code>TaskList</Code> as format to be saved in file.
     */
    public String toFile() {
        StringBuilder res = new StringBuilder();
        for (Task task : taskList) {
            res.append(task.toFileFormat() + "\n");
        }
        return res.toString();
    }

    /**
     * Adds <Code>Task</Code> from file to <Code>TaskList</Code>.
     * @param dataArgs Array containing details of <Code>Task</Code> to be
     *                 added to <Code>TaskList</Code>.
     */
    public void addFromFile(String[] dataArgs) {
        Boolean isDone = dataArgs[1].equals("true");
        Task newTask = null;

        switch (dataArgs[0]) {
        case ("deadline"):
            LocalDate deadline = LocalDate.parse(dataArgs[3],
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            newTask = new Deadline(dataArgs[2], deadline, isDone);
            break;

        case ("event"):
            newTask = new Event(dataArgs[2], dataArgs[3], isDone);
            break;

        case ("todo"):
            newTask = new ToDo(dataArgs[2], isDone);
            break;

        default:
            break;
        }
        taskList.add(newTask);
    }

    /**
     * Finds given word among the <Code>Task</Code>s in <Code>TaskList</Code>.
     * @param keyword Word to search for.
     * @return        <Code>String</Code> with information of <Code>Task</Code>s
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
