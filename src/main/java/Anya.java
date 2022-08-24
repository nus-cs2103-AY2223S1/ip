import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Anya {
    private static String breakLine = "\n---------------------------------------------------------------------";

    private TaskList tasks;
    private Ui ui;

    public Anya() {
        this.ui = new Ui();
        this.tasks = new TaskList();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        String userInput;
        String command;

        String fileName = "data/Anya.txt";
        loadFile(tasks, fileName);

        // Greet
        ui.greetMessage();

        // Get user input
        userInput = sc.nextLine();

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

            userInput = sc.nextLine();
        }

        // Exit
        saveFile(tasks, fileName);
        this.ui.exitMessage();
    }

    public static void main(String[] args) {
        new Anya().run();
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

    public void saveFile(TaskList tasks, String fileName) {
        this.ui.savingFileMessage();
        try {
            FileWriter saveTask = new FileWriter(fileName);
            for (int i = 0; i < tasks.getLength(); i++) {
                Task task = tasks.getTaskFromIndex(i + 1);
                saveTask.write(task.toSave() + "\n");
            }
            saveTask.close();
            this.ui.saveFileSuccessMessage();
        } catch (IOException e) {
            this.ui.errorMessage(e.getMessage());
        }
    }

    public void loadFile(TaskList tasks, String fileName) {
        this.ui.loadingFileMessage();
        File dir = new File("data/");
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            File savedTask = new File(fileName);
            if (!savedTask.exists()) {
                savedTask.createNewFile();
            }
            Scanner sc = new Scanner(savedTask);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                readEntry(tasks, line);
            }
        } catch (IOException e) {
            this.ui.errorMessage(e.getMessage());
        }
        this.ui.loadFileSuccessMessage();
    }

    public void readEntry(TaskList tasks, String line) {
        String[] arrStr = line.split(" \\| ", 3);
        String taskType = arrStr[0];
        boolean isTaskDone = arrStr[1].equals("1");
        if (taskType.equals("T")) {
            String taskName = arrStr[2];
            addTask(tasks, new Todo(taskName));
        } else if (taskType.equals("D")) {
            String taskName = arrStr[2].split(" \\| ")[0];
            String dateTimeStr = arrStr[2].split(" \\| ")[1];
            addTask(tasks, new Deadline(taskName,
                    LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))));
        } else {
            String taskName = arrStr[2].split(" \\| ")[0];
            String time = arrStr[2].split(" \\| ")[1];
            addTask(tasks, new Event(taskName, time));
        }
        if (isTaskDone) {
            mark(tasks, tasks.getLength());
        }
    }
}
