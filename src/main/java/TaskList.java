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
        this.memo.get(index - 1).mark();
        return "Nice! I've marked this task as done :)\n      "
                + this.memo.get(index - 1).getTask();
    }

    public String updateUnmark(int index) {
        this.memo.get(index - 1).unmark();
        return "okay I mark this task as not done yet...\n      "
                + this.memo.get(index - 1).getTask();
    }

    public String deleteTask(int index) {
        String temp = this.memo.get(index - 1).getTask();
        this.memo.remove(index - 1);
        String noteUpdated = "Now you have " + memo.size() + " tasks in the list.";
        return "Noted. I've deleted this task:\n      " + temp
                + "\n    " + noteUpdated;
    }
}
