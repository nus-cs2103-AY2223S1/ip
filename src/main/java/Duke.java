import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class Duke {
    private static Scanner input = new Scanner(System.in);  // Create a Scanner object
    private static ArrayList<Task> list = new ArrayList<>(); // list of user input
    private static ArrayList<String> saveList = new ArrayList<>();
    private static int size = 0;


    /**
     * Outputs the goodbye messages.
     */
    public static void bye() {
        save(saveList);
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Returns true if input is within scope of the program.
     *
     * @param in Input of the user.
     * @return True if input is within scope of the program, false otherwise.
     */
    public static boolean withinScope(String in) {
        return (in.startsWith("list") || in.startsWith("mark") || in.startsWith("unmark") || in.startsWith("deadline")
                || in.startsWith("event") || in.startsWith("todo") || in.startsWith("delete"));
    }

    /**
     * Lists the inputs of the user.
     */
    public static void list() {
        int count = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task tasks : list) {
            System.out.println(count + ". " + tasks.toString());
            count += 1;
        }
    }

    /**
     * Deletes a task.
     *
     * @param in Input of the user.
     */
    public static void delete(String in) {
        char n = in.charAt(7);
        int number = Character.getNumericValue(n) - 1;
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + list.get(number).toString());
        list.remove(number);
        size -= 1;
        System.out.println("Now you have " + size + " tasks in the list");
    }

    /**
     * Unmarks a task.
     *
     * @param in Input of the user.
     */
    public static void unmark(String in) {
        char n = in.charAt(7);
        int number = Character.getNumericValue(n) - 1;
        Task t = list.get(number);
        t.markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t.toString());
    }

    /**
     * Marks a task.
     *
     * @param in Input of the user.
     */
    public static void mark(String in) {
        char n = in.charAt(5);
        int number = Character.getNumericValue(n) - 1;
        Task t = list.get(number);
        t.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t.toString());
    }

    /**
     * Adds a deadline event to the list.
     *
     * @param in Input of the user.
     * @throws DukeException An exception unique to Duke.
     */
    public static void deadline(String in) throws DukeException {
        String deadLine = in.replaceFirst("deadline", "");
        if (deadLine.trim().isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else {
            String[] aStr = deadLine.split("/by ", 2);
            String desc = aStr[0];
            String by = aStr[1];
            Deadline d = new Deadline(desc, LocalDate.parse(by));
            list.add(d);
            size += 1;
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + d.toString());
            System.out.println("Now you have " + size + " tasks in the list");
        }
    }

    /**
     * Adds an event task to the list.
     *
     * @param in Input of the user.
     * @throws DukeException An exception unique to Duke.
     */
    public static void event(String in) throws DukeException {
        String event = in.replaceFirst("event", "");
        if (event.trim().isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        } else {
            String[] aStr = event.split("/at ", 2);
            String desc = aStr[0];
            String by = aStr[1];
            Event e = new Event(desc, LocalDate.parse(by));
            list.add(e);
            size += 1;
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + e.toString());
            System.out.println("Now you have " + size + " tasks in the list");
        }
    }

    /**
     * Adds a to-do task to the list.
     *
     * @param in Input of the user.
     * @throws DukeException An exception unique to Duke.
     */
    public static void todo(String in) throws DukeException {
        String todo = in.replaceFirst("todo", "");
        if (todo.trim().isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else {
            Todo t = new Todo(todo);
            list.add(t);
            size += 1;
            System.out.println("Got it. I've added this task:");  // Output user input
            System.out.println("  " + t.toString());
            System.out.println("Now you have " + size + " tasks in the list");
        }
    }

    public static void add(String in) throws DukeException {
        if (!withinScope(in)) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-( ");
        } else if (in.equals("list")) {
            saveList.add(in);
            list();
        } else if (in.startsWith("delete")) {
            saveList.add(in);
            delete(in);
        } else if (in.startsWith("unmark")) {
            saveList.add(in);
            unmark(in);
        } else if (in.startsWith("mark")) {
            saveList.add(in);
            mark(in);
        } else if (in.startsWith("deadline")) {
            saveList.add(in);
            deadline(in);
        } else if (in.startsWith("event")) {
            saveList.add(in);
            event(in);
        } else {
            saveList.add(in);
            todo(in);
        }
    }

    public static void addFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String in = s.nextLine();
            try {
                add(in);
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }
        s.close();
    }

    public static void save(ArrayList<String> list) {
        try {
            new File(System.getProperty("user.dir") + "/data/duke.txt").getParentFile().mkdirs();
            FileWriter fw = new FileWriter(System.getProperty("user.dir") + "/data/duke.txt");
            for (String tasks : list) {
                fw.write(String.format("%s%n", tasks));
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Oops: " + e.getMessage());
        }
    }



    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What may I do for you?");
        try {
            addFileContents(System.getProperty("user.dir") + "/data/duke.txt");
            System.out.println("I have added tasks from your previous session to the current list");
            list();
        } catch (FileNotFoundException e) {
            System.out.println("File not found, proceed with empty list");
        }

        while (input.hasNextLine()) {
            String in = input.nextLine();  // Read user input
            try {
                if (in.equals("bye")) {
                    bye();
                    break;
                } else {
                    add(in);
                }
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }
    }
}

