package Duke;

import Duke.DukeExceptions.BadTaskOperationException;
import Duke.DukeTasks.Task;

import java.time.LocalDate;
import java.util.ArrayList;

public class TasksManager {
    private String line = "_______________________________________";

    private Storage storage;
    private static ArrayList<Task> tasks = new ArrayList<>();

    public TasksManager() {
        this.storage = new Storage();
        this.storage.readfile(this);
    }

    public boolean addTask(Task task) {
        //add to the tasks
        tasks.add(task);
        //write to file
        storage.addTask(task);
        //print to console
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + numTasks() + " tasks in your list");
        System.out.println(line);

        return true;
    }

    public void addTaskNoPrint(Task task) {
        tasks.add(task);
    }

    public void showList() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int counter = i + 1;
            System.out.println(counter + ". " + tasks.get(i));
        }
        System.out.println(line);
    }

    public void markTaskAsDone(int n) {
        Task doneTask = this.tasks.get(n - 1);
        doneTask.markAsDone();

        // rewrite file entirely
        storage.rewriteFile(this.tasks);
        //print to the console
        System.out.println(line);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(doneTask);
        System.out.println(line);
    }

    public void deleteTask(int n) {
        Task deleted = this.tasks.remove(n - 1);
        //rewrite file entirely
        storage.rewriteFile(this.tasks);
        //print to console
        System.out.println(line);
        System.out.println("The following task has been deleted:");
        System.out.println(deleted);
        System.out.println(line);
    }

    public int numTasks() {
        return tasks.size();
    }

    public void closePW() {
        storage.end();
    }

    public void showDate(LocalDate localDate) {
        System.out.println(line);
        System.out.println("These tasks are due on " + localDate);
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).compareDate(localDate)) {
                System.out.println(tasks.get(i));
            }
        }
        System.out.println(line);
    }
}

