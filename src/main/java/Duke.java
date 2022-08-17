import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String GREETING = "Hello! I'm Duke\n"
            + "What can I do for you?\n";

    private static final List<Task> TASKS = new ArrayList<>();
    // private static final File FILE = new File("data/duke.txt");


    public static void main(String[] args) {
        System.out.println(LOGO);
        System.out.println(GREETING);

        loadLocalData();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            try {
                String userInput = scanner.nextLine();
                if (userInput.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    scanner.close();
                    break;
                }

                if (userInput.equals("list")) {
                    printTasks();
                    continue;
                }

                String[] split = userInput.split(" ");
                if (split.length == 2 && isNumeric(split[1]) && split[0].equals("mark")) {
                    markTaskAsDone(getTask(Integer.parseInt(split[1]) - 1));
                    storeLocalData();
                    continue;
                }

                if (split.length == 2 && isNumeric(split[1]) && split[0].equals("unmark")) {
                    markTaskAsUndone(getTask(Integer.parseInt(split[1]) - 1));
                    storeLocalData();
                    continue;
                }

                if (split.length == 2 && isNumeric(split[1]) && split[0].equals("delete")) {
                    deleteTask(getTask(Integer.parseInt(split[1]) - 1));
                    storeLocalData();
                    continue;
                }

                if (split[0].equals("todo")) {
                    addToDo(userInput.substring(4).trim());
                    storeLocalData();
                    continue;
                }

                if (split[0].equals("deadline") && userInput.contains("/by")) {
                    int index = userInput.indexOf("/by");
                    addDeadline(userInput.substring(8, index).trim(), userInput.substring(index + 3).trim());
                    storeLocalData();
                    continue;
                }

                if (split[0].equals("event") && userInput.contains("/at")) {
                    int index = userInput.indexOf("/at");
                    addEvent(userInput.substring(5, index).trim(), userInput.substring(index + 3).trim());
                    storeLocalData();
                    continue;
                }

                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void loadLocalData() {
        try {
            Files.createDirectories(Paths.get("data/"));
            File file = new File("data/duke.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String[] entry = scanner.nextLine().split(" \\| ");
                Task task = null;
                if (entry[0].equals("T")) {
                    task = new ToDo(entry[2]);
                } else if (entry[0].equals("D")) {
                    task = new Deadline(entry[2], entry[3]);
                } else {
                    task = new Event(entry[2], entry[3]);
                }

                if (entry[1].equals("1")) {
                    task.mark();
                }
                TASKS.add(task);
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void storeLocalData() {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            List<String> list = new ArrayList<>();
            for (Task task : TASKS) {
                list.add(task.stringifyTask());
            }
            fw.write(String.join(System.lineSeparator(), list));
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static boolean isNumeric(String input) {
        for (char c : input.toCharArray()) {
            if (c < 48 || c > 57) {
                return false;
            }
        }
        return true;
    }

    private static Task getTask(int index) throws DukeException {
        if (index < 0 || index >= TASKS.size()) {
            throw new DukeException("OOPS!!! The task number is out of bounds.");
        }
        return TASKS.get(index);
    }

    private static void deleteTask(Task task) {
        TASKS.remove(task);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", TASKS.size()));
    }

    private static void markTaskAsDone(Task task) {
        task.mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    private static void markTaskAsUndone(Task task) {
        task.unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
    }

    private static void addToTasks(Task task) {
        TASKS.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(String.format("%s", task.toString()));
        System.out.println(String.format("Now you have %d tasks in the list.", TASKS.size()));
    }

    private static void addToDo(String description) throws DukeException {
        if (description.length() == 0) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        addToTasks(new ToDo(description));
    }

    private static void addDeadline(String description, String by) throws DukeException {
        if (description.length() == 0) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
        if (by.length() == 0) {
            throw new DukeException("OOPS!!! The date of a deadline cannot be empty.");
        }
        try {
            LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! Please input date in YYYY-MM-DD format.");
        }
        addToTasks(new Deadline(description, by));
    }

    private static void addEvent(String description, String at) throws DukeException {
        if (description.length() == 0) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        }
        if (at.length() == 0) {
            throw new DukeException("OOPS!!! The date of an event cannot be empty.");
        }
        addToTasks(new Event(description, at));
    }

    private static void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < TASKS.size(); i++) {
            System.out.println(String.format("%d. %s", 1 + i, TASKS.get(i).toString()));
        }
    }
}
