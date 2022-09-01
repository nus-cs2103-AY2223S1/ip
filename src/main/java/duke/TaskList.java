package duke;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import duke.controller.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Returns an instance of a task list.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Returns an instance of a task list with saved tasks.
     * @param sc Scanner object to read the text file.
     */
    public TaskList(Scanner sc) {
        this.list = new ArrayList<>();
        try {
            while (sc.hasNextLine()) {
                String[] arguments = sc.nextLine().split(",");
                switch (arguments[0]) {
                case "T":
                    this.list.add(new ToDo(arguments[1], arguments[2]));
                    break;
                case "D":
                    this.list.add(new Deadline(arguments[1], arguments[2], arguments[3]));
                    break;
                case "E":
                    this.list.add(new Event(arguments[1], arguments[2], arguments[3]));
                    break;
                default:
                    throw new DukeException("Invalid input from file.");
                }
            }
            Ui.showMsg("Successfully loaded saved contents.\n" + this);
        } catch (DukeException e) {
            Ui.showMsg(e.getMessage());
        } finally {
            sc.close();
        }
    }

    /**
     * Adds new task to the list.
     * @param task New task.
     * @return String response after adding the new task.
     */
    public String add(Task task) {
        this.list.add(task);
        StringBuilder stringBuilder = new StringBuilder("Got it. I've added this task:\n");
        stringBuilder.append(task);
        stringBuilder.append(String.format("\nNow you have %d tasks in the list.", this.list.size()));
        return stringBuilder.toString();
    }

    /**
     * Deletes task at specified index in the list.
     * @param index Index of which task to be deleted in the list.
     * @return String response after deleting the task.
     * @throws DukeException If specified index is out of range of the list.
     */
    public String delete(int index) throws DukeException {
        if (index < 0 || index >= this.list.size()) {
            throw new DukeException("Something went wrong!\nPlease select at task to be removed within the list.");
        }
        Task task = this.list.remove(index);
        StringBuilder stringBuilder = new StringBuilder("Noted. I've removed this task:\n");
        stringBuilder.append(task);
        stringBuilder.append(String.format("\nNow you have %d tasks in the list.", this.list.size()));
        return stringBuilder.toString();
    }

    /**
     * Marks task as completed.
     * @param index Index of which task to be marked in the list.
     * @return String response after marking the task.
     * @throws DukeException If specified index is out of range of the list.
     */
    public String markDone(int index) throws DukeException {
        if (index < 0 || index >= this.list.size()) {
            throw new DukeException("Something went wrong!\nPlease select at task to be marked within the list.");
        }
        this.list.get(index).markDone();
        return this.list.get(index).toString();
    }

    /**
     * Marks task as not completed.
     * @param index Index of which task to be unmarked in the list.
     * @return String response after unmarking the task.
     * @throws DukeException If specified index is out of range of the list.
     */
    public String unmarkDone(int index) throws DukeException {
        if (index < 0 || index >= this.list.size()) {
            throw new DukeException("Something went wrong!\nPlease select at task to be unmarked within the list.");
        }
        this.list.get(index).markNotDone();
        return this.list.get(index).toString();
    }

    /**
     * Finds all tasks in the list based on a keyword.
     * @param searchStr Specified search keyword.
     * @return String response after finding all the tasks.
     */
    public String find(String searchStr) {
        StringBuilder stringBuilder = new StringBuilder("Here are the matching tasks in your list:\n");
        int index = 1;
        for (int i = 0; i < this.list.size(); i++) {
            Task task = this.list.get(i);
            if (task.matchKeyword(searchStr.subSequence(0, searchStr.length()))) {
                stringBuilder.append(String.format("%d.%s", index++, task));
            }
        }
        stringBuilder.append("Search completed.");
        return stringBuilder.toString();
    }

    /**
     * Returns iterator of the list of tasks.
     * Used in writing tasks to file in Storage class.
     * @return Iterator of the list of tasks.
     */
    public Iterator<Task> toSave() {
        return this.list.iterator();
    }

    /**
     * Returns the string representation of all the tasks in the list.
     * @return String representation of all the tasks in the list.
     */
    @Override
    public String toString() {
        if (this.list.size() < 1) {
            // List is empty
            return "You have no task on your list.\n";
        }
        StringBuilder stringBuilder = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < this.list.size(); i++) {
            stringBuilder.append(String.format("%d.%s", i + 1, this.list.get(i).toString()));
        }
        stringBuilder.append("When you're ready, you may mark them as complete.");
        return stringBuilder.toString();
    }
}
