package duke;

import java.util.ArrayList;

import static duke.Scan.dukePrintLn;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        dukePrintLn("added: " + task.getTaskName());
        this.taskList.add(task);
    }

    public void displayAllTask(){
        System.out.println("----");
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println(i + ". " + taskList.get(i-1).getTaskName());
        }
        System.out.println("-----");
    }
}
