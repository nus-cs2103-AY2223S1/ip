import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Duke {
    static List<Task> tasks = new ArrayList<>();

    static final String FILE_PATH = "data/duke.txt";

    private static void readDataFile() {
        tasks = new ArrayList<>();

        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                System.out.printf("File %s not found\n\n", FILE_PATH);
                return;
            }

            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String data = s.nextLine();
                String[] taskInfo = data.split(" \\| ");

                Task task;
                switch (taskInfo[0]) {
                    case "T":
                        task = new Todo(taskInfo[2]);
                        break;
                    case "E":
                        LocalDate date = LocalDate.parse(taskInfo[3]);
                        task = new Event(taskInfo[2], date);
                        break;
                    case "D":
                        date = LocalDate.parse(taskInfo[3]);
                        task = new Deadline(taskInfo[2], date);
                        break;
                    default:
                        throw new DukeException("Corrupt file");
                }
                if (taskInfo[1] == "1") {
                    task.mark();
                }
                tasks.add(task);
            }
            s.close();

            System.out.printf("Loaded tasks from %s\n\n", FILE_PATH);
        } catch (Exception e) {
            System.out.println("Error: Could not read from file");
            System.out.println(e);
        }
    }

    private static void syncTasksToFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(FILE_PATH);
            ArrayList<String> taskStrings = new ArrayList<>();
            for (Task task : tasks) {
                String taskString = task.toFileString();
                taskStrings.add(taskString);
            }

            String fileString = String.join("\n", taskStrings);
            writer.write(fileString);
            writer.close();
        } catch (Exception e) {
            System.out.println("Error: could not write to file");
            System.out.println(e);
        }
    }

    private static void markTask(String input, boolean done) throws DukeException {
        String[] splitInput = input.split(" ");
        if (splitInput.length < 2) {
            throw new DukeException("Please specify the number of the task");
        }
        try {
            int taskIdx = Integer.parseInt(splitInput[1]);
            Task task = tasks.get(taskIdx - 1);
            if (done) {
                task.mark();
            } else {
                task.unmark();
            }
            String successMessage = done
                    ? "Nice! I've marked this task as done:\n  %s\n"
                    : "OK, I've marked this task as not done yet:\n  %s\n";
            System.out.printf(successMessage, task.toString());
            syncTasksToFile();
        } catch (Exception e) {
            throw new DukeException("That is not a valid task number!");
        }
    }

    private static void deleteTask(String input) throws DukeException {
        String[] splitInput = input.split("delete ");
        if (splitInput.length < 2) {
            throw new DukeException("Please specify the number of the task to delete");
        }
        try {
            int taskIdx = Integer.parseInt(splitInput[1]);
            Task task = tasks.remove(taskIdx - 1);
            System.out.printf("Noted. I've removed this task:\n  %s\n", task.toString());
            System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
            syncTasksToFile();
        } catch (Exception e) {
            throw new DukeException("That is not a valid task number!");
        }
    }

    private static void printTasks() {
        if (tasks.size() == 0) {
            System.out.println("You currently have no tasks. ");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + "." + tasks.get(i));
            }
        }
    }

    enum TaskType {
        TODO("todo "),
        DEADLINE("deadline "),
        EVENT("event ");

        public final String string;

        TaskType(String string) {
            this.string = string;
        }
    }

    private static void addTask(String input, TaskType type) throws DukeException {
        String[] splitInput = input.split(type.string);
        String errorMessage = type == TaskType.TODO
                ? "Please add a description for the %s"
                : "Please add a description and date for the %s";
        if (splitInput.length < 2) {
            throw new DukeException(String.format(errorMessage, type.string));
        }
        Task task;
        if (type == TaskType.TODO) {
            String desc = splitInput[1];
            task = new Todo(desc);
        } else {
            splitInput = splitInput[1].split(type == TaskType.DEADLINE ? " /by " : " /at ");
            if (splitInput.length < 2) {
                throw new DukeException(String.format(errorMessage, type.string));
            }
            LocalDate date = LocalDate.parse(splitInput[1]);
            task = type == TaskType.DEADLINE
                    ? new Deadline(splitInput[0], date)
                    : new Event(splitInput[0], date);
        }
        tasks.add(task);
        syncTasksToFile();
        System.out.printf("Got it. I've added this task:\n  %s\n", task.toString());
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }

    public static void main(String[] args) {
        readDataFile();
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?\n");
        Scanner s = new Scanner(System.in);
        while (true) {
            String input = s.nextLine();
            try {
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    printTasks();
                } else if (input.startsWith("mark ")) {
                    markTask(input, true);
                } else if (input.startsWith("unmark ")) {
                    markTask(input, false);
                } else if (input.startsWith("delete ")) {
                    deleteTask(input);
                } else if (input.startsWith("todo ")) {
                    addTask(input, TaskType.TODO);
                } else if (input.startsWith("deadline ")) {
                    addTask(input, TaskType.DEADLINE);
                } else if (input.startsWith("event ")) {
                    addTask(input, TaskType.EVENT);
                } else {
                    throw new DukeException("what");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
