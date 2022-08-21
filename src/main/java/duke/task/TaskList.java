package duke.task;

import java.util.ArrayList;
import duke.Ui;

public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        TaskList.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return TaskList.tasks;
    }

    public void printTasks() {
        Ui.separationLine();
        if (TaskList.tasks.isEmpty()) {
            System.out.println("You have no tasks! :D");
        } else {
            for (int i = 0; i < TaskList.tasks.size(); i++) {
                System.out.println((i + 1) + "." + TaskList.tasks.get(i));
            }
        }

        Ui.separationLine();
    }

    public void markTask(boolean isDone, int index) {
        TaskList.tasks.get(index).isDone = isDone;
        Ui.markLog(TaskList.tasks.get(index), isDone);
    }

    public void addTask(Task task) {
        TaskList.tasks.add(task);
        Ui.addTaskLog(task);
    }

    public void removeTask(int index) {
        Ui.removeTaskLog(TaskList.tasks.get(index));
        TaskList.tasks.remove(index);
    }

    public static int length() {
        return TaskList.tasks.size();
    }
}
