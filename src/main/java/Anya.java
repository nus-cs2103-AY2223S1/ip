import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Anya {
    private static String breakLine = "\n---------------------------------------------------------------------";

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
                command = userInput.split(" ")[0];
                if (command.equals("list")) {
                    list(tasks);
                } else if (command.equals("mark")) {
                    int index = Integer.parseInt(userInput.split(" ")[1]);
                    mark(tasks, index);
                } else if (command.equals("unmark")) {
                    int index = Integer.parseInt(userInput.split(" ")[1]);
                    unmark(tasks, index);
                } else if (command.equals("delete")) {
                    int index = Integer.parseInt(userInput.split(" ")[1]);
                    delete(tasks, index);
                } else if (command.equals("todo")) {
                    try {
                        String inputTask = userInput.split(" ", 2)[1];
                        Task task = new Todo(inputTask);
                        addTask(tasks, task);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new AnyaException("The description of a todo cannot be empty.");
                    }
                } else if (command.equals("deadline")) {
                    try {
                        String inputTask = userInput.split(" ", 2)[1];
                        String[] details = inputTask.split(" /by ");
                        String dateTimeStr = details[1];
                        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr,
                                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
                        Task task = new Deadline(details[0], dateTime);
                        addTask(tasks, task);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new AnyaException("The description/cut-off time of a deadline cannot be empty.");
                    } catch (DateTimeParseException e) {
                        this.ui.errorMessage("Invalid format.");
                        this.ui.deadlineFormatExample();
                    }
                } else if (command.equals("event")) {
                    try {
                        String inputTask = userInput.split(" ", 2)[1];
                        String[] details = inputTask.split(" /at ");
                        Task task = new Event(details[0], details[1]);
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

    // Commands
    // put in tasklist
    public void addTask(TaskList tasks, Task task) {
        tasks.addTask(task);
        this.ui.addTaskMessage(task, tasks.getLength());
    }

    public void list(TaskList tasks) {
        this.ui.getListMessage(tasks);
    }

    public void mark(TaskList tasks, int index) {
        Task task = tasks.getTaskFromIndex(index);
        task.markDone();
        this.ui.markTaskMessage(task);
    }

    public void unmark(TaskList tasks, int index) {
        Task task = tasks.getTaskFromIndex(index);
        task.markUndone();
        this.ui.unmarkTaskMessage(task);
    }

    // put in tasklist
    public void delete(TaskList tasks, int index) {
        Task removedTask = tasks.getTaskFromIndex(index);
        tasks.deleteTaskFromIndex(index);
        this.ui.deleteTaskMessage(removedTask, index);
    }
}
