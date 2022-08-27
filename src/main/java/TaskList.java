import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> ls;
    private Storage storage;

    public TaskList(ArrayList<Task> tasks) {
        this.ls =  tasks;
    }

    public TaskList() {
        this.ls = new ArrayList<>();
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public void showTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < ls.size(); i++) {
            System.out.println(i + 1 + "." + " " + ls.get(i).toString());
        }
    }

    public void add(Task task) {
        System.out.println("Got it. I've added this task:");
        ls.add(task);
        System.out.println(task.toString());
        String file2 = "duke.txt";
        try {
            storage.writeToFile(file2, task.toString() + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        System.out.println("Now you have" + " " + ls.size()+ " " + "tasks in the list.");
    }

    public void remove(int task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(ls.get(task).toString());
        ls.remove(task);
        String file2 =  "duke.txt";
        try {
            storage.deleteFromFile(file2, ls);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        System.out.println("Now you have" + " " + ls.size() + " " + "tasks in the list.");
    }

    public void setTaskStatus(int index, boolean status) {
        ls.get(index).setStatus(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(ls.get(index).toString());
    }



}
