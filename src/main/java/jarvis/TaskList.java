package jarvis;

import jarvis.Storage;
import jarvis.task.Deadline;
import jarvis.task.Event;
import jarvis.task.Task;
import jarvis.task.Todo;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    ArrayList<Task> taskList = new ArrayList<>();
    // The first index that is empty
    //private int firstEmptyIndex = 0;
    private Storage storage;

    public TaskList(Storage storage) {
        this.storage = storage;
    }

    public TaskList() {
        super();
    }

    public void addTask(String input, Task.TaskType taskType, boolean isDone) throws IOException {
        Task task;
        try {
            task = taskType == Task.TaskType.ToDo
                    ? new Todo(input, isDone)
                    : taskType == Task.TaskType.Event
                    ? new Event(input, isDone)
                    : new Deadline(input, isDone);
        } catch (DateTimeParseException e) {
            return;
        }

        taskList.add(task);
        storage.saveAddedTask(task);

        String msg = "Got it. I've added this task:\n" + "  "
                + task
                + "\n" + "Now you have " + taskList.size() + " tasks in the list";
        System.out.println(msg);
        //++firstEmptyIndex;
    }

    public void appendLoadedTask(Task task) throws IOException {

        taskList.add(task);
        //++firstEmptyIndex;
    }

    public void markTaskAsDone(int taskNum) {
        if (taskNum >= taskList.size()) {
            System.out.println("There is no task with index " + (taskNum + 1));
            return;
        }
        taskList.get(taskNum).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.get(taskNum));
    }

    public void markTaskAsUnDone(int taskNum) {
        if (taskNum >= taskList.size()) {
            System.out.println("There is no task with index " + (taskNum + 1));
            return;
        }
        taskList.get(taskNum).markAsUnDone();
        System.out.println("I've marked this task as not done:");
        System.out.println(taskList.get(taskNum));
    }

    public void deleteTask(int index) {
        if (index >= taskList.size()) {
            System.out.println("There is no task with index " + (index + 1));
            return;
        }
        System.out.println("Noted. I've removed this task:\n" +
                taskList.get(index) + "\n" +
                "Now you have " + (taskList.size() - 1) + " tasks in the list");
        taskList.remove(index);
//        for (int i = index; i < firstEmptyIndex; i++) {
//            taskList[index] = taskList[index + 1];
//        }
        //--firstEmptyIndex;
    }

    public void printTasks() {
        if (taskList.size() == 0) {
            System.out.println("There's nothing in the list.");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }

    public Task getTask(int i) {
        return taskList.get(i);
    }

    public int getTaskCount() {
        return taskList.size();
    }

    public void find(String keyword) {
        Task[] searchResult = taskList.stream().filter(task -> task.match(keyword)).toArray(Task[]::new);
        String msg = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < searchResult.length; i++) {
            msg += (i + 1) + ". " + searchResult[i] + "\n";
        }
        System.out.println(msg);
    }
}
