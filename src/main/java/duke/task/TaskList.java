package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exception.DukeException;

/**
 * Contains the list of tasks for Duke.
 */
public class TaskList {
    private static List<Task> savedTasks;
    private static final int INDEX_OF_TASK_STATUS = 1;
    private static final int INDEX_OF_DESCRIPTION = 2;
    private static final int INDEX_OF_DATE = 3;
    private static final int POSITION_OF_DESCRIPTION = 7;
    private static final String OUT_OF_BOUNDS_INDEX_MESSAGE = "Index has to be between 1 and ";

    /**
     * Constructs a new ArrayList to store tasks in the event when there is no stored tasks.
     */
    public TaskList() {
        TaskList.savedTasks = new ArrayList<Task>();
    }

    /**
     * Translates the stored tasks into an ArrayList for future edits.
     *
     * @param savedTasks the tasks which were stored previously
     * @throws DukeException
     */
    public TaskList(String savedTasks) throws DukeException {
        Scanner s = new Scanner(savedTasks);
        TaskList.savedTasks = new ArrayList<Task>();

        while (s.hasNext()) {
            Task newTask = null;

            String[] inputArr = s.nextLine().split(" \\| ");
            String taskType = inputArr[0];

            if (taskType.equals("T")) {
                newTask = new ToDo();
                newTask.addName("todo " + inputArr[INDEX_OF_DESCRIPTION]);
            } else if (taskType.equals("E")) {
                newTask = new Event();
                newTask.addName("event " + inputArr[INDEX_OF_DESCRIPTION] + " /at " + inputArr[INDEX_OF_DATE]);
            } else if (taskType.equals("D")) {
                newTask = new DeadLine();
                newTask.addName("deadline " + inputArr[INDEX_OF_DESCRIPTION] + " /by " + inputArr[INDEX_OF_DATE]);
            } else {
                throw new DukeException("File is corrupted!");
            }

            if (inputArr[INDEX_OF_TASK_STATUS].equals("1")) {
                newTask.markAsDone();
            }
            TaskList.savedTasks.add(newTask);
        }
    }

    /**
     * Adds a new task into the existing list of tasks.
     *
     * @param task the task which will be added
     */
    public String add(Task task) {
        TaskList.savedTasks.add(task);
        return "Got it. I've added this task:\n" + task.getStatus()
                + String.format("\nNow you have %d tasks in the list.", TaskList.savedTasks.size());
    }

    /**
     * Deletes a task from the existing list of tasks.
     * <p>
     * The index provided has to be within the length of the current list.
     *
     * @param index the index of the task which the user wishes to delete
     * @throws DukeException
     */
    public String delete(int index) throws DukeException {
        int size = TaskList.savedTasks.size();
        if (size == 0) {
            throw new DukeException("You cannot delete anything from an empty Task List!");
        }
        if (index <= 0 || index > size) {
            throw new DukeException(OUT_OF_BOUNDS_INDEX_MESSAGE + String.valueOf(size));
        } else {
            Task removedTask = TaskList.savedTasks.remove(index - 1);
            return "Noted. I've removed this task:\n" + removedTask.getStatus()
                    + String.format("\nNow you have %d tasks in the list.", size - 1);
        }
    }

    /**
     * Marks a task from the existing list of tasks.
     * <p>
     * The index provided has to be within the length of the current list.
     *
     * @param index the index of the task to be marked as done
     * @throws DukeException
     */
    public String mark(int index) throws DukeException {
        int size = TaskList.savedTasks.size();
        if (size == 0) {
            throw new DukeException("You cannot mark anything from an empty Task List!");
        }
        if (index <= 0 || index > size) {
            throw new DukeException(OUT_OF_BOUNDS_INDEX_MESSAGE + String.valueOf(size));
        } else {
            Task selectedTask = TaskList.savedTasks.get(index - 1);
            selectedTask.markAsDone();
            return "Nice! I've marked this task as done:\n" + selectedTask.getStatus();
        }
    }

    /**
     * Unmarks a task from the existing list of tasks.
     * <p>
     * The index provided has to be within the length of the current list.
     *
     * @param index the index of the task to be unmarked as done
     * @throws DukeException
     */
    public String unmark(int index) throws DukeException {
        int size = TaskList.savedTasks.size();
        if (size == 0) {
            throw new DukeException("You cannot unmark anything from an empty Task List!");
        }
        if (index <= 0 || index > size) {
            throw new DukeException(OUT_OF_BOUNDS_INDEX_MESSAGE + String.valueOf(size));
        } else {
            Task selectedTask = TaskList.savedTasks.get(index - 1);
            selectedTask.markAsUndone();
            return "OK, I've marked this task as not done yet:\n"
                    + selectedTask.getStatus();
        }
    }

    /**
     * Iterates the list of tasks and print out the individual task with its index, status of tasks,
     * description of task, and date of task if any.
     *
     * @param tasks the list of tasks to be printed out
     * @return the details of the tasks in a readable format
     */
    private String iterateList(List<Task> tasks) {
        String listString = "";
        for (int i = 0; i < tasks.size(); i++) {
            listString += String.format("%d. %s", i + 1, tasks.get(i).getStatus());
            if (i != tasks.size() - 1) {
                listString += "\n";
            }
        }
        return listString;
    }

    /**
     * Returns a string that is formatted to store the index of a task, the type of task, the
     * description of the task and the date of the task for future use.
     *
     * @return a String for storage purpose
     */
    public String writeTasks() {
        String dataWritten = "";

        for (int i = 0; i < TaskList.savedTasks.size(); i++) {
            dataWritten += TaskList.savedTasks.get(i).getTask();

            if (i != TaskList.savedTasks.size() - 1) {
                dataWritten += "\n";
            }
        }

        return dataWritten;
    }

    /**
     * Returns a string that is formatted to display the index of a task, the type of task, the
     * description of the task from the current list of tasks.
     *
     * @return a String to display the current list of tasks
     */
    public String getList() {
        return iterateList(TaskList.savedTasks);
    }

    /**
     * Finds all the current tasks that contain the keyword specified by the user.
     * This search includes keywords that are partially in the description.
     *
     * @param keyWord the keyword the user would like to search in the current tasks
     * @throws DukeException
     */
    public String find(String keyWord) throws DukeException {
        List<Task> tasksFound = new ArrayList<Task>();

        if (keyWord.equals("")) {
            throw new DukeException("Please input a keyword to find the task!");
        }

        for (int i = 0; i < TaskList.savedTasks.size(); i++) {
            Task currentTask = TaskList.savedTasks.get(i);

            String taskDescription = currentTask.getStatus().substring(POSITION_OF_DESCRIPTION);

            if (taskDescription.contains(keyWord)) {
                tasksFound.add(currentTask);
            }
        }
        return "Here are the matching tasks in your list:\n" + iterateList(tasksFound);
    }



}
