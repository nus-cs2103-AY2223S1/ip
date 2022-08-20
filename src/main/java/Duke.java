import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * An interactive chatbot which can keep track of a list of tasks.
 *
 * @author Lai Han Wen
 */
public class Duke {

    /**
     * Arraylist which stores the current list of tasks.
     */
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Marks a particular task as done.
     *
     * @param input String input from the user.
     */
    public static void isMark(String input) {
        String numString = input.replace("mark", "")
                .replace(" ", ""); // Isolate int from string input
        int num = Integer.parseInt(numString);
        tasks.get(num - 1).markAsDone();
        saveToFile();
        System.out.println("Enter command:");
    }

    /**
     * Prints the current list of tasks.
     */
    public static void isList() {
        int count = 1;
        for (Task t : tasks) {
            String s = Integer.toString(count);
            System.out.println(s + ". " + t);
            count++;
        }
        System.out.println("Enter command:");
    }

    /**
     * Adds a deadline task to the current list of tasks.
     *
     * @param input String input from the user.
     * @throws DukeException if deadline task is empty.
     */
    public static void isDeadline(String input) throws DukeException {
        if (input.replace(" ", "").length() == 8) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String description = input.substring(9, input.indexOf('/') - 1);
        LocalDate by = LocalDate.parse(input.substring(input.indexOf('/') + 4));
        Task t = new Deadline(description, by);
        tasks.add(t);
        saveToFile();
        reply(t);
    }

    /**
     * Adds a todo task to the current list of tasks.
     *
     * @param input String input from the user.
     * @throws DukeException if todo task is empty.
     */
    public static void isTodo(String input) throws DukeException {
        if (input.replace(" ", "").length() == 4) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String description = input.substring(5);
        Task t = new Todo(description);
        tasks.add(t);
        saveToFile();
        reply(t);
    }

    /**
     * Adds an event task to the current list of tasks.
     *
     * @param input String input from the user.
     * @throws DukeException if event task is empty.
     */
    public static void isEvent(String input) throws DukeException {
        if (input.replace(" ", "").length() == 5) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        String description = input.substring(6, input.indexOf('/') - 1);
        String at = input.substring(input.indexOf('/') + 4);
        Task t = new Event(description, at);
        tasks.add(t);
        saveToFile();
        reply(t);
    }

    /**
     * Removes a particular task from the current list of tasks.
     *
     * @param input String input from the user.
     */
    public static void isDelete(String input) {
        String numString = input.replace("delete", "")
                .replace(" ", "");
        int num = Integer.parseInt(numString);
        System.out.println("Noted. I've removed this task:\n" + "  " + tasks.get(num - 1));
        tasks.remove(num - 1);
        saveToFile();
        String size = Integer.toString(tasks.size());
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("Enter command:");
    }

    /**
     * Prints reply after adding a task to the current list of tasks.
     *
     * @param t The task added to the current list of tasks.
     */
    public static void reply(Task t) {
        String size = Integer.toString(tasks.size());
        System.out.println("Got it. I've added this task:\n" + "  " + t);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("Enter command:");
    }

    /**
     * Saves the current list of tasks to "duke.txt" file.
     */
    public static void saveToFile() {
        String filePath = "data\\duke.txt";
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.flush();
            for (Task t : tasks) {
                fw.write(t.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates "data" folder if folder does not exist, and does nothing if
     * folder already exists.
     */
    public static void createFolder() {
        Path folder = Paths.get("data");
        try {
            Files.createDirectories(folder);
        } catch (IOException e) {
            System.err.println("Failed to create folder!" + e.getMessage());
        }
    }

    /**
     * Creates "duke.txt" file in "data" folder if file does not exist, and
     * does nothing if file already exists.
     */
    public static void createFile() {
        File file = new File("data\\duke.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.err.println("Failed to create file!" + e.getMessage());
        }
    }

    /**
     * Loads the data from "duke.txt" file. If file does not exist, create
     * "data" folder and create "duke.txt" file in it.
     */
    public static void loadFromFile() {
        File file = new File("data\\duke.txt");
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
                String s = sc.nextLine();
                String description;
                char task = s.charAt(1);

                if (task == 'T') {
                    description = s.substring(7);
                    tasks.add(new Todo(description));
                    continue;
                }

                description = s.substring(7, s.indexOf('(') - 1);
                String time = s.substring(s.indexOf('(') + 5, s.indexOf(')'));
                if (task == 'D') {
                    tasks.add(new Deadline(description, time));
                    continue;
                }

                if (task == 'E') {
                    tasks.add(new Event(description, time));
                }

            }
        } catch (FileNotFoundException e) {
            createFolder();
            createFile();
        }
    }

    /**
     * Reads String input from user and decides what to do next. Possible actions
     * include adding a task, marking a task as done and deleting a task from the
     * current list of tasks.
     */
    public static void decision() {
        Scanner sc = new Scanner(System.in);

        String command = sc.nextLine();

        while (!Objects.equals(command, "bye")) {

            if (command.contains("deadline")) {
                try {
                    isDeadline(command);
                } catch (DukeException e) {
                    System.out.println(e);
                }
                command = sc.nextLine();
                continue;
            }

            if (command.contains("todo")) {
                try {
                    isTodo(command);
                } catch (DukeException e) {
                    System.out.println(e);
                }
                command = sc.nextLine();
                continue;
            }

            if (command.contains("event")) {
                try {
                    isEvent(command);
                } catch (DukeException e) {
                    System.out.println(e);
                }
                command = sc.nextLine();
                continue;
            }

            if (command.contains("delete")) {
                isDelete(command);
                command = sc.nextLine();
                continue;
            }

            if (command.contains("mark")) {
                isMark(command);
                command = sc.nextLine();
                continue;
            }

            if (Objects.equals(command, "list")) {
                isList();
                command = sc.nextLine();
                continue;
            }

            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(" +
                    "\nEnter command:"); // Incorrect input: unknown input
            command = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");

        loadFromFile();

        System.out.println("Enter command:"); // Prompt user input

        decision();
    }
}
