package duke.task;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.exception.InvalidTaskIndexException;

public class TaskList {
    private final ArrayList<Task> tasks;
    public enum TaskType {
        DEADLINE, EVENT, TODO
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task t) {
        this.tasks.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
    }

    public void addTask(TaskType taskType, String desc) {
        Task newTask;
        switch (taskType) {
        case TODO:
            newTask = new Todo(desc);
            break;
        case DEADLINE: {
            String[] descSplit = desc.split(" /by ", 2);
            if (descSplit.length > 1) {
                newTask = new Deadline(descSplit[0], descSplit[1]);
            } else {
                throw new DukeException("Date/time not specified for deadline");
            }
            break;
        }
        case EVENT: {
            String[] descSplit = desc.split(" /at ", 2);
            if (descSplit.length > 1) {
                newTask = new Event(descSplit[0], descSplit[1]);
            } else {
                throw new DukeException("Date/time not specified for event");
            }
            break;
        }
        default:
            throw new DukeException("Unrecognized task type: " + taskType);
        }
        this.addTask(newTask);
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println(i + 1 + "." + this.tasks.get(i));
        }
    }

    public void markTaskAsDone(int taskIndex) {
        if (taskIndex < 1 || taskIndex > this.tasks.size()) {
            throw new InvalidTaskIndexException(taskIndex);
        }
        this.tasks.get(taskIndex - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + this.tasks.get(taskIndex - 1));
    }

    public void markTaskAsNotDone(int taskIndex) {
        if (taskIndex < 1 || taskIndex > this.tasks.size()) {
            throw new InvalidTaskIndexException(taskIndex);
        }
        this.tasks.get(taskIndex - 1).markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + this.tasks.get(taskIndex - 1));
    }
}
