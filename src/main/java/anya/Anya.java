package anya;

import java.io.IOException;
import java.time.LocalDate;
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
            this.tasks = new TaskList(storage.loadCurrentTasksFromFile(),
                    storage.loadDeletedTasksFromFile(), storage.loadDateCreatedFromFile());
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
        try {
            String command = Parser.parseCommand(userInput);
            switch (command) {
            case "BYE":
                return exit();
            case "NEW":
                return newList();
            case "LIST":
                return getList(tasks);
            case "FIND":
                String keyword = Parser.parseKeyword(userInput);
                return find(tasks, keyword);
            case "MARK":
                int markIndex = Parser.parseCommandIndex(userInput);
                return mark(tasks, markIndex);
            case "UNMARK":
                int unmarkIndex = Parser.parseCommandIndex(userInput);
                return unmark(tasks, unmarkIndex);
            case "DELETE":
                int deleteIndex = Parser.parseCommandIndex(userInput);
                return delete(tasks, deleteIndex);
            case "STATISTICS":
                return getStatistics(tasks);
            case "TODO":
                String todoTaskName = Parser.parseTaskName(userInput);
                Task todoTask = new Todo(todoTaskName);
                return addTask(tasks, todoTask);
            case "DEADLINE":
                String deadlineTaskName = Parser.parseTaskName(userInput);
                LocalDateTime dateTime = Parser.parseDateTime(userInput);
                Task deadlineTask = new Deadline(deadlineTaskName, dateTime);
                return addTask(tasks, deadlineTask);
            case "EVENT":
                String eventTaskName = Parser.parseTaskName(userInput);
                String eventDetails = Parser.parseEventDetails(userInput);
                Task eventTask = new Event(eventTaskName, eventDetails);
                return addTask(tasks, eventTask);
            default:
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
        String message = this.ui.getGreetMessage();
        return message;
    }

    /**
     * Returns the status of loading the saved data.
     *
     * @return A success message and a list of previously stored tasks; a failure message otherwise.
     */
    public String getLoadFileStatus() {
        boolean isTaskListEmpty = this.tasks.getLength() == 0;
        boolean isTaskListCreatedToday = this.tasks.getDateCreated().equals(LocalDate.now().toString());
        if (isTaskListEmpty && isTaskListCreatedToday) {
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
            message.append(this.ui.getSaveFileSuccessMessage()).append("\n");
        } catch (IOException e) {
            message.append(this.ui.getErrorMessage(e.getMessage() + " Sorry, Anya is unable to save data.\n"));
        }
        message.append(this.ui.getExitMessage());
        return message.toString();
    }

    /**
     * Deletes the previous TaskList and creates a new TaskList with today's date.
     *
     * @return A success message with the date that the TaskList was created.
     */
    public String newList() {
        this.tasks = new TaskList();
        return this.ui.getNewListMessage(this.tasks.getDateCreated());
    }

    /**
     * Gets the total number of tasks that have been completed since the TaskList was created.
     * The completed tasks include those that have been deleted.
     * @param tasks A collection of Tasks
     * @return The total number of completed tasks
     */
    public String getStatistics(TaskList tasks) {
        int numCompletedTasks = tasks.getNumOfAllCompletedTasks();
        String dateCompleted = tasks.getDateCreated();
        return this.ui.getStatisticsMessage(numCompletedTasks, dateCompleted);
    }

    /**
     * Appends a Task to the end of the TaskList.
     *
     * @param tasks A collection of Tasks.
     * @param task The task to be added into the TaskList.
     * @return The task added and the current number of tasks.
     */
    public String addTask(TaskList tasks, Task task) {
        // Always succeeds; has side effect of checking the size of TaskList
        int initLen = 0;
        assert (initLen = tasks.getLength()) >= 0;

        tasks.addTask(task);

        // Ensure the current size of TaskList is 1 more than initial size
        assert tasks.getLength() == initLen + 1 : "Size of TaskList should be 1 more than it's initial size";

        return this.ui.getAddTaskMessage(task, tasks.getLength());
    }

    /**
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
    public String mark(TaskList tasks, int index) throws AnyaException {
        try {
            Task task = tasks.getTaskFromIndex(index);
            // Ensure that task from TaskList is not null
            assert task != null : "Task cannot be null";

            task.markDone();
            // Ensure that task is marked as done
            assert task.getStatusIcon().equals("X") : "Task should be marked as done";

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
    public String unmark(TaskList tasks, int index) throws AnyaException {
        try {
            Task task = tasks.getTaskFromIndex(index);

            // Ensure that task from TaskList is not null
            assert task != null : "task cannot be null";

            task.markUndone();
            // Ensure that task is marked as undone
            assert task.getStatusIcon().equals(" ") : "Task should be marked as undone";

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
    public String delete(TaskList tasks, int index) throws AnyaException {
        try {
            // Always succeeds; has side effect of checking the size of TaskList
            int initLen = 0;
            assert (initLen = tasks.getLength()) >= 0;

            Task removedTask = tasks.getTaskFromIndex(index);
            tasks.deleteTaskFromIndex(index);
            // Ensure the current size of TaskList is 1 less than initial size
            assert tasks.getLength() == initLen - 1 : "Size of TaskList should be 1 less than it's initial size";

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
