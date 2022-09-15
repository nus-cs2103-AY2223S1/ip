package seedu.duke.list;

import seedu.duke.task.Task;

import java.util.ArrayList;

/**
 * ArrayList but it stores tasks.
 */
public class TaskList extends ArrayList<Task> {
    public TaskList(int initialCapacity) {
        super(initialCapacity);
    }

    public TaskList() {}

    /**
     * Transforms the TaskList into an ArrayList which stores strings. For saving in a text file.
     * @return the list as an ArrayList with strings
     */
    public ArrayList<String> toStringList() {
        ArrayList<String> stringList = new ArrayList<>();
        for (int i = 0; i < this.size(); i++) {
            stringList.add(this.get(i).toString());
        }
        return stringList;
    }

    /**
     * Transforms the list into a visually pleasing list for printing.
     * @return
     */
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.size(); i++) {
            int num = i + 1;
            str = str + num + ". " + this.get(i).toString() + "\n";
        }
        return str;
    }
}
