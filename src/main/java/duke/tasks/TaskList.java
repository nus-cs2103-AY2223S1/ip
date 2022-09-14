package duke.tasks;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.storage.Storage;

/**
 * TaskList implements methods for TaskList objects.
 *
 * @author Isaac Li Haoyang
 * @version v0.2
 */
public class TaskList {

    private static ArrayList<Task> taskList;
    private final Storage prevStorage = new Storage("data/prevTaskList.txt");

    /**
     * Constructs new TaskList using the given ArrayList<Task>.
     *
     * @param taskArrayList the ArrayList to construct the TaskList from
     *
     * @throws DukeException to handle if the given ArrayList is empty or invalid
     */
    public TaskList(ArrayList<Task> taskArrayList) throws DukeException {
        taskList = taskArrayList;
    }

    /**
     * Constructs an empty TaskList if no ArrayList<Task> is given.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the task list is empty
     */
    public boolean isEmpty() {
        return taskList.size() == 0;
    }

    /**
     * Fetches the current number of tasks.
     *
     * @return an Integer representing the current number of tasks
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Prints out the current task list.
     */
    public String printList() {
        StringBuilder res = new StringBuilder();
        try {
            if (isEmpty()) {
                throw new DukeException(" ☹ OOPS!!! Seems like your list is empty.");
            } else {
                for (int i = 0; i < taskList.size(); i++) {
                    res.append("     ").append(i + 1).append(". ")
                            .append(taskList.get(i).toString())
                            .append("\n");
                }
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return res.toString();
    }

    /**
     * Prints out list of tasks that contains the keyword searched for by the user
     *
     * @param input the keyword to be searched for by the user
     */
    public String findTask(String input) {
        boolean noMatches = true;
        ArrayList<String> res = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.getDesc().contains(input)) {
                noMatches = false;
                res.add( "     " + (i + 1) + ". " + task);
            }
        }
        if (noMatches) {
            try {
                throw new DukeException(" ☹ OOPS!!! Seems like there are no tasks matching this description.");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        return res.toString();
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param taskIndex the index of the task to be marked
     */
    public void markTask(int taskIndex) {
        saveTaskList();
        taskList.get(taskIndex).mark();
    }

    /**
     * Marks the task at the given index as not done.
     *
     * @param taskIndex the index of the task to be marked
     */
    public void unmarkTask(int taskIndex) {
        saveTaskList();
        taskList.get(taskIndex).unmark();
    }

    /**
     * Adds given task to the task list.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        saveTaskList();
        taskList.add(task);
    }

    /**
     * Deletes the task at the given index from the list.
     *
     * @param index index of the task to be deleted
     */
    public void deleteTask(int index) {
        saveTaskList();
        taskList.remove(index);
    }

    /**
     * Fetches the task at the given index of the list.
     *
     * @param taskId index of the task to be fetched
     *
     * @return the task at the given index
     */
    public Task getTask(int taskId) {
        return taskList.get(taskId);
    }

    /**
     * Clears out the task list.
     */
    public void deleteAll() {
        saveTaskList();
        taskList = new ArrayList<>();
    }

    /**
     * Fetches all the tasks in the task list.
     *
     * @return an ArrayList containing all the tasks in the task list
     */
    public ArrayList<Task> getAllTasks() {
        return taskList;
    }

    /**
     * Saves the taskList into src/main/data/prevTaskList.txt.
     */
    public void saveTaskList() {
        prevStorage.store(taskList);
    }

    /**
     * Updates the taskList as the previously saved taskList.
     */
    public void undo() {
        taskList = prevStorage.load();
    }
}
