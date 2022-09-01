package anya;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import anya.task.Deadline;
import anya.task.Event;
import anya.task.Task;
import anya.task.TaskList;
import anya.task.Todo;

public class Anya {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Anya(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.loadFile());
        } catch (IOException e) {
            this.tasks = new TaskList();
        }
    }

    /**
     * Takes in a user input and runs the relevant commands.
     *
     * @param userInput A string of command.
     * @return A response depending on the command entered.
     */
    String getResponse(String userInput) {
        String command = Parser.parseCommand(userInput);
        try {
            if (command.equals("bye")) {
                return exit();
            } else if (command.equals("list")) {
                return getList(tasks);
            } else if (command.equals("find")) {
                String keyword = Parser.parseKeyword(userInput);
                return find(tasks, keyword);
            } else if (command.equals("mark")) {
                int index = Parser.parseCommandIndex(userInput);
                return mark(tasks, index);
            } else if (command.equals("unmark")) {
                int index = Parser.parseCommandIndex(userInput);
                return unmark(tasks, index);
            } else if (command.equals("delete")) {
                int index = Parser.parseCommandIndex(userInput);
                return delete(tasks, index);
            } else if (command.equals("todo")) {
                String taskName = Parser.parseTaskName(userInput);
                Task task = new Todo(taskName);
                return addTask(tasks, task);
            } else if (command.equals("deadline")) {
                String taskName = Parser.parseTaskName(userInput);
                LocalDateTime dateTime = Parser.parseDateTime(userInput);
                Task task = new Deadline(taskName, dateTime);
                return addTask(tasks, task);
            } else if (command.equals("event")) {
                String taskName = Parser.parseTaskName(userInput);
                String eventDetails = Parser.parseEventDetails(userInput);
                Task task = new Event(taskName, eventDetails);
                return addTask(tasks, task);
            } else {
                throw new AnyaException("Anya doesn't understand this command.");
            }
        } catch (AnyaException e) {
            return this.ui.getErrorMessage(e.getMessage());
        } catch (DateTimeParseException e) {
            String message = this.ui.getErrorMessage("Invalid format.\n");
            message += this.ui.deadlineFormatExample();
            return message;
        }
    }

    /**
     * Returns a greeting message.
     *
     * @return A greeting message.
     */
    public String greet() {
        // great message
        String message = this.ui.getGreetMessage();
        return message;
    }

    /**
     * Returns the status of loading the saved data.
     *
     * @return A success message and a list of previously stored tasks; a failure message otherwise.
     */
    public String getLoadFileStatus() {
        if (this.tasks.getLength() == 0) {
            return this.ui.getLoadFileFailureMessage();
        } else {
            String message = this.ui.getLoadFileSuccessMessage() + "\n";
            message += getList(this.tasks);
            return message;
        }
    }

    /**
     * Saves the current TaskList into a database.
     *
     * @return A success message if file is successfully saved; a failure message otherwise.
     */
    public String exit() {
        StringBuilder message = new StringBuilder();
        try {
            this.ui.getSavingFileMessage();
            this.storage.saveFile(tasks);
            message.append(this.ui.getSaveFileSuccessMessage() + "\n");
        } catch (IOException e) {
            message.append(this.ui.getErrorMessage(e.getMessage() + " Sorry, Anya is unable to save data.\n"));
        }
        message.append(this.ui.getExitMessage());
        return message.toString();

        // Implement terminating of program
    }

    /**
     * Appends a Task to the end of the TaskList.
     *
     * @param tasks A collection of Tasks.
     * @param task The task to be added into the TaskList.
     * @return The task added and the current number of tasks.
     */
    public String addTask(TaskList tasks, Task task) {
        tasks.addTask(task);
        return this.ui.getAddTaskMessage(task, tasks.getLength());
    }

    /**
     *
     * Returns a string of tasks in the TaskList.
     *
     * @param tasks A collection of Tasks.
     * @return A list of current tasks
     */
    public String getList(TaskList tasks) {
        return this.ui.getListMessage(tasks);
    }

    /**
     * Marks the task at the specified position as complete.
     * The TaskList is One-Indexed.
     *
     * @param tasks A collection of Tasks.
     * @param index The index of the task in the TaskList to be marked.
     * @return The task that is marked.
     */
    public String mark(TaskList tasks, int index) throws AnyaException{
        try {
            Task task = tasks.getTaskFromIndex(index);
            task.markDone();
            return this.ui.getMarkTaskMessage(task);
        } catch (IndexOutOfBoundsException e) {
            throw new AnyaException("Invalid index. You only have " + this.tasks.getLength() + " tasks!");
        }
    }

    /**
     * Marks the task at the specified position as incomplete.
     * The TaskList is One-Indexed.
     *
     * @param tasks A collection of Tasks.
     * @param index The index of the task in the TaskList to be unmarked
     * @return The task that is unmarked.
     */
    public String unmark(TaskList tasks, int index) throws AnyaException{
        try {
            Task task = tasks.getTaskFromIndex(index);
            task.markUndone();
            return this.ui.getUnmarkTaskMessage(task);
        } catch (IndexOutOfBoundsException e) {
            throw new AnyaException("Invalid index. You only have " + this.tasks.getLength() + " tasks!");
        }
    }

    /**
     * Removes the task at the specified position in the TaskList.
     * The TaskList is One-Indexed.
     *
     * @param tasks A collection of Tasks.
     * @param index The index of the task in the TaskList to be removed
     * @return The task that is removed.
     */
    public String delete(TaskList tasks, int index) throws AnyaException{
        try {
            Task removedTask = tasks.getTaskFromIndex(index);
            tasks.deleteTaskFromIndex(index);
            return this.ui.getDeleteTaskMessage(removedTask);
        } catch (IndexOutOfBoundsException e) {
            throw new AnyaException("Invalid index. You only have " + this.tasks.getLength() + " tasks!");
        }
    }

    /**
     * Gets a filtered TaskList where each task contains the keyword.
     *
     * @param tasks A collection of Tasks.
     * @param keyword The word that the task must contain.
     * @return A filtered list of tasks that contains the keyword.
     */
    public String find(TaskList tasks, String keyword) {
        TaskList filteredTasks = tasks.getMatchingTasks(keyword);
        return this.ui.getFilteredTaskMessage(filteredTasks, keyword);
    }
}
