package gibson.task;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TaskList is basically a ArrayList of tasks.
 * It adds more niche features on to the ArrayList class
 * that a list of tasks would need.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Creates an empty list of tasks.
     */
    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    /**
     * Creates a populated list of tasks from a list of task.toString().
     * @param list a list where each entry is a task.toString()
     */
    public TaskList(List<String> list) {
        taskList = new ArrayList<Task>();
        for (String line : list) {
            Pattern pattern = Pattern.compile("]");
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            char type = line.charAt(matcher.start() - 1);
            matcher.find();
            char marker = line.charAt(matcher.start() - 1);
            String body = line.substring(matcher.end() + 1);
            Task t;
            if (type == 'T') {
                t = new Task(body);
            } else if (type == 'E') {
                String[] stringArray = Parser.substringBeforeAfterToken(body, "\\(at:");
                t = new Event(stringArray[0], stringArray[1].substring(0, stringArray[1].length() - 1));
            } else {
                String[] stringArray = Parser.substringBeforeAfterToken(body, "\\(by:");
                t = new Deadline(stringArray[0], stringArray[1].substring(0, stringArray[1].length() - 1));
            }
            if (marker == 'X') {
                t.mark();
            }
            taskList.add(t);
        }
    }

    /**
     * Appends the specified task to the end of the list
     * @param task the task to be appended
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Removes the task at the specified position in the list
     * @param i the position to remove from
     * @return the task that was removed
     */
    public Task remove(int i) {
        Task t = taskList.get(i);
        taskList.remove(i);
        return t;
    }

    /**
     * Marks the task at the specified position in the list
     * @param i the position of the task to mark
     * @return the task that was marked
     */
    public Task mark(int i) {
        Task t = taskList.get(i);
        t.mark();
        return t;
    }

    /**
     * Unmarks the task at the specified position in the list
     * @param i the position of the task to unmark
     * @return the task that was unmarked
     */
    public Task unmark(int i) {
        Task t = taskList.get(i);
        t.unmark();
        return t;
    }

    /**
     * Returns true of this list contains no tasks.
     * @return true if this list contains no tasks
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public boolean isDuplicate(Task task) {
        for (int i = 0; i < taskList.size(); i++) {
            if (task.equals(taskList.get(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a TaskList populated with tasks whose taskString matches the query.
     * @param searchQuery query string to match with
     * @return a TaskList populated with tasks whose taskString matches the query
     */
    public TaskList find(String searchQuery) {
        TaskList tl = new TaskList();
        for (Task t : taskList) {
            if (t.taskContains(searchQuery)) {
                tl.add(t);
            }
        }
        return tl;
    }

    /**
     * Returns the number of Tasks in the list
     * @return the number of Tasks in the list
     */
    public int size() {
        return taskList.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            sb.append(i + 1).append(".").append(taskList.get(i).toString());
            if (i + 1 < taskList.size()) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
