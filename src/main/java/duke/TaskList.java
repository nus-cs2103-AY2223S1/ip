package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    protected static ArrayList<Task> taskList = new ArrayList<Task>();
    private Ui ui;
    private Storage storage;

    public TaskList(Ui ui, ArrayList<Task> taskList, Storage storage) {
        this.ui = ui;
        this.storage = storage;
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
        storage.addTaskToDisk(task.taskMemo() + System.lineSeparator());
        ui.printAddTask(task, taskList.size());
    }

    public void deleteTask(int taskNumber, String description) {
        if (description.equals("")) {
            throw new DukeException("OOPS!!! The task number for delete cannot be empty.");
        }
        if (taskNumber > taskList.size()) {
            throw new DukeException("OOPS!!! Please enter a valid task number");
        }
        storage.deleteTaskFromDisk(taskNumber);
        Task removedTask = taskList.remove(taskNumber - 1);
        ui.printDeleteTask(removedTask, taskList.size());
    }

    public void addTodo(String description) {
        if (description.equals("")) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        Task task = new ToDo(description);
        addTask(task);
    }

    public void addEvent(String description, String at) {
        if (description.equals("")) {
            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
        }
        try {
            int indexOfDate = at.indexOf(" ") + 1;
            String date = at.substring(indexOfDate);
            LocalDate d1 = LocalDate.parse(date);
            Task task = new Event(description, d1);
            addTask(task);
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            ui.printWithLineFormat("     OOPS!!! Please enter a valid date format (/at yyyy-mm-dd)");
        }
    }

    public void addDeadline(String description, String by) {
        if (description.equals("")) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
        try {
            int indexOfDate = by.indexOf(" ") + 1;
            String date = by.substring(indexOfDate);
            LocalDate d1 = LocalDate.parse(date);
            Task task = new Deadline(description, d1);
            addTask(task);
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            ui.printWithLineFormat("     OOPS!!! Please enter a valid date format (/by yyyy-mm-dd)");
        }
    }

    public void displayList() {
        String message = "";
        for (int i = 1; i <= taskList.size(); i++) {
            Task currentTask = taskList.get(i - 1);
            String taskDescription = currentTask.toString();
            message += ("     "
                    + String.valueOf(i)
                    + "."
                    + taskDescription);
            if (i != taskList.size()) {
                message += "\n";
            }
        }
        ui.printDisplayList(message);
    }

    public void markTask(int taskNumber, String description) {
        if (description.equals("")) {
            throw new DukeException("OOPS!!! The task number for mark cannot be empty.");
        }
        if (taskNumber > taskList.size()) {
            throw new DukeException("OOPS!!! Please enter a valid task number");
        }
        Task currentTask = taskList.get(taskNumber - 1);
        currentTask.setTaskStatus(true);
        storage.setTaskStatusOnDisk(taskNumber, true);
        String taskDescription = currentTask.toString();
        ui.printMarkTask(taskDescription);
    }

    public void unmarkTask(int taskNumber, String description) {
        if (description.equals("")) {
            throw new DukeException("OOPS!!! The task number for unmark cannot be empty.");
        }
        if (taskNumber > taskList.size()) {
            throw new DukeException("OOPS!!! Please enter a valid task number");
        }
        Task currentTask = taskList.get(taskNumber - 1);
        currentTask.setTaskStatus(false);
        storage.setTaskStatusOnDisk(taskNumber, false);
        String taskDescription = currentTask.toString();
        ui.printUnmarkTask(taskDescription);
    }

    public void exit() {
        Duke.terminate = true;
        ui.printExit();
    }


}
