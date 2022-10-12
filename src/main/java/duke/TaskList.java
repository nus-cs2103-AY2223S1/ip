package duke;

import java.util.ArrayList;

/**
 * Contains the task list.
 */
public class TaskList {
    private String list;
    private ArrayList<String> arrayList;

    public TaskList() {}

    public TaskList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
        this.list = getList();
    }

    /**
     * Sets the list to be current list.
     *
     * @param list A list that is of type String.
     */
    public void set(String list) {
        this.list = list;
    }

    /**
     * Returns a String representation of the command.
     *
     * @return String
     */
    public String get() {
        return this.list;
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
