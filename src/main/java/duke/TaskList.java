/**
 * Project Duke CS2103
 * Done by Hong Jin.
 */
package duke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import duke.task.Task;

/**
 * Class TaskList to store the list of Tasks.
 */
public class TaskList {
    private ArrayList<Task> memo;
    private int size;

    /**
     * public constructor for TaskList that takes in an ArrayList to convert to tasklist.
     * @param memo Arraylist
     */
    public TaskList(ArrayList<Task> memo) {
        int temp = memo.size();
        this.memo = memo;
        this.size = temp;
    }

    /**
     * public class Method addTask to add task into TaskList.
     * @param t task to input.
     */
    public String addTask(Task t) {
        memo.add(t);
        this.size++;
        String note = "Now you have " + this.size + " tasks in the list.";
        return "Got it, I've added this task:\n" + t.toString()
                + "\n" + note;
    }

    /**
     * class method saveList to convert Arraylist of Tasks into String representation of tasks.
     * @return Arraylist containing tasks in String form.
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
            count++;
            str += "\n" + count + ". " + k.toString();
        }
        return str;
    }

    /**
     * class method deleteTask that delete tasks in TaskList based on input index.
     * @param index
     * @return String updated note.
     */
    public String deleteTask(int index) {
        assert index >= 0 : "index should be >= 0";
        Task removedTask = this.memo.remove(index);
        this.size--;
        return removedTask.toString();
    }

    /**
     * public class getter method to get Size of TaskList.
     * @return int size.
     */
    public int getSize() {
        assert this.size >= 0 : "size should be not negative";
        return this.size;
    }

    /**
     * public class getter method to get Task at particular index.
     * @param index
     * @return Task at input index.
     */
    public Task get(int index) {
        assert index >= 0 : "index should be not negative";
        return this.memo.get(index - 1);
    }

    /**
     * public method findTask to search for description of task that match keywords in the TaskList.
     * @param keywords
     * @return list of tasks that match keywords.
     */
    public String findTask(String... keywords) {
        assert keywords.length > 0 : "keywords should not be blank";

        List<Task> result = this.memo
                .stream()
                .filter(task -> Arrays.stream(keywords)
                        .allMatch(word -> task.getTaskName().contains(word))) //only store task names that has keywords.
                .collect(Collectors.toList());

        //break code if nothing is found to be the same.
        if (result.size() == 0) {
            return "There are no matching tasks in your list!";
        }

        int count = 0;
        String output = "Here are the matching tasks in your list:";

        for (Task t : result) {
            count++;
            output += "\n    " + count + ". " + t.toString();
        }

        return output;
    }
}
