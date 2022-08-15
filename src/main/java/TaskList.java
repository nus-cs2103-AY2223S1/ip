/**
 * Project done by Hong Jin.
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
            str += count + ". " + k.getTaskList() + "\n    ";
        }
        return str;
    }

    public String updateMark(int k) {
        this.memo.get(k - 1).mark();
        return "Nice! I've marked this task as done :)";
    }

    public String updateUnmark(int k) {
        this.memo.get(k - 1).unmark();
        return "okay I mark this task as not done yet...";
    }
}
