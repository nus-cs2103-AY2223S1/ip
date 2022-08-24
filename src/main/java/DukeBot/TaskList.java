package DukeBot;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

    public TaskList() {
        super();
    }

    public void addTask(Task newTask, boolean onStartUp) {
        this.add(newTask);
        if (!onStartUp) {
            System.out.println("    Got it. I've added this task:");
            System.out.print("      ");
            System.out.println(newTask);
            System.out.println(String.format("    Now you have %d tasks in the list.", Task.getTaskCount()));
        }
    }

    public void deleteTask(int taskToDelete) {
        Task deletedTask = this.remove(taskToDelete);
        Task.reduceTaskCount();
        System.out.println("    Noted. I've removed this task:");
        System.out.println(String.format("      %s", deletedTask));
        System.out.println(String.format("    Now you have %d tasks in the list.", Task.getTaskCount()));
    }
}
