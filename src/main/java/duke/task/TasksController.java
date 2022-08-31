package duke.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import duke.exception.EmptyContentException;
import duke.exception.InvalidTimeException;
import duke.exception.NoSuchTaskException;

/**
 * Logic controller of Duke tasks
 */
public class TasksController {
    private ArrayList<Task> tasks;

    public TasksController(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Checks if task content is empty.
     * @param content task content
     * @return a boolean value
     * @throws EmptyContentException if content is empty
     */
    public boolean checkTaskContent(String content) throws EmptyContentException {
        if (content.isBlank()) {
            throw new EmptyContentException("ERROR: No empty task is allowed! Please try again.");
        }
        return true;
    }


    /**
     * Return the target task.
     * @param taskIndex task index
     * @return the target task.
     * @throws NoSuchTaskException if the task doesn't exist
     */
    public Task getTask(int taskIndex) throws NoSuchTaskException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new NoSuchTaskException("ERROR: The task you choose doesn't exist!");
        }
        return tasks.get(taskIndex);
    }

    /**
     * Changes the status of a task
     * @param taskIndex index of the task in the list
     * @param status status to be changed
     * @throws NoSuchTaskException if the task doesn't exist
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

    /**
     * Deletes a task from the list.
     * @param taskIndex index of the task
     * @throws NoSuchTaskException if the task doesn't exist
     */
    public void deleteFromList(int taskIndex) throws NoSuchTaskException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new NoSuchTaskException("ERROR: The task you choose doesn't exist!");
        }
        tasks.remove(taskIndex);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds relevant tasks by keyword
     * @param keywords user's input
     * @return a list of relevant tasks
     */
    public ArrayList<Task> findByKeyword(String ...keywords) {
        System.out.println(Arrays.toString(keywords));
        ArrayList<Task> result = new ArrayList<>();
        for (String keyword: keywords) {
            for (Task task: tasks) {
                String content = task.getTaskDescription();
                if (content.contains(keyword)) {
                    result.add(task);
                }
            }

        }
        return result;
    }

    /**
     * Stringify task list
     * @return the string
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        int i;
        for (i = 0; i < tasks.size(); ++i) {
            s.append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return s.toString();
    }

}
