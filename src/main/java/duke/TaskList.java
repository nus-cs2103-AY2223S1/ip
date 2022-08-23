/**
 * Project done by Hong Jin.
 */
package duke;

import duke.task.*;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


/**
 * TaskList class to store the list of Tasks.
 *
 * Also allows user to mark and unmark the task in list.
 */
public class TaskList {
    private List<Task> memo;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" d/MM/uuuu");

    public TaskList(ArrayList<Task> memo) {
        this.memo = memo;
    }
    public String addTask(Task t) {
        memo.add(t);
        String note = "Now you have " + memo.size() + " tasks in the list.";
        return "Got it, I've added this task:\n      " + t.toString()
                + "\n    " + note;
    }

    public ArrayList<String> saveList() {
        ArrayList<String> result = new ArrayList<>();
        for (Task task : this.memo) {
            result.add(task.toString());
        }
        return result;
    }

    public String enumerate() {
        int count = 0;
        String str = "Here are the tasks in your list:";
        for (Task k : memo) {
            count ++;
            str += "\n    " + count + ". " + k.toString();
        }
        return str;
    }

    public String updateMark(int index) {
        this.memo.get(index - 1).mark();
        return "Nice! I've marked this task as done :)\n      "
                + this.memo.get(index - 1).toString();
    }

    public String updateUnmark(int index) {
        this.memo.get(index - 1).unmark();
        return "okay I mark this task as not done yet...\n      "
                + this.memo.get(index - 1).toString();
    }

    public String deleteTask(int index) {
        String temp = this.memo.get(index - 1).toString();
        this.memo.remove(index - 1);
        String noteUpdated = "Now you have " + memo.size() + " tasks in the list.";
        return "Noted. I've deleted this task:\n      " + temp
                + "\n    " + noteUpdated;
    }

    public int getSize() {
        return this.memo.size();
    }

    public Task get(int index) {
        return this.memo.get(index - 1);
    }
}
