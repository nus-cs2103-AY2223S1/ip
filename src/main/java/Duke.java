import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
public class Duke {
    protected static final String TOP_WINDOW = "╔══════════════════════════════════════════════╗";
    protected static final String BOTTOM_WINDOW = "╚══════════════════════════════════════════════╝";
    private static final String GREETING = "Hi, I'm Ploopy! Nice to meet you!\n\tWhats up?";
    private static final String FAREWELL = "Okay then, see ya later :)";
    private static final String COMPLETED_TASK = "Nice! You've completed this task. I'll mark it as done.";
    private static final String incompleteTask = "Alright this task has been marked as undone.";
    private static final String addedTask = "I've added this task to your list.\n\tHere you go: ";

    //Exceptions:
    private static final String NoInputMessage = "You didn't say what I should do! (ಠ_ʖಠ)";
    private static final String EmptyCommandMessage = "What should I do with the ";
    private static final String NonsenseInputMessage = "I can't do that right now, sorry ┐(‘～`；)┌";
    private static final String FOLDER_PATH = "./data/";
    private static final String FILE_NAME = "PloopyDatabase.txt";
    private static final String FILE_PATH = FOLDER_PATH + FILE_NAME;
    private static File folder, file;

    public static final String textArt = "\n" +
            "\t███████████████████████████████████\n" +
            "\t█▄─▄▄─█▄─▄███─▄▄─█─▄▄─█▄─▄▄─█▄─█─▄█\n" +
            "\t██─▄▄▄██─██▀█─██─█─██─██─▄▄▄██▄─▄██\n" +
            "\t▀▄▄▄▀▀▀▄▄▄▄▄▀▄▄▄▄▀▄▄▄▄▀▄▄▄▀▀▀▀▄▄▄▀▀";

    private static ArrayList<Task> tasks;

