package task;

import printer.Printer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
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

    public List<String> toStorageRepresentation() {
        return this.tasks.stream()
                .map(Task::toStorageRepresentation)
                .collect(Collectors.toList());
    }
}
