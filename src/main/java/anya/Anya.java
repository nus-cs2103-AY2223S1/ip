package anya;

import anya.task.Deadline;
import anya.task.Event;
import anya.task.Task;
import anya.task.TaskList;
import anya.task.Todo;

import java.util.Scanner;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Anya {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Anya(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.ui.loadingFileMessage();
            this.tasks = new TaskList(storage.loadFile());
        } catch (IOException e) {
            this.ui.errorMessage(e.getMessage() + " Creating a new database");
            this.tasks = new TaskList();
        } finally {
            this.ui.loadFileSuccessMessage();
            list(tasks);
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        String userInput;
        String command;

        // Greet
        ui.greetMessage();

        // Get user input
        userInput = this.ui.readLine();

        while (!userInput.equals("bye")) {
            try {
                command = Parser.parseCommand(userInput);
                if (command.equals("list")) {
                    list(tasks);
                } else if (command.equals("mark")) {
                    int index = Parser.parseCommandIndex(userInput);
                    mark(tasks, index);
                } else if (command.equals("unmark")) {
                    int index = Parser.parseCommandIndex(userInput);
                    unmark(tasks, index);
                } else if (command.equals("delete")) {
                    int index = Parser.parseCommandIndex(userInput);
                    delete(tasks, index);
                } else if (command.equals("todo")) {
                    try {
                        String taskName = Parser.parseTaskName(userInput);
                        Task task = new Todo(taskName);
                        addTask(tasks, task);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new AnyaException("The description of a todo cannot be empty.");
                    }
                } else if (command.equals("deadline")) {
                    try {
                        String taskName = Parser.parseTaskName(userInput);
                        LocalDateTime dateTime = Parser.parseDateTime(userInput);
                        Task task = new Deadline(taskName, dateTime);
                        addTask(tasks, task);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new AnyaException("The description/cut-off time of a deadline cannot be empty.");
                    } catch (DateTimeParseException e) {
                        this.ui.errorMessage("Invalid format.");
                        this.ui.deadlineFormatExample();
                    }
                } else if (command.equals("event")) {
                    try {
                        String taskName = Parser.parseTaskName(userInput);
                        String eventDetails = Parser.parseEventDetails(userInput);
                        Task task = new Event(taskName, eventDetails);
                        addTask(tasks, task);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new AnyaException("The description/time of an event cannot be empty.");
                    }
                } else {
                    throw new AnyaException("Anya doesn't understand this command.");
                }

            } catch (AnyaException e) {
                this.ui.errorMessage(e.getMessage());
            }

            userInput = this.ui.readLine();
        }

        // Exit
        try {
            this.ui.savingFileMessage();
            this.storage.saveFile(tasks);
            this.ui.saveFileSuccessMessage();
        } catch (IOException e){
            this.ui.errorMessage(e.getMessage() + " Sorry, Anya is unable to save data.");
        }
        this.ui.closeScanner();
        this.ui.exitMessage();
    }

    public static void main(String[] args) {
        new Anya("data/Anya.txt").run();
    }

    /**
     * Appends a Task to the end of the TaskList.
     *
     * @param tasks A collection of Tasks.
     * @param task The task to be added into the TaskList.
     */
    public void addTask(TaskList tasks, Task task) {
        tasks.addTask(task);
        this.ui.addTaskMessage(task, tasks.getLength());
    }

    /**
     * Prints out the tasks in the TaskList
     *
     * @param tasks A collection of Tasks.
     */
    public void list(TaskList tasks) {
        this.ui.getListMessage(tasks);
    }

    /**
     * Marks the task at the specified position as complete.
     * The TaskList is One-Indexed.
     *
     * @param tasks A collection of Tasks.
     * @param index The index of the task in the TaskList to be marked.
     */
    public void mark(TaskList tasks, int index) {
        Task task = tasks.getTaskFromIndex(index);
        task.markDone();
        this.ui.markTaskMessage(task);
    }

    /**
     * Marks the task at the specified position as incomplete.
     * The TaskList is One-Indexed.
     *
     * @param tasks A collection of Tasks.
     * @param index The index of the task in the TaskList to be unmarked
     */
    public void unmark(TaskList tasks, int index) {
        Task task = tasks.getTaskFromIndex(index);
        task.markUndone();
        this.ui.unmarkTaskMessage(task);
    }

    /**
     * Removes the task at the specified position in the TaskList.
     * The TaskList is One-Indexed.
     *
     * @param tasks A collection of Tasks.
     * @param index The index of the task in the TaskList to be removed
     */
    public void delete(TaskList tasks, int index) {
        Task removedTask = tasks.getTaskFromIndex(index);
        tasks.deleteTaskFromIndex(index);
        this.ui.deleteTaskMessage(removedTask);
    }
}
