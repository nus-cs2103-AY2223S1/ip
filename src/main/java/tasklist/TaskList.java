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

}
