package duke;

import java.util.ArrayList;

public class Tag {
    private final String name;
    private final ArrayList<Task> list = new ArrayList<>();

    public Tag(String name) {
        this.name = name;
    }

    static String getTasksString(ArrayList<Task> list) {
        if (list.size() == 0) {
            return "You do not have any tasks currently";
        }

        StringBuilder ret = new StringBuilder();
        ret.append("Here are the tasks in your list: \n");
        for (int i = 1; i <= list.size(); i++) {
            Task task = list.get(i - 1);
            ret.append(i).append(". ").append(task).append("\n");
        }

        return ret.toString();
    }

    public String getTagName() {
        return this.name;
    }

    public String addTaskToTag(Task task) {
        list.add(task);
        return "Tag has been added to task";
    }

    public String showAllTaskUnderList() {
        return getTasksString(list);
    }

    @Override
    public String toString() {
        return name;
    }
}
