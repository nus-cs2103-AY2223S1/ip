package Qoobee;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private Storage storage;
    private List<Task> taskList;

    public TaskList(Storage storage) {
        this.storage = storage;
        this.taskList = storage.getList();
    }

    public int taskListSize() {
        return this.taskList.size();
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public void printTasks() {
        if (taskListSize() == 0) {
            System.out.println("You have no tasks dummy!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskListSize(); i++) {
                Task currentTask = taskList.get(i);
                System.out.println((i + 1) + "." + currentTask);
            }
        }
    }

    public void addTask(Task task) throws QoobeeException {
        this.taskList.add(task);
        System.out.println("Got it. I've added this task:\n" + task + "\n" +
                "Now you have " + taskListSize() + " tasks in the list.");
        storage.save(taskList);
    }

    public void removeTask(int index) throws QoobeeException {
        try {
            Task task = taskList.remove(index);
            System.out.println("Noted. I've removed this task:\n" + task + "\n" +
                    "Now you have " + taskListSize() + " tasks in the list.");
            storage.save(taskList);
        } catch (IndexOutOfBoundsException e){
            throw new QoobeeException("Please enter a right number!");
        }
    }

    public void unmark(Task task) throws QoobeeException {
        task.markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
        storage.save(taskList);
    }


    public void mark(Task task) throws QoobeeException {
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + task);
        storage.save(taskList);
    }

}
