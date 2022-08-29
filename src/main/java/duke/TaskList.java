package duke;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * TaskList class contains the task list that has operations such as
 * to add or delete tasks in the list.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private int taskCount;
    private Ui ui;

    /**
     * Constructor for TaskList. An empty ArrayList of type Task is created.
     */
    public TaskList() {
        tasks = new ArrayList<>();
        taskCount = 0;
    }

    /**
     * Constructor for TaskList that takes in an ArrayList of Task objects.
     *
     * @param arr an ArrayList of Task objects
     */
    public TaskList(ArrayList<Task> arr) {
        tasks = arr;
        ui = new Ui();
        taskCount = tasks.size();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param splitStr the string input
     * @param type the type of task
     * @throws DukeException if the input is invalid
     */
    public void addTask(String[] splitStr, Duke.TaskType type) throws DukeException {
        if (splitStr.length < 2) {
            throw new DukeException("The description of a task cannot be empty.");
        }
        switch (type) {
        case TODO:
            tasks.add(new Todo(splitStr[1]));
            taskCount++;
            ui.printMessage("Got it. I've added this task:\n       " + tasks.get(taskCount - 1)
                    + "\n     Now you have " + taskCount + " tasks in the list");
            break;
        case DEADLINE:
            String[] strDeadline = splitStr[1].split("/by", 2);
            if (strDeadline.length < 2 || strDeadline[1].equals("")) {
                throw new DukeException("Please also specify the date and time.");
            }
            LocalDateTime deadlineDateTime = Parser.parseDateTime(strDeadline[1].trim());
            tasks.add(new Deadline(strDeadline[0].trim(), deadlineDateTime));
            taskCount++;
            ui.printMessage("Got it. I've added this task:\n       " + tasks.get(taskCount - 1)
                    + "\n     Now you have " + taskCount + " tasks in the list.");
            break;
        case EVENT:
            String[] strEvent = splitStr[1].split("/at", 2);
            if (strEvent.length < 2 || strEvent[1].equals("")) {
                throw new DukeException("Please also specify the date and time.");
            }
            LocalDateTime eventDateTime = Parser.parseDateTime(strEvent[1].trim());
            tasks.add(new Event(strEvent[0].trim(), eventDateTime));
            taskCount++;
            ui.printMessage("Got it. I've added this task:\n       " + tasks.get(taskCount - 1)
                    + "\n     Now you have " + taskCount + " tasks in the list.");
            break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means!");
        }
    }

    /**
     * Deletes a task from the list.
     *
     * @param splitStr the string input
     * @throws DukeException if the input does not specify an int after delete
     */
    public void deleteTask(String[] splitStr) throws DukeException {
        if (splitStr.length < 2) {
            throw new DukeException("Please specify task number to delete.");
        }
        int deleteNo;
        try {
            deleteNo = Integer.parseInt(splitStr[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Please specify task number to delete.");
        }
        if (deleteNo <= 0) {
            throw new DukeException("Invalid task number.");
        }
        if (deleteNo > taskCount) {
            throw new DukeException("There are not that many tasks!");
        }
        taskCount--;
        ui.printMessage("Noted. I've removed this task:\n       " + tasks.get(deleteNo - 1)
                + "\n     Now you have " + taskCount + " tasks in the list.");
        tasks.remove(deleteNo - 1);
    }

    /**
     * Marks a task as done.
     *
     * @param splitStr the string input
     * @throws DukeException if the input does not specify a valid number after mark
     */
    public void mark(String[] splitStr) throws DukeException {
        if (splitStr.length < 2) {
            throw new DukeException("Please specify task number to mark.");
        }
        int markNo;
        try {
            markNo = Integer.parseInt(splitStr[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Please specify task number to mark.");
        }
        if (markNo <= 0) {
            throw new DukeException("Invalid task number.");
        }
        if (markNo > taskCount) {
            throw new DukeException("There are not that many tasks!");
        }
        tasks.get(markNo - 1).markAsDone();
        ui.printMessage("Nice! I've marked this task as done:\n       " + tasks.get(markNo - 1));
    }

    /**
     * Marks a task as undone.
     *
     * @param splitStr the string input
     * @throws DukeException if the input does not specify a valid number after unmark
     */
    public void unmark(String[] splitStr) throws DukeException {
        if (splitStr.length < 2) {
            throw new DukeException("Please specify task number to unmark.");
        }
        int unmarkNo;
        try {
            unmarkNo = Integer.parseInt(splitStr[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Please specify task number to unmark.");
        }
        if (unmarkNo <= 0) {
            throw new DukeException("Invalid task number.");
        }
        if (unmarkNo > taskCount) {
            throw new DukeException("There are not that many tasks!");
        }
        tasks.get(unmarkNo - 1).markAsUnDone();
        ui.printMessage("Nice! I've marked this task as not done yet:\n       " + tasks.get(unmarkNo - 1));
    }

    /**
     * Find a task by searching for a keyword.
     *
     * @param splitStr the string input
     * @throws DukeException if the input does not specify a keyword
     */
    public void findTasks(String[] splitStr) throws DukeException {
        int i = 1;
        String line = "    ____________________________________________________________";
        if (splitStr.length < 2) {
            throw new DukeException("Please specify keyword to search.");
        }
        String keyword = splitStr[1].trim();
        System.out.println(line);
        System.out.println("     Here are the matching tasks in your list:");
        for (Task t : tasks) {
            String[] words = t.splitDescriptionToWords();
            for (String word : words) {
                if (keyword.equals(word)) {
                    System.out.println("     " + i + "." + t);
                    i++;
                    break;
                }
            }
        }
        if (i == 1) {
            System.out.println("     No results found!");
        }
        System.out.println(line);
    }

    /**
     * Prints the entire TaskList.
     */
    public void printTaskList() {
        int i = 1;
        String line = "    ____________________________________________________________";
        System.out.println(line);
        System.out.println("     Here are the tasks in your list:");
        for (Task t : tasks) {
            System.out.println("     " + i + "." + t);
            i++;
        }
        System.out.println(line);
    }

    /**
     * Returns the ArrayLIst of Task objects
     *
     * @return ArrayLIst of Task objects
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
