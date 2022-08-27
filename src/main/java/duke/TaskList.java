package duke;

import java.util.ArrayList;

/**
 * A Class to store the ArrayList of Tasks of a user, and deal with commands that edit the list.
 *
 * @author Denzel Tan
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * The constructor for the TaskList class.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<String> strList) {
        this.taskList = new ArrayList<>();
        for (String s : strList) {
            this.taskList.add(Parser.parseTaskFromText(s));
        }
    }


    /**
     * Marks the specified task as done.
     *
     * @param index The index of the task to be mark done
     */
    public void markTaskAsDone(int index) throws DukeException {
        if (1 <= index && index <= taskList.size()) {
            Task currTask = taskList.get(index - 1);
            currTask.markAsDone();
            Ui.markedDoneMessage(currTask);
        } else {
            throw new DukeException("Input a valid task index!");
        }
    }


    /**
     * Unmarks the specified task as not done.
     *
     * @param index The index of the task to be unmarked
     */
    public void unmarkTask(int index) throws DukeException {
        if (1 <= index && index <= taskList.size()) {
            Task currTask = taskList.get(index - 1);
            currTask.markUndone();
            Ui.markUndoneMessage(currTask);
        } else {
            throw new DukeException("Input a valid task index!");
        }
    }


    /**
     * Deletes the specified task.
     *
     * @param index The index of the task to be deleted
     */
    public void deleteTask(int index) throws DukeException {
        if (1 <= index && index <= taskList.size()) {
            System.out.println("Noted. I've removed this task:\n  " + taskList.get(index - 1));
            taskList.remove(index - 1);
            System.out.println("Now you have " + taskList.size() + " taskList in the list.");
        } else {
            throw new DukeException("Input a valid task index!");
        }
    }


    /**
     * Returns the size of the task list.
     *
     * @return the size of the task list
     */
    public int size() {
        return this.taskList.size();
    }


    /**
     * Returns the task at the index of the array of tasks.
     *
     * @param index the index of the array of tasks that the task is wanted
     * @return the task that is at the given index of the array
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }


    /**
     * Adds the given task to the list of tasks.
     *
     * @param task the task to be added to the list of tasks
     */
    public void add(Task task) {
        this.taskList.add(task);
    }
}
