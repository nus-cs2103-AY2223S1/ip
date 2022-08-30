package duke;

import duke.task.Task;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;
    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    
    protected ArrayList<Task> getTaskList() {
        return tasks;
    }

    protected  int getSize() {
        return tasks.size();
    }

    protected Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }
    
    protected void addTask(Task task) {
        this.tasks.add(task);
    }

    protected Task deleteTask(int taskIndex) { 
        return tasks.remove(taskIndex);
    }
    
    protected ArrayList<Task> find(String input) {
        ArrayList<Task> result = new ArrayList<>();
        
        for (Task task : tasks) {
            if (task.getDescription().contains(input)) {
                result.add(0, task);
            }
        }
        return result;
    }
}
