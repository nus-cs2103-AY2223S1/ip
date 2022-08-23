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
public class TaskList {
    private  ArrayList<Task> userInputHistory = new ArrayList<>();

    public TaskList(ArrayList<Task> listOfTasksInStorage) throws DukeException{
        userInputHistory = listOfTasksInStorage;
    }

    public void addTask(Task t) {
        userInputHistory.add(t);
    }
    public void addEvent(Event e) {
        userInputHistory.add(e);
    }
    public void addDeadline(Deadline d) {
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
    public String markTask(int n) {
        userInputHistory.get(n - 1).markAsDone();
        if(userInputHistory.get(n - 1) instanceof  Task) {
            return StorageParser.storableTaskDescription(userInputHistory.get(n-1));
        } else if (userInputHistory.get(n - 1) instanceof  Event) {
            return StorageParser.storableEventDescription(userInputHistory.get(n-1));
        } else if (userInputHistory.get(n - 1) instanceof  Deadline) {
            return StorageParser.storableDeadlineDescription(userInputHistory.get(n-1));
        }

    }
    public String unmarkTask(int n) {
        userInputHistory.get(n - 1).markAsDone();
        return StorageParser.storableDescription(userInputHistory.get(n-1));
    }
}
