package main.java;

public class UnmarkCommand extends Command {
    private TaskList taskList;
    private int query;

    public UnmarkCommand(TaskList taskList, int query) throws IllegalTaskException {
        this.taskList = taskList;
        if (this.taskList.exists(query)) {
            this.query = query;
        } else {
            throw new IllegalTaskException("Task does not exist.");
        }
    }

    public void run() {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskList.unmarkTask(query).toString());
    }
}