package duke;

import java.io.IOException;
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
    @SafeVarargs
    public TaskList(ArrayList<String>... strList) {
        this.taskList = new ArrayList<>();
        for (ArrayList<String> a : strList) {
            for (String s : a) {
                this.taskList.add(Parser.parseTaskFromText(s));
            }
        }
    }


    /**
     * Marks the specified task as done.
     *
     * @param index The index of the task to be mark done
     */
    public String markTaskAsDone(int index) throws DukeException, IOException {
        if (1 <= index && index <= taskList.size()) {
            Task currTask = taskList.get(index - 1);
            currTask.markAsDone();
            Storage.writeToFile(this);
            return Ui.markedDoneMessage(currTask);
        } else {
            throw new DukeException("Input a valid task index!");
        }
    }


    /**
     * Unmarks the specified task as not done.
     *
     * @param index The index of the task to be unmarked
     */
    public String unmarkTask(int index) throws DukeException, IOException {
        if (1 <= index && index <= taskList.size()) {
            Task currTask = taskList.get(index - 1);
            currTask.markUndone();
            Storage.writeToFile(this);
            return Ui.markUndoneMessage(currTask);
        } else {
            throw new DukeException("Input a valid task index!");
        }
    }


    /**
     * Deletes the specified task.
     *
     * @param index the index of the task to be deleted
     * @return the string to show after a task has been deleted
     */
    public String deleteTask(int index) throws DukeException, IOException {
        if (1 <= index && index <= taskList.size()) {
            Task tempTask = taskList.get(index - 1);
            taskList.remove(index - 1);
            Storage.writeToFile(this);
            return "Noted. I've removed this task:\n  " + tempTask + "\nNow you have " + taskList.size() +
                    " tasks in the list.";
        } else {
            throw new DukeException("Input a valid task index!");
        }
    }


    /**
     * Tags the specified task.
     *
     * @param index the index of the task to be deleted
     * @param tag the string to set the tag as
     * @return the string to show after a task has been tagged
     */
    public String tagTask(int index, String tag) throws DukeException, IOException {
        if (1 <= index && index <= taskList.size()) {
            Task tempTask = taskList.get(index - 1);
            tempTask.setTag(tag);
            Storage.writeToFile(this);
            return "Noted. I've tagged this task:\n  " + tempTask + "\nwith #" + tag;
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
        assert (1 <= index && index <= taskList.size());
        return this.taskList.get(index);
    }


    /**
     * Adds the given task to the list of tasks.
     *
     * @param task the task to be added to the list of tasks
     */
    public void add(Task task) {
        assert task != null;
        this.taskList.add(task);
    }


    /**
     * Finds the tasks in the task list that contain the given string.
     *
     * @param str the given string that is used to find the tasks
     * @return the string to show after the tasks have been found
     */
    public String find(String str) {
        assert !str.isEmpty();
        return Ui.printFoundTasks(str, this);
    }


    /**
     * Finds the tasks in the task list that have the given tag.
     *
     * @param tag the given tag that is used to find the tasks
     * @return the string to show after the tasks with the given tag have been found
     */
    public String findTag(String tag) {
        assert !tag.isEmpty();
        return Ui.printTaggedTasks(tag, this);
    }


    /**
     * Returns the String representation of a TaskList object.
     * @return the String representation of a TaskList object
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < this.taskList.size(); i++) {
            res.append(String.format("%d. %s\n", 1 + i, this.taskList.get(i)));
        }
        return res.toString();
    }
}
