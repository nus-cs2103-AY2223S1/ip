package uwu.task;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> userToDoList;

    public TaskList() {
        this.userToDoList = new ArrayList<Task>();
    }

    public int size() {
        return userToDoList.size();
    }

    public Task get(int i) {
        return userToDoList.get(i);
    }

    public void add(Task newTask) {
        userToDoList.add(newTask);
    }

    public Task remove(int i) {
        return userToDoList.remove(i);
    }

    public String taskListToString() {
        int count = userToDoList.size();

        if (count == 0) {
            return "\n\n\tyou currently have no tasks, feed me <:";
        }

        String userToDoStr = "";

        for (int i = 0; i<count; i++) {
            String listItem = "\t" + String.valueOf(i + 1) + ".\t" + userToDoList.get(i).toString();

            userToDoStr = userToDoStr + "\n" + listItem;
        }

        return userToDoStr;
    }

    public String taskListToStorageString() {
        int count = userToDoList.size();
        String taskListStorage = "";

        for (int i = 0; i<count; i++) {
            String taskItem = userToDoList.get(i).toStorageString();

            if (i == 0) {
                taskListStorage = taskItem;
            } else {
                taskListStorage = taskListStorage + "\n" + taskItem;
            }
        }

        return taskListStorage;
    }
}
