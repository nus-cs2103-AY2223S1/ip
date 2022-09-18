package Cinnamon.Handler;
import Cinnamon.Tasks.Deadline;
import Cinnamon.Tasks.TaskList;
import Cinnamon.Tasks.Event;
import Cinnamon.Tasks.Todo;
import Cinnamon.Tasks.Task;
import java.util.ArrayList;

/**
 * Contains methods of the program
 */
public class Commands {
    private final TaskList taskList;
    private final Ui ui;

    /**
     * Constructor of command that initialised ui and tasklist
     */
    public Commands() {
        this.ui = new Ui();
        this.taskList = new TaskList();
    }

    /**
     * Prints out current list of tasks
     */
    public void printList() {
        ui.listTask(taskList.listTasks());
    }

    /**
     * Marks a task as done using the task index
     *
     * @param input read task index
     */
    public void markDone(String input) {
        ui.markDoneMes();
        int j = Integer.parseInt(input.substring(5)) - 1;
        Task task = taskList.get(j);
        task.markAsDone();
        System.out.println(task);
    }

    /**
     * Marks a task as not done using the task index
     *
     * @param input read task index
     */
    public void unmark(String input) {
        ui.unmarkedMes();
        int j = Integer.parseInt(input.substring(7)) - 1;
        Task task = taskList.get(j);
        task.markAsNotDone();
        System.out.println(task);
    }

    /**
     * Adds a task to list and print the task out
     *
     * @param input read name of task
     */
    public void todo(String input) {
        ui.addTask();
        Task todo = new Todo(input.substring(5));
        taskList.add(todo);
        System.out.println(todo);
        ui.printSummary(taskList.size());
    }

    /**
     * Adds a deadline task to list and print the task out
     *
     * @param input read name, time and date of task
     */
    public void deadline(String input) {
        ui.addTask();
        String[] parts = input.split(" ");
        String date = parts[parts.length - 2];
        String time = parts[parts.length - 1];
        Task dl = new Deadline(input.substring(9, input.indexOf("/") - 1), date, time);
        taskList.add(dl);
        System.out.println(dl);
        ui.printSummary(taskList.size());
    }

    /**
     * Adds an event task to list and print the task out
     *
     * @param input read name, time and date of task
     */
    public void event(String input) {
        ui.addTask();
        String[] parts = input.split(" ");
        String at = parts[parts.length - 3];
        String date = parts[parts.length - 2];
        String time = parts[parts.length - 1];
        Task event = new Event(input.substring(6, input.indexOf("/") - 1), at, date, time);
        taskList.add(event);
        System.out.println(event);
        ui.printSummary(taskList.size());
    }

    /**
     * Removes a task from list
     *
     * @param input read task index
     */
    public void delete(String input) {
        ui.deleteTask();
        int index = Integer.parseInt(input.substring(7)) - 1;
        Task task = taskList.get(index);
        System.out.println(task);
        taskList.remove(index);
        ui.printSummary(taskList.size());
    }

    /**
     * Searches for task that matches the date inputted
     *
     * @param input read date
     */
    public void searchDate(String input) {
        String[] parts = input.split(" ", 2);
        ArrayList<Task> matched = new ArrayList<>();
        for (Task t : taskList.listTasks()) {
            String str = t.toString();
            if (str.contains(parts[1])) {
                matched.add(t);
            }
        }
        if (matched.isEmpty()) {
            ui.notFound();
        } else {
            ui.printMatchedTasks(matched);
        }
    }

    /**
     * Searches for task that matches the name inputted
     *
     * @param input read name
     */
    public void searchName(String input) {
        String[] parts = input.split(" ", 2);
        ArrayList<Task> matched = new ArrayList<>();
        for (Task t : taskList.listTasks()) {
            String str = t.toString();
            if (str.contains(parts[1])) {
                matched.add(t);
            }
        }
        if (matched.isEmpty()) {
            ui.nameNotFound();
        } else {
            ui.printMatchedTasks(matched);
        }
    }

    public void tagTask(String input) {
        int index = Integer.parseInt(input.substring(4, 5)) - 1;
        Task task = taskList.get(index);
        System.out.println(task);
        task.tagged();
        String[] parts = input.split(" ", 3);
        task.setTag(parts[2]);
        System.out.println(task.getTag());
    }

    public void printTaskTag(String input) {
        // input format = print tag 1(index)
        int index = Integer.parseInt(input.substring(10)) - 1;
        Task task = taskList.get(index);
        if (task.isTagged()) {
            System.out.print("Your task is tagged as %d \n" + task.getTag());
            System.out.println(task);
        } else {
            System.out.println("The task is not tagged");
        }
    }

    /**
     * Searches the task list and prints tasks of matching tag
     *
     * @param input command by user
     */
    public void searchTag(String input) {
        String s = "";
        ArrayList<Task> matched = new ArrayList<>();
        for (Task t : taskList.listTasks()) {
            if (t.getTag().equals(input)) {
                matched.add(t);
            }
        }
        if (matched.isEmpty()) {
            System.out.println("No matching task of given tag");
        } else {
            ui.printMatchedTasks(matched);
        }
    }

    /**
     * Removes tag for the task
     *
     * @param input by user
     */
    public void removeTag(String input) {
        String[] parts = input.split(" ", 3);
        int index = Integer.parseInt(parts[2]) - 1;
        Task task = taskList.get(index);
        task.setTag("");
        ui.unTag();
        System.out.println(task);
    }
}