    public static void start() {
        System.out.println(messageFormatter(textArt));
        System.out.println(messageFormatter(GREETING));
        tasks = new ArrayList<>();
        folder = new File(FOLDER_PATH);
        file = new File(FILE_PATH);
        if (!folder.exists()) {
            System.out.println(messageFormatter("Creating necessary files..."));
            try {
                folder.mkdir();
            } catch (SecurityException e) {
                System.out.println(e.getMessage());
            }
        }

        try {
            if (!file.createNewFile()) {
                System.out.println(messageFormatter("Looks like you have previous tasks " +
                        "\n- let me load them up..."));
                readFromFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

         command();
    }

    protected static String messageFormatter(String input) {
        return TOP_WINDOW + "\n\t" + input + "\n" + BOTTOM_WINDOW;
    }

    private static String formatLineToWrite(Task task) {
        final String seperator = "/";
        String type = task.getType();
        String done = task.isDone() ? "1" : "0";
        String name = task.getName();
        String date = task.getDate();
        return type + seperator + done + seperator + name + seperator + date;
    }

    private static void readFromFile() {
        try {
            Scanner fileReader = new Scanner(file);
            System.out.println(messageFormatter("Adding existing tasks..."));
            while (fileReader.hasNext()) {
                spliceData(fileReader.nextLine());
            }
            System.out.println(messageFormatter("Tasks added!"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void spliceData(String input) {
        String[] inputSequence = input.split("/");
        String type = inputSequence[0];
        String name = inputSequence[2];
        String date = inputSequence.length > 3 ? inputSequence[3] : "";
        Task createdTask = Task.createTask(type, name, date);
        if (inputSequence[1].equals("1")) {
            createdTask.markDone();
        }
        tasks.add(createdTask);
    }

    private static void rewriteFile() {
        try {
            FileWriter fileDelete = new FileWriter(FILE_PATH, false);
            fileDelete.write("");
            fileDelete.close();
            FileWriter fileWriter = new FileWriter(FILE_PATH, true);
            for (Task task : tasks) {
                fileWriter.write(formatLineToWrite(task) + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void writeToFile(Task task) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH, true);
            fileWriter.write(formatLineToWrite(task));
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void displayList() {
        System.out.println(TOP_WINDOW);
        int index = 1;
        for (Task item : tasks) {
            System.out.println("\n" + index + "." + item);
            index++;
        }
        System.out.println(BOTTOM_WINDOW);
    }

    private static void markTask(int taskIndex) throws DukeException {
        if (taskIndex > 0 && taskIndex <= tasks.size()) {
            Task current = tasks.get(taskIndex - 1);
            current.markDone();
            System.out.println(messageFormatter(COMPLETED_TASK + "\n\t" + " " + current));
            rewriteFile();
        } else throw new DukeException(messageFormatter("That task number doesn't exist on your list!"));
    }

    private static void unmarkTask(int taskIndex) throws DukeException {
        if (taskIndex > 0 && taskIndex <= tasks.size()) {
            Task current = tasks.get(taskIndex - 1);
            current.unmark();
            System.out.println(messageFormatter(incompleteTask + "\n\t" + " " + current));
            rewriteFile();
        } else throw new DukeException(messageFormatter("That task number doesn't exist on your list!"));
    }

    private static void deleteTask(int taskNumber) throws DukeException {
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            //totalTasks--;
            String message = "Deleted: " + tasks.get(taskNumber - 1) + "\n\tYou have "
                    + tasks.size() + " task(s) remaining.";
            tasks.remove(taskNumber - 1);
            System.out.println(messageFormatter(message));
            rewriteFile();
        } else throw new DukeException(messageFormatter("That task number doesn't exist on your list!"));
    }

    private static void addTaskMessage(Task task) {
        String message = addedTask + task.toString() + "\n\tYou have " + tasks.size() + " tasks in your list.";
        System.out.println(messageFormatter(message));
    }

    private static boolean isEmptyCommand(String command, int size) {
        return command.trim().length() == size;
    }

    private static void createToDo(String input) throws DukeException {
        if (!isEmptyCommand(input, "todo".length())) {
            Task newTask = new ToDo(input.split(" ")[1], "");
            tasks.add(newTask);
            addTaskMessage(newTask);
            writeToFile(newTask);
            //totalTasks++;
        } else throw new DukeException(messageFormatter(EmptyCommandMessage + " todo"));
    }

    private static void createDeadline(String input) throws DukeException {
        if (!isEmptyCommand(input, "deadline".length())) {
            String date = input.split("/")[1];
            String name = input.split(" ")[1];
            Task newTask = new Deadline(name, date);
            tasks.add(newTask);
            addTaskMessage(newTask);
            writeToFile(newTask);
            //totalTasks++;
        } else throw new DukeException(messageFormatter(EmptyCommandMessage + " deadline"));
    }

    private static void createEvent(String input) throws DukeException {
        if (!isEmptyCommand(input, "event".length())) {
            String date = input.split("/")[1];
            String name = input.split(" ")[1];
            Task newTask = new Event(name, date);
            tasks.add(newTask);
            addTaskMessage(newTask);
            writeToFile(newTask);
            //totalTasks++;
        } else throw new DukeException(messageFormatter(EmptyCommandMessage + " event"));
    }

    private static void command() {
        System.out.println(messageFormatter("What do you wanna do to your list?"));
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String command;

        while (!input.equals("bye")) {
            try {
                if (input.isBlank() || input.isEmpty()) throw new DukeException(messageFormatter(NoInputMessage));
                command = input.split(" ")[0];
                switch (command) {
                    case "mark":
                        markTask(input.charAt(5) - 48);
                        break;
                    case "unmark":
                        unmarkTask(input.charAt(7) - 48);
                        break;
                    case "list":
                        displayList();
                        break;
                    case "delete":
                        deleteTask(input.charAt(7) - 48);
                        break;
                    case "todo":
                        createToDo(input);
                        break;
                    case "deadline":
                        createDeadline(input);
                        break;
                    case "event":
                        createEvent(input);
                        break;
                    default:
                        throw new DukeException(messageFormatter(NonsenseInputMessage));
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            input = scanner.nextLine();
        }
        System.out.println(messageFormatter(FAREWELL));
    }

    public static void main(String[] args) {
        start();

    }
}
