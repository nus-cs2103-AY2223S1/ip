import exceptions.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public String getRemainingTasks() {
        return String.format("Now you have %d tasks in the list.", taskList.size());
    }

    public String addToDo(String taskName, String flag, String additionalValue) throws DukeException {
        Task task = new ToDo(taskName);
        task.checkCommandValidity(taskName, flag, additionalValue);
        taskList.add(task);
        return "Got it. I've added this task:\n\t" + task + "\n" + getRemainingTasks();
    }

    public String addEvent(String taskName, String flag, String additionalValue) throws DukeException {
        Task task = new Event(taskName, additionalValue);
        task.checkCommandValidity(taskName, flag, additionalValue);
        taskList.add(task);
        return "Got it. I've added this task:\n\t" + task + "\n" + getRemainingTasks();
    }

    public String addDeadline(String taskName, String flag, String additionalValue) throws DukeException {
        Task task = new Deadline(taskName, additionalValue);
        task.checkCommandValidity(taskName, flag, additionalValue);
        taskList.add(task);
        return "Got it. I've added this task:\n\t" + task + "\n" + getRemainingTasks();
    }

    public String markTask(String value, boolean isCompleted) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(value);
            Task task = taskList.get(taskNumber - 1);
            task.mark(isCompleted);
            String message = isCompleted
                    ? "Nice! I've marked this task as done:\n\t"
                    : "Ok, I've marked this task as not done yet:\n\t";
            return message + task;
        } catch (NumberFormatException e) {
            throw new DukeException("Please input a number.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(String.format("Please input a valid number! There are %d tasks remaining.", taskList.size()));
        }
    }

    public String deleteTask(String value) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(value);
            Task task = this.taskList.get(taskNumber - 1);
            this.taskList.remove(taskNumber - 1);
            return "Noted. I've removed this task:\n\t" + task + "\n" + getRemainingTasks();
        } catch(NumberFormatException e) {
            throw new DukeException("Please input a number.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(String.format("Please input a valid number! There are %d tasks remaining.", taskList.size()));
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        int counter = 1;
        for (Task task : this.taskList) {
            String numberedTask = counter + "." + task + "\n";
            result.append(numberedTask);
            counter++;
        }
        return result.toString();
    }
}
