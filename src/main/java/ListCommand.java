package main.java;

public class ListCommand extends Command {
    private TaskList taskList;

    public ListCommand(TaskList taskList) {
        super();
        this.taskList = taskList;
    }

    public void run() {
        int order = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : taskList) {
            System.out.println(order++ + "." + task.toString());
        }
    }
}
