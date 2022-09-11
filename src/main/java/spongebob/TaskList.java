package spongebob;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import spongebob.task.Deadline;
import spongebob.task.Event;
import spongebob.task.Task;
import spongebob.task.ToDo;

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
     *
     * @param sc Scanner object to read the text file.
     */
    public TaskList(Scanner sc) {
        this.list = new ArrayList<>();
        try {
            while (sc.hasNextLine()) {
                String[] arguments = sc.nextLine().split(",");
                assert arguments.length > 0;
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
                    throw new SpongebobException("Invalid input from file.");
                }
            }
            System.out.println("Successfully loaded saved contents.\n" + this);
        } catch (SpongebobException e) {
            System.out.println(e.getMessage());
        } finally {
            sc.close();
        }
    }

    /**
     * Adds new task to the list.
     *
     * @param task New task.
     * @return String response after adding the new task.
     */
    public String add(Task task) {
        int prevSize = this.list.size();
        this.list.add(task);
        int currSize = this.list.size();
        assert currSize == prevSize + 1; // Ensure task has been added to list.
        StringBuilder stringBuilder = new StringBuilder("Got it. I've added this task:\n");
        stringBuilder.append(task);
        stringBuilder.append(String.format("Now you have %d tasks in the list.", this.list.size()));
        return stringBuilder.toString();
    }

    /**
     * Deletes task at specified index in the list.
     *
     * @param indexList List of indexes of tasks to be deleted from the list.
     * @return String response after deleting the task.
     */
    public String delete(Integer[] indexList) {
        int numOfDeletedTask = 0;
        for (int index : indexList) {
            try {
                if (index < 0 || index >= this.list.size()) {
                    throw new SpongebobException(
                            String.format("Could not delete index %d as it is not within the list.", index)
                    );
                }
                this.list.get(index).markToDelete();
                numOfDeletedTask++;
            } catch (SpongebobException e) {
                System.out.println(e.getMessage());
            }
        }
        this.list.removeIf(Task::isToBeDeleted);
        return numOfDeletedTask > 0
                ? String.format("%d task%s been deleted.", numOfDeletedTask, numOfDeletedTask > 1 ? "s have" : " has")
                : "Please select a task to be deleted within the list.";
    }

    /**
     * Marks task as completed.
     *
     * @param index Index of which task to be marked in the list.
     * @return String response after marking the task.
     * @throws SpongebobException If specified index is out of range of the list.
     */
    public String markDone(int index) throws SpongebobException {
        if (index < 0 || index >= this.list.size()) {
            throw new SpongebobException(String.format("Could not mark index %d as it is not within the list.", index));
        }
        this.list.get(index).markDone();
        return this.list.get(index).toString();
    }

    /**
     * Marks task as not completed.
     *
     * @param index Index of which task to be unmarked in the list.
     * @return String response after unmarking the task.
     * @throws SpongebobException If specified index is out of range of the list.
     */
    public String unmarkDone(int index) throws SpongebobException {
        if (index < 0 || index >= this.list.size()) {
            throw new SpongebobException(String.format("Could not unmark index %d as it is not within the list.", index));
        }
        this.list.get(index).markNotDone();
        return this.list.get(index).toString();
    }

    /**
     * Finds all tasks in the list based on a keyword.
     *
     * @param searchStr Specified search keyword.
     * @return String response after finding all the tasks.
     */
    public String find(String searchStr) {
        StringBuilder stringBuilder = new StringBuilder("Here are the matching tasks in your list:\n");
        int index = 1;
        for (int i = 0; i < this.list.size(); i++) {
            Task task = this.list.get(i);
            if (task.matchKeyword(searchStr.subSequence(0, searchStr.length()))) {
                stringBuilder.append(String.format("%d. %s", index++, task));
            }
        }
        stringBuilder.append("Search completed.");
        return stringBuilder.toString();
    }

    /**
     * Returns iterator of the list of tasks.
     *
     * Used in writing tasks to file in Storage class.
     * @return Iterator of the list of tasks.
     */
    public Iterator<Task> getIterator() {
        return this.list.iterator();
    }

    /**
     * Returns the string representation of all the tasks in the list.
     *
     * @return String representation of all the tasks in the list.
     */
    @Override
    public String toString() {
        if (this.list.size() < 1) {
            // List is empty
            return "You have no task in your list.\n";
        }
        StringBuilder stringBuilder = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < this.list.size(); i++) {
            stringBuilder.append(String.format("%d. %s", i + 1, this.list.get(i).toString()));
        }
        stringBuilder.append("When you're ready, you may mark them as complete.");
        return stringBuilder.toString();
    }
}
