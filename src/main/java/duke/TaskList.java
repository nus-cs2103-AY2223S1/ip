/**
 * Project Duke CS2103
 * Done by Hong Jin.
 */
package duke;

import duke.task.*;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


/**
 * Class TaskList to store the list of Tasks.
 */
public class TaskList {
    private List<Task> memo;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" d/MM/uuuu");

    /**
     * public constructor for TaskList that takes in an ArrayList to convert to tasklist.
     * @param memo Arraylist
     */
    public TaskList(ArrayList<Task> memo) {
        this.memo = memo;
    }

    /**
     * public class Method addTask to add task into TaskList.
     * @param t task to input.
     */
    public String addTask(Task t) {
        memo.add(t);
        String note = "Now you have " + memo.size() + " tasks in the list.";
        return "Got it, I've added this task:\n      " + t.toString()
                + "\n    " + note;
    }

    /**
     * class method saveList to convert Arraylist of Tasks into String representation of tasks.
     * @return Arraylist<String> containing tasks in String form.
     */
    public ArrayList<String> saveList() {
        ArrayList<String> result = new ArrayList<>();
        for (Task task : this.memo) {
            result.add(task.toString());
        }
        return result;
    }

    /**
     * class method enumerate to Enumerate the TaskList when LIST command is called.
     * @return List of tasks in String.
     */
    public String enumerate() {
        int count = 0;
        String str = "Here are the tasks in your list:";
        for (Task k : memo) {
            count ++;
            str += "\n    " + count + ". " + k.toString();
        }
        return str;
    }

    /**
     * class method deleteTask that delete tasks in TaskList based on input index.
     * @param index
     * @return String updated note.
     */
    public String deleteTask(int index) {
        String temp = this.memo.get(index - 1).toString();
        this.memo.remove(index - 1);
        String noteUpdated = "Now you have " + memo.size() + " tasks in the list.";
        return "Noted. I've deleted this task:\n      " + temp
                + "\n    " + noteUpdated;
    }

    /**
     * public class getter method to get Size of TaskList.
     * @return int size.
     */
    public int getSize() {
        return this.memo.size();
    }

    /**
     * public class getter method to get Task at particular index.
     * @param index
     * @return Task at input index.
     */
    public Task get(int index) {
        return this.memo.get(index - 1);
    }
}
