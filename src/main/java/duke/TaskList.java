package duke;

import duke.exceptions.DukeMissingIndexException;

import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> taskList = new ArrayList<>();;

    public static void add(Task task) {
        taskList.add(task);
    }

    public static void read() {
        if (taskList.size() == 0) {
            System.out.println("You have no task");
            return;
        }

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task curr = taskList.get(i);
            if (curr != null) {
                System.out.println(i + 1 + "." + curr.toString());
            }
        }
    }

    public static void mark(int index) throws DukeMissingIndexException {
        if (index >= taskList.size() || taskList.get(index) == null) {
            throw new DukeMissingIndexException();
        }
        taskList.get(index).setDone();
    }

    public static void unMark(int index) throws DukeMissingIndexException {
        if (index >= taskList.size() || taskList.get(index) == null) {
            throw new DukeMissingIndexException();
        }
        taskList.get(index).setNotDone();
    }

    public static void delete(int index) throws DukeMissingIndexException {
        if (index >= taskList.size() || taskList.get(index) == null) {
            throw new DukeMissingIndexException();
        }
        taskList.remove(index);
    }

    public static ArrayList<Task> getTaskList() {
        return taskList;
    }
}
