package duke;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public int size() {
        return taskList.size();
    }

    public void add(Task task) {
        taskList.add(task);
        printOnAdd(task);
    }

    public void printOnAdd(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task.toString());
        System.out.println("Now you have " + taskList.size() + " task" + (taskList.size() == 1 ? " " : "s ") + "in the list");
    }

    public void remove(int i) {
        printOnDelete(taskList.remove(i));
    }

    public void printOnDelete(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + task.toString());
        System.out.println("Now you have " + taskList.size() + " task" + (taskList.size() == 1 ? " " : "s ") + "in the list");
    }
    public void mark(int index) {
        taskList.get(index).markAsDone();
    }

    public void unmark(int index) {
        taskList.get(index).markAsUndone();
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i).toString());
        }
    }


    public Task get(int i) {
        return taskList.get(i);
    }
}
