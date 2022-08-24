import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<>();
    private FileWriter writer;

    public TaskList(File file) {
        try {
            this.writer = new FileWriter(file, true);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void add(Task task, boolean isNewTask) {
        this.tasks.add(task);
        if(isNewTask) {
            System.out.println("Got it. I've added this task:\n  " + task + "\nNow you have " + tasks.size() + " tasks in the list.");
            try {
                char taskType = task instanceof ToDo
                                  ? 'T'
                                  : task instanceof Deadline ? 'D' : 'E';
                char isTaskDone = task.getStatusIcon().equals("X") ? '1' : '0';
                String taskDescription = task.getDescription();
                if (taskType == 'T') {
                    writer.write(taskType + " | " + isTaskDone + " | " + taskDescription + "\n");
                } else if (taskType == 'D') {
                    writer.write(taskType + " | " + isTaskDone + " | " + taskDescription + " | " + ((Deadline) task).getBy() +"\n");
                } else {
                    writer.write(taskType + " | " + isTaskDone + " | " + taskDescription + " | " + ((Event) task).getTime() +"\n");
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public void markTaskAsDone(int index) {
        tasks.get(index).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(index));
    }

    public void markTaskAsUndone(int index) {
        tasks.get(index).markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + tasks.get(index));
    }

    public void delete(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);
        System.out.println("Noted. I've removed this task:\n  " + task + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    public void list() {
        Task[] x = new Task[tasks.size()];
        Task[] tasksArray = tasks.toArray(x);
        System.out.println("Here are the tasks in your list:");
        for(int i = 1; i <= tasksArray.length; i++) {
            Task task = tasksArray[i - 1];
            System.out.println(i + "." + task.toString());
        }
    }

    public void closeWriter() {
        try {
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
