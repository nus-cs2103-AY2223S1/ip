package Duke.Handler;
import Duke.Tasks.Deadline;
import Duke.Tasks.TaskList;
import Duke.Tasks.Event;
import Duke.Tasks.Todo;
import Duke.Tasks.Task;
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
     * Mark a task as done using the task index
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
     * Mark a task as not done using the task index
     *
     * @param input read task index
     */
    public void unmark (String input) {
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
        String date = parts[parts.length-2];
        String time = parts[parts.length-1];
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
        String at = parts[parts.length-3];
        String date = parts[parts.length-2];
        String time = parts[parts.length-1];
        Task event = new Event(input.substring(6, input.indexOf("/") - 1), at, date, time);
        taskList.add(event);
        System.out.println(event);
        ui.printSummary(taskList.size());
    }

    /**
     * Remove a task from list
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
     * Search for task that matches the date inputted
     *
     * @param input read date
     */
    public void searchDate(String input) {
        String[] parts = input.split(" ", 2);
        ArrayList<Task> matched = new ArrayList<>();
        for(Task t: taskList.listTasks()) {
            String str = t.toString();
            if(str.contains(parts[1])) {
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
     * Search for task that matches the name inputted
     *
     * @param input read name
     */
    public void searchName(String input) {
        String[] parts = input.split(" ", 2);
        ArrayList<Task> matched = new ArrayList<>();
        for(Task t: taskList.listTasks()) {
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
}
