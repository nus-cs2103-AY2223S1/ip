/**
 * Project done by Hong Jin.
 */
import java.util.*;

/**
 * TaskList class to store the list of Tasks.
 *
 * Also allows user to mark and unmark the task in list.
 */
public class TaskList {
    List<Task> memo = new ArrayList<>();

    public String addTask(Task t) {
        memo.add(t);
        String note = "Now you have " + memo.size() + " tasks in the list.";
        return "Got it, I've added this task:\n      " + t.getTask()
                + "\n    " + note;
    }

    public String enumerate() {
        int count = 0;
        String str = "Here are the tasks in your list:";
        for (Task k : memo) {
            count ++;
            str += "\n    " + count + ". " + k.getTask();
        }
        return str;
    }

    public String updateMark(int index) {
        if (index > memo.size()) return "You don't have so many tasks phew...";
        this.memo.get(index - 1).mark();
        return "Nice! I've marked this task as done :)";
    }

    public String updateUnmark(int index) {
        if (index > memo.size()) return "You don't have so many tasks phew...";
        this.memo.get(index - 1).unmark();
        return "okay I mark this task as not done yet...";
    }

}
