package dobby;

import dobby.tasks.Deadline;
import dobby.tasks.Event;
import dobby.tasks.Task;
import dobby.tasks.Todo;

import java.util.ArrayList;
import java.util.List;

/**
 * DobbyList is a class that stores lists of tasks
 * and provides methods to manipulate the list.
 */
public class DobbyList {
    private static List<Task> dobbyList = new ArrayList<>();

    /**
     * Adds task to the list.
     *
     * @param newTask task to be added
     */
    public void add(Task newTask) {
        dobbyList.add(newTask);
    }

    /**
     * Marks task done at index specified
     *
     * @param toMark index specified
     */
    public void mark(int toMark) {
        dobbyList.get(toMark - 1).mark();
    }

    /**
     * Marks task not done at index specified
     *
     * @param toUnmark index specified
     */
    public void unmark(int toUnmark) {
        dobbyList.get(toUnmark - 1).unmark();
    }

    /**
     * Deletes task at index specified
     *
     * @param toDelete index specified
     */
    public void delete(int toDelete) {
        dobbyList.remove(toDelete - 1);
    }

    /**
     * Adding string from .txt file as task to list.
     *
     * @param string string of task to be added
     */
    //adding task to dL from .txt file
    public void addTask(String string) {
        String taskType = Parser.getTaskTypeTxt(string);
        boolean isDone = Parser.getStatusTxt(string);
        String rest = Parser.getRestTxt(string);

        if (taskType.equals("T")) {
            add(new Todo(rest, isDone));
        } else if (taskType.equals("E") || taskType.equals("D")) {
            String desc = Parser.getDescTxt(rest);
            String date = Parser.getDateTxt(rest);
            if (taskType.equals("E")) {
                add(new Event(desc, date, isDone));
            } else {
                add(new Deadline(desc, date, isDone));
            }
        } else {
            DobbyChat.wrongTaskFormat();
        }
    }

    /**
     * Returns list of string of tasks.
     *
     * @return String representation of list of string of tasks.
     */
    @Override
    public String toString() {
        String dobbyListString = "";
        String intro = "Here are the tasks in your list:\n\t";

        int i = 0;
        for (Task dobbyTask : dobbyList) {
            dobbyListString += (i + 1) + "." + dobbyTask.toString() + "\n\t";
            i++;
        }
        return intro + dobbyListString;
    }

    /**
     * Finds tasks contaning a specific string in the list.
     *
     * @param toFind specific string to find.
     * @return String representation of list of string of tasks containing the specific string.
     */
    public String toFind(String toFind) {
        String foundListString = "";
        String intro = "Here are the matching tasks in your list:\n\t";

        int i = 0;
        for (Task dobbyTask : dobbyList) {
            if (dobbyTask.isPresent(toFind)) {
                foundListString += (i + 1) + "." + dobbyTask.toString() + "\n\t";
                i++;
            } else {
                continue;
            }
        }
        return intro + foundListString;
    }

    /**
     * Returns list of tasks to be saved in the .txt file
     *
     * @return String of list of tasks.
     */
    public String toPrint() {
        if (getLength() == 0) {
            return "No Task Available";
        } else {
            String dobbyListString = "";
            for (Task dobbyTask : dobbyList) {
                dobbyListString += dobbyTask.toPrint() + "\n";
            }
            return dobbyListString;
        }
    }

    /**
     * Returns String representation of task at the specified index.
     *
     * @param i specified index
     * @return String representation of task
     */
    public String getTaskString(int i) {
        return dobbyList.get(i - 1).toString();
    }

    /**
     * Returns tasks at the specified index.
     *
     * @param i specified index
     * @return task
     */
    public Task getTask(int i) {
        return dobbyList.get(i - 1);
    }

    /**
     * Returns length of list.
     *
     * @return length of list
     */
    public int getLength() {
        return dobbyList.size();
    }

    /**
     * Returns whether or not the list is empty.
     *
     * @return true if list is empty, false if list contains at least one task.
     */
    public Boolean isEmpty() {
        return dobbyList.size() == 0;
    }
}