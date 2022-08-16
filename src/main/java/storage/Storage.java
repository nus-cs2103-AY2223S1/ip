package storage;
import java.util.ArrayList;
import java.util.List;
import task.Task;
import printer.Printer;

public class Storage {
    private final List<Task> tasks;

    public Storage() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task newTask) {
        this.tasks.add(newTask);
        Printer.print(String.format("This task is successfully added:\n %s\n"
                + "Now you have %d task(s) in the list",
                newTask.toString(), this.tasks.size()));
    }

    public void markTaskWithIndex(int index) {
        Task selectedTask = this.tasks.get(index);
        selectedTask.markAsFinished();
        Printer.print(String.format("This task has been marked as done:\n %s",
                selectedTask));
    }

    public void unmarkTaskWithIndex(int index) {
        Task selectedTask = this.tasks.get(index);
        selectedTask.markAsNotFinished();
        Printer.print(String.format("This task has been marked as not done yet:\n %s",
                selectedTask));
    }

    public void removeTaskWithIndex(int index) {
        Task selectedTask = this.tasks.get(index);
        this.tasks.remove(index);
        Printer.print(String.format("Noted. I've removed this task:\n %s\n"
                + "Now you have %d task(s) in the list",
                selectedTask, this.tasks.size()));
    }

    public boolean checkIndex(int index) {
        return index >= 0 && index < this.tasks.size();
    }

    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "You have no tasks at the moment.";
        }

        String tasksString = "";
        for (int i = 0; i < tasks.size(); i++) {
            tasksString += String.format("%d. %s\n", i + 1, this.tasks.get(i));
        }
        return "Here are the tasks in your list\n" + tasksString;
    }

}
