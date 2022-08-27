package Duke;
import java.util.ArrayList;

/**
 * Contains methods of the program
 */
public class Commands {
    private TaskList taskList;
    private Ui ui;

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
        System.out.println("Nice! I have marked this task as done:");
        int j = Integer.parseInt(input.substring(5)) - 1;
        Task task = taskList.get(j);
        task.markAsDone();
        System.out.println(task.toString());
    }

    /**
     * Mark a task as not done using the task index
     *
     * @param input read task index
     */
    public void unmark(String input) {
        System.out.println("This task is marked as not done:");
        int j = Integer.parseInt(input.substring(7)) - 1;
        Task task = taskList.get(j);
        task.markAsNotDone();
        System.out.println(task.toString());
    }

    /**
     * Add a task to list and print the task out
     *
     * @param input read name of task
     */
    public void todo(String input) {
        System.out.println("Got it, this task is added in your list:");
        Task todo = new Todo(input.substring(5));
        taskList.add(todo);
        System.out.println(todo.toString());
        ui.printSummary(taskList.size());
    }

    /**
     * Add a deadline task to list and print the task out
     *
     * @param input read name, time and date of task
     */
    public void deadline(String input) {
        System.out.println("Got it, this task is added in your list:");
        String[] parts = input.split(" ");
        String date = parts[parts.length-2];
        String time = parts[parts.length-1];
        Task dl = new Deadline(input.substring(9, input.indexOf("/") - 1), date, time);
        taskList.add(dl);
        System.out.println(dl.toString());
        ui.printSummary(taskList.size());
    }

    /**
     * Add an event task to list and print the task out
     *
     * @param input read name, time and date of task
     */
    public void event(String input) {
        System.out.println("Got it, this task is added in your list:");
        String[] parts = input.split(" ");
        String at = parts[parts.length-3];
        String date = parts[parts.length-2];
        String time = parts[parts.length-1];
        Task event = new Event(input.substring(6, input.indexOf("/") - 1), at, date, time);
        taskList.add(event);
        System.out.println(event.toString());
        ui.printSummary(taskList.size());
    }

    /**
     * Remove a task from list
     *
     * @param input read task index
     */
    public void delete(String input) {
        System.out.println("Noted. I've removed this task:");
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
    public void search(String input) {
        ArrayList<Task> matched = new ArrayList<>();
        for(Task t: taskList.listTasks()) {
            String str = t.toString();
            if(str.contains(input)) {
                matched.add(t);
            }
        }
        if (matched.isEmpty()) {
            ui.notFound();
        } else {
            ui.printMatchedTasks(matched);
        }
    }
}
