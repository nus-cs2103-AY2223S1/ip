package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> arrayList;
    private final Storage storage;

//duke feeds its own storage into tasklist
    protected TaskList() { //storage gives it the finished arraylist upon setup
        this.storage = new Storage();
        this.arrayList = this.storage.ReadFileAtStart();
    }

    private void SaveChangesToFile() throws IOException {
        this.storage.SaveData(this.arrayList);
    }

    protected void ListTasks() {
        System.out.println("Here are the tasks in your current list:");

        if (arrayList.size() == 0) {
            System.out.println("  Wow! You have no tasks to do currently!!");
        }

        for (int i = 0; i < arrayList.size(); i++) {
            int j = i + 1;
            System.out.println(j + "." + arrayList.get(i));
        }
    }

    protected void MarkTaskDoneAt(int i) throws IOException {
        this.arrayList.get(i).markDone();
        this.SaveChangesToFile();

        System.out.println("Nice! You actually did something:");
        System.out.println(" " + this.arrayList.get(i));
    }

    protected void MarkTaskNotDoneAt(int i) throws IOException {
        this.arrayList.get(i).markNotDone();
        this.SaveChangesToFile();

        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(" " + this.arrayList.get(i));
    }

    protected void DeleteTaskAt(int i) throws IOException {
        this.arrayList.get(i).markNotDone();
        Task deletedTask = arrayList.remove(i);
        this.SaveChangesToFile();

        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + deletedTask);
        System.out.println("Now you have " + arrayList.size() + " task(s) in the list.");
    }

    protected void AddToDo(String taskname) throws IOException {
        Task todo = new Todo(taskname.trim());
        arrayList.add(todo);
        this.SaveChangesToFile();

        System.out.println("Got it. I've added this task:");
        System.out.println(" " + todo);
        System.out.println("Now you have " + arrayList.size() + " task(s) in the list.");
    }

    protected void AddDeadline(String taskname, LocalDate d) throws IOException {
        Task deadline = new Deadline(taskname.trim(), d);
        arrayList.add(deadline);
        this.SaveChangesToFile();

        System.out.println("Got it. I've added this task:");
        System.out.println(" " + deadline);
        System.out.println("Now you have " + arrayList.size() + " task(s) in the list.");
    }

    protected void AddEvent(String taskname, LocalDate d) throws IOException {
        Task event = new Event(taskname.trim(), d);
        arrayList.add(event);
        this.SaveChangesToFile();

        System.out.println("Got it. I've added this task:");
        System.out.println(" " + event);
        System.out.println("Now you have " + arrayList.size() + " task(s) in the list.");
    }

}
