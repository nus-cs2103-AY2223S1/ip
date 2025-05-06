package duke;

import java.util.ArrayList;

/**
 * Contains the task list.
 */
public class TaskList {
    private ArrayList<String> arrayList;

    public TaskList() {}

    /**
     * Creates an object of TaskList.
     *
     * @param arrayList
     */
    public TaskList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    /**
     * Returns a String representation of the command.
     *
     * @return String
     */
    public String get() {
        return getList();
    }

    /**
     * Returns a String representation of the command list.
     *
     * @return String
     */
    public String getList() {
        String list = "";
        for (int k = 1; k < arrayList.size() + 1; k++) {
            list += k + "." + arrayList.get(k - 1) + "\n";
        }
        return list;
    }
}
