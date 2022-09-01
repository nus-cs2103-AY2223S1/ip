package ted.task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import ted.exception.TedException;

/**
 * Represents list of tasks. A <code>TaskList</code> object corresponds to the tasks saved
 * to the bot by the user.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates empty TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates Tasklist object with pre-existing tasks.
     *
     * @param tasks arraylist of tasks to be inserted into TaskList object.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns number of tasks in the TaskList.
     *
     * @return size of TaskList.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns String representation of the list of tasks in the TaskList object.
     *
     * @return String of task list.
     */
    public String list() {
        String listOfTasks = "";
        for (int i = 0; i < tasks.size(); i++) {
            int bulletPoint = i + 1;
            listOfTasks = listOfTasks + bulletPoint + ". " + tasks.get(i) + "\n";
        }
        return listOfTasks;
    }

    /**
     * Writes tasks in TaskList to file to be saved.
     *
     * @param fw FileWriter that writes to file with saved tasks.
     * @throws IOException if error is encountered writing to file.
     */
    public void writeToFile(FileWriter fw) throws IOException {
        for (int i = 0; i < tasks.size(); i++) {
            fw.write(tasks.get(i).toFileString());
        }
    }

    /**
     * Returns String representation of task marked.
     *
     * @param i task in TaskList to be marked.
     * @return marked task description.
     * @throws TedException if i index is out of bounds of the TaskList.
     */
    public String markTask(int i) throws TedException {
        if (i - 1 < 0 || i > tasks.size()) {
            throw new TedException("Oh no, there's no such task T_T\n");
        }

        tasks.get(i - 1).markDone();
        return tasks.get(i - 1).toString();
    }

    /**
     * Returns String representation of task unmarked.
     *
     * @param i task in TaskList to be unmarked.
     * @return unmarked task description.
     * @throws TedException if i index is out of bounds of the TaskList.
     */
    public String unmarkTask(int i) throws TedException {
        if (i - 1 < 0 || i > tasks.size()) {
            throw new TedException("Oh no, there's no such task T_T\n");
        }

        tasks.get(i - 1).unmarkDone();
        return tasks.get(i - 1).toString();
    }

    /**
     * Returns String representation of task added to TaskList.
     *
     * @param t Task object to be added to TaskList.
     * @return added task description.
     */
    public String addTask(Task t) {
        tasks.add(t);
        return t.toString();
    }

    /**
     * Returns String representation of task deleted from TaskList.
     *
     * @param i task in TaskList to be deleted.
     * @return deleted task description.
     * @throws TedException if i index is out of bounds of the TaskList.
     */
    public String deleteTask(int i) throws TedException {
        if (i - 1 < 0 || i > tasks.size()) {
            throw new TedException("Oh no, there's no such task T_T\n");
        }

        String task = tasks.get(i - 1).toString();
        tasks.remove(i - 1);
        return task;
    }

    /**
     * Returns String representation of the list of tasks in the TaskList object
     * that contain the keyword.
     *
     * @param keyword keyword to be searched for in TaskList.
     * @return String of filtered task list.
     */
    public String findTasks(String keyword) {
        String temp = "";
        int count = 1;

        for (int i = 0; i < tasks.size(); i++) {
            String toSearch = tasks.get(i).toString();
            if (toSearch.contains(keyword)) {
                temp = temp + count + ". " + toSearch + "\n";
                count++;
            }
        }
        return temp;
    }
}
