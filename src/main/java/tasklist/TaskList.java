package tasklist;
import exceptions.DukeException;

import java.util.ArrayList;
import task.Task;
import task.Event;
import task.Deadline;

/**
 * Represents ArrayList maintained in program
 */
import task.Task;
import utility.StorageParser;

/**
 * Manage all interactions between Duke and UserInputHistory FILE storage.
 */
public class TaskList<T extends Task> {
    private  ArrayList<T> userInputHistory = new ArrayList<>();

    public void addTask(T t) {
        userInputHistory.add(t);
    }
    public void addEvent(T e) {
        userInputHistory.add(e);
    }
    public void addDeadline(T d) {
        userInputHistory.add(d);
    }
    public void deleteTask(int n) {
        userInputHistory.remove(n - 1);
    }
    public boolean checkIsToday(int n) {return userInputHistory.get(n - 1).isToday();}
    public String getLongDescription(int n) {return userInputHistory.get(n - 1).longDescription();}
    public int getSize() {
        return userInputHistory.size();
    }
    public StringBuffer getContents() {
        StringBuffer list = new StringBuffer();
        for (int i = 0; i < userInputHistory.size() ; i++) {
            list.append((i+1) + ". " + userInputHistory.get(i)+"\n");
        }
        return list;
    }
    public void markTask(int n) {
        userInputHistory.get(n - 1).markAsDone();
    }
    public void unmarkTask(int n) {
        userInputHistory.get(n - 1).markAsNotDone();
    }

    public T getTask(int n) {
        return userInputHistory.get(n - 1);
    }

    public ArrayList<T> findTasks(String keyword) {
        return null;
    }
}
