package duke.tasks;

import java.util.ArrayList;

import duke.exceptions.DukeException;

/**
 * TaskList implements methods for TaskList objects.
 *
 * @author Isaac Li Haoyang
 * @version v0.1
 */
public class TaskList {

    private ArrayList<Task> taskList;
    private ArrayList<Task> prevTaskList;

    /**
     * Constructs new TaskList using the given ArrayList<Task>.
     *
     * @param taskArrayList the ArrayList to construct the TaskList from
     *
     * @throws DukeException to handle if the given ArrayList is empty or invalid
     */
    public TaskList(ArrayList<Task> taskArrayList) throws DukeException {
        this.taskList = taskArrayList;
    }

    /**
     * Constructs an empty TaskList if no ArrayList<Task> is given.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the task list is empty
     */
    public boolean isEmpty() {
        return this.taskList.size() == 0;
    }

    /**
     * Fetches the current number of tasks.
     *
     * @return an Integer representing the current number of tasks
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Prints out the current task list.
     */
    public void printList() {
        try {
            if (isEmpty()) {
                throw new DukeException(" ☹ OOPS!!! Seems like your list is empty.");
            } else {
                for (int i = 0; i < this.taskList.size(); i++) {
                    System.out.println("     " + (i + 1) + ". " + this.taskList.get(i).toString());
                }
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints out list of tasks that contains the keyword searched for by the user
     *
     * @param input the keyword to be searched for by the user
     */
    public void findTask(String input) {
        boolean noMatches = true;
        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = this.taskList.get(i);
            if (task.getDesc().contains(input)) {
                noMatches = false;
                System.out.println("     " + (i + 1) + ". " + task);
            }
        }
        if (noMatches) {
            try {
                throw new DukeException(" ☹ OOPS!!! Seems like there are no tasks matching this description.");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param taskIndex the index of the task to be marked
     */
    public void markTask(int taskIndex) {
        this.taskList.get(taskIndex).mark();
    }

    /**
     * Marks the task at the given index as not done.
     *
     * @param taskIndex the index of the task to be marked
     */
    public void unmarkTask(int taskIndex) {
        this.taskList.get(taskIndex).unmark();
    }

    /**
     * Adds given task to the task list.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Fetches the task at the given index of the list.
     *
     * @param taskId index of the task to be fetched
     *
     * @return the task at the given index
     */
    public Task getTask(int taskId) {
        return this.taskList.get(taskId);
    }

    /**
     * Deletes the task at the given index from the list.
     *
     * @param index index of the task to be deleted
     */
    public void deleteTask(int index) {
        this.taskList.remove(index);
    }

    /**
     * Clears out the task list.
     */
    public void deleteAll() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Fetches all the tasks in the task list.
     *
     * @return an ArrayList containing all the tasks in the task list
     */
    public ArrayList<Task> getAllTasks() {
        return this.taskList;
    }

}
