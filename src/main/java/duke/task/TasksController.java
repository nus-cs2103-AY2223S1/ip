package duke.task;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import duke.exception.EmptyContentException;
import duke.exception.InvalidTimeException;
import duke.exception.NoSuchTaskException;

public class TasksController {
    private ArrayList<Task> tasks;

    public TasksController(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean checkTaskContent(String content) throws EmptyContentException {
        if (content.isBlank()) {
            throw new EmptyContentException("ERROR: No empty task is allowed! Please try again.");
        }
        return true;
    }

    /**
     * Check the legitimacy of user's input of time
     * @param s time string
     * @return boolean value
     */
    public boolean checkTimeFormat(String s) throws InvalidTimeException {
        Pattern pattern1 = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
        Pattern pattern2 = Pattern.compile("[0-9]{2}:[0-9]{2}");
        Matcher matcher1 = pattern1.matcher(s);
        Matcher matcher2 = pattern2.matcher(s);
        if(!(matcher1.find() && matcher2.find())) {
            throw new InvalidTimeException("ERROR");
        }
        return true;
    }

    /**
     * Return the target task.
     * @param taskIndex task index
     * @return the target task.
     */
    public Task getTask(int taskIndex) throws NoSuchTaskException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new NoSuchTaskException("ERROR: The task you choose doesn't exist!");
        }
        return tasks.get(taskIndex);
    }

    /**
     * A bridging method for changing the status of a task
     */
    public void changeTaskStatus(int taskIndex, boolean status) throws NoSuchTaskException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new NoSuchTaskException("ERROR: The task you choose doesn't exist!");
        }
        tasks.get(taskIndex).changeStatus(status);
    }

    /**
     * Add a new task to the list
     * @param task task to be added
     */
    public void addToList(Task task) {
        tasks.add(task);
    }

    public void deleteFromList(int taskIndex) throws NoSuchTaskException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new NoSuchTaskException("ERROR: The task you choose doesn't exist!");
        }
        tasks.remove(taskIndex);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < tasks.size(); ++i) {
            s.append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return s.toString();
    }

}
