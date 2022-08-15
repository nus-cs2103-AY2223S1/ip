/**
 * Project done by Hong Jin
 */
import java.util.*;

/**
 * TaskList class to store the list of Tasks.
 */
public class TaskList {
    List<Task> memo = new ArrayList<>();

    public String addTask(Task t) {
        memo.add(t);
        return "added: " + t.getTask();
    }

    public String enumerate() {
        int count = 0;
        String str = "";
        for (Task k : memo) {
            count ++;
            str += count + ". " + k.getTask() + "\n    ";
        }
        return str;
    }
}
