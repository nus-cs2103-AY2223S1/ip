package duke.task;

import duke.DukeException;
import duke.ui.Ui;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(ArrayList<String> stringList) {
        for (String line : stringList) {
            parseLine(line);
        }
    }

    public TaskList() {
    }

    public ArrayList<Task> get() {
        return tasks;
    }

    private void parseLine(String line) {
        String[] parts = line.split(" \\| ");
        Task task = switch (parts[0]) {
            case "T" -> new Todo(parts[2]);
            case "D" -> new Deadline(parts[2], parts[3]);
            case "E" -> new Event(parts[2], parts[3]);
            default -> null;
        };

        switch (parts[1]) {
            case "0" -> task.markAsUndone();
            case "1" -> task.markAsDone();
        }
        tasks.add(task);
    }

    public void listTasks(Ui ui) {
        ui.showList(tasks);
    }

    public void markAsDone(int taskNumber, Ui ui) throws DukeException {
        try {
            tasks.get(taskNumber).markAsDone();
            ui.showMessage("Nice! I've marked this task as done:\n  " + tasks.get(taskNumber));
        } catch (Exception e) {
            throw new DukeException("Please give a valid task number");
        }
    }

    public void markAsUndone(int taskNumber, Ui ui) throws DukeException {
        try {
            tasks.get(taskNumber).markAsUndone();
            ui.showMessage("OK, I've marked this task as not done yet:\n  " + tasks.get(taskNumber));
        } catch (Exception e) {
            throw new DukeException("Please give a valid task number");
        }
    }

    private void printOnAdd(Task task, Ui ui) {
        ui.showMessage("Got it. I've added this task:\n  " + task + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    public void addTask(Task task, Ui ui) {
        tasks.add(task);
        printOnAdd(task, ui);
    }

    private void printOnDelete(int taskNum, Ui ui) {
        ui.showMessage("Noted. I've deleted this task:\n  " + tasks.get(taskNum) + "\nNow you have " + (tasks.size() - 1) + " tasks in the list.");
    }

    public void deleteTask(int taskNumber, Ui ui) {
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            printOnDelete(taskNumber, ui);
            tasks.remove(taskNumber);
        } else {
            throw new DukeException("Please give a valid task number");
        }
    }
}
