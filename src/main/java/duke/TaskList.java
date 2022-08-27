package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Deals with keeping track of Tasks added.
 */
public class TaskList {
    protected static ArrayList<Task> taskList = new ArrayList<Task>();
    private Ui ui;
    private Storage storage;

    /**
     * TaskList constructor.
     *
     * @param ui Deals with user interaction.
     * @param taskList ArrayList to store Tasks.
     * @param storage Deals with interaction with memory.
     */
    public TaskList(Ui ui, ArrayList<Task> taskList, Storage storage) {
        this.ui = ui;
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * Adds Task on Duke program and ensure Task is added to memory.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
        storage.addTaskToDisk(task.taskMemo() + System.lineSeparator());
        ui.printAddTask(task, taskList.size());
    }

    /**
     * Deletes Task on Duke program and ensure Task is removed from memory.
     *
     * @param taskNumber The row of Task to be deleted.
     * @param description The taskNumber inputted as String for checking proper inputs.
     * @throws DukeException if Task number inputted is empty or out of bounds.
     */
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

    /**
     * Creates ToDo object to be added and throws exception if inputs are wrong.
     *
     * @param description ToDo Task description.
     * @throws DukeException if Task description is left empty.
     */
    public void addTodo(String description) {
        if (description.equals("")) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        Task task = new ToDo(description);
        addTask(task);
    }

    /**
     * Creates Event Object to be added and throws exception if inputs are wrong.
     *
     * @param description Event Task description.
     * @param at Time Event occurs.
     * @throws DukeException if Task description inputted is empty.
     */
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

    /**
     * Creates Deadline Object to be added and throws exception if inputs are wrong.
     *
     * @param description Deadline Task description.
     * @param by Time Deadline is due.
     * @throws DukeException if Task description inputted is empty.
     */
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

    /**
     * Prints list of Tasks being tracked.
     */
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

    /**
     * Marks a Task as done.
     *
     * @param taskNumber Row of Task to be marked.
     * @param description The taskNumber inputted as String for checking proper inputs.
     * @throws DukeException if Task number inputted is empty or out of bounds.
     */
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

    /**
     * Marks a Task as incomplete.
     *
     * @param taskNumber Row of Task to be unmarked.
     * @param description The taskNumber inputted as String for checking proper inputs.
     * @throws DukeException if Task number inputted is empty or out of bounds.
     */
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

    /**
     * Terminates program.
     */
    public void exit() {
        Duke.terminate = true;
        ui.printExit();
    }


}
