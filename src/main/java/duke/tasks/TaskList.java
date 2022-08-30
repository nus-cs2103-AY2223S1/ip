package duke.tasks;

import duke.exceptions.DukeException;

import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> taskList;

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
    public void printList() {
        try {
            if (isEmpty()) {
                throw new DukeException(" ☹ OOPS!!! Seems like your list is empty.");
            } else {
                for (int i = 0; i < taskList.size(); i++)
                    System.out.println("     " + (i + 1) + ". " + taskList.get(i).toString());
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
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
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
        taskList.get(taskIndex).mark();
    }

    /**
     * Marks the task at the given index as not done.
     *
     * @param taskIndex the index of the task to be marked
     */
    public void unmarkTask(int taskIndex) {
        taskList.get(taskIndex).unmark();
    }

    /**
     * Adds given task to the task list.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        taskList.add(task);
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
     * Deletes the task at the given index from the list.
     *
     * @param index index of the task to be deleted
     */
    public void deleteTask(int index) {
        taskList.remove(index);
    }

    /**
     * Clears out the task list.
     */
    public void deleteAll() {
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
}
