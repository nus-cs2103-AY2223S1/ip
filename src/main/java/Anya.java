import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Anya {
    static final String breakLine = "\n---------------------------------------------------------------------";

    public static void main(String[] args) {
        // Initialising variables
        Scanner sc = new Scanner(System.in);
        String userInput;
        String command;
        TaskList tasks = new TaskList();
        String fileName = "data/Anya.txt";

        loadFile(tasks, fileName);
        // Greet
        System.out.println("Hello! Anya is happy to meet you.\nHow can Anya help?" + breakLine);

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
                        System.out.println("Wrong format. Format: <name> /by <dd/MM/yyyy> <HHmm>. " +
                                "Example: return book /by 01/01/2022 2030");
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
                System.out.println(e.getMessage() + breakLine);
            }

            userInput = sc.nextLine();
        }

        // Exit
        saveFile(tasks, fileName);
        System.out.println("Anya is sad to see you leave. Please be back soon." + breakLine);
    }

    // Commands
    // put in tasklist
    public static void addTask(TaskList tasks, Task task) {
        tasks.addTask(task);
        System.out.println("Anya added: " + task);
        System.out.println("Anya sees that you have " + tasks.getLength() + " task(s) in your list." + breakLine);
    }

    public static void list(TaskList tasks) {
        System.out.println("Anya is getting you your list...");
        for (int i = 0; i < tasks.getLength(); i++) {
            int num = i + 1;
            System.out.println(num + ". " + tasks.getTaskFromIndex(num).toString());
        }
        System.out.println(breakLine);
    }

    public static void mark(TaskList tasks, int index) {
        Task task = tasks.getTaskFromIndex(index);
        task.markDone();
        System.out.println("Anya has marked this task as done: \n  " + task.toString() + breakLine);
    }

    public static void unmark(TaskList tasks, int index) {
        Task task = tasks.getTaskFromIndex(index);
        task.markUndone();
        System.out.println("Anya has marked this task as uncompleted: \n  " + task.toString() + breakLine);
    }

    // put in tasklist
    public static void delete(TaskList tasks, int index) {
        Task removedTask = tasks.getTaskFromIndex(index);
        tasks.deleteTaskFromIndex(index);
        System.out.println("Anya has removed this task : \n" + removedTask.toString() + breakLine);
    }

    public static void saveFile(TaskList tasks, String fileName) {
        System.out.println("Anya is saving your data...");
        try {
            FileWriter saveTask = new FileWriter(fileName);
            for (int i = 0; i < tasks.getLength(); i++) {
                Task task = tasks.getTaskFromIndex(i + 1);
                saveTask.write(task.toSave() + "\n");
            }
            saveTask.close();
            System.out.println("Anya has successfully saved your data!" + breakLine);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loadFile(TaskList tasks, String fileName) {
        System.out.println("Anya is loading your saved file...");
        File dir = new File("data/");
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            File savedTask = new File(fileName);
            if (!savedTask.exists()) {
                System.out.println("Anya cannot find your saved file... Let Anya create one now!");
                savedTask.createNewFile();
            }
            Scanner sc = new Scanner(savedTask);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                readEntry(tasks, line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage() + breakLine);
        }
        System.out.println("Anya has finished loading your saved file!" + breakLine);
    }

    public static void readEntry(TaskList tasks, String line) {
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
