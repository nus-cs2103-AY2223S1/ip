import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    enum Ability {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE
    }

    private final static String HELLO = "    ____________________________________________________________\n" +
            "     Hello! I'm Duke\n" +
            "     What can I do for you?\n" +
            "    ____________________________________________________________\n";
    private final static String BREAK = "    ____________________________________________________________";
    private final static String PATH = "data/duke.txt";

    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println(HELLO);

        try {
            File taskFile = new File(PATH);
            Scanner sc = new Scanner(taskFile);

            while (sc.hasNext()) {
                String input = sc.nextLine();
                String[] inputArr = input.split(" \\| ");
                String type = inputArr[0];
                Task task;
                switch (type) {
                    case "T":
                        task = new Todo(inputArr[2]);
                        tasks.add(task);
                        if (inputArr[1].equals("1")) {
                            task.markAsDone();
                        }
                        break;
                    case "D":
                        task = new Deadline(inputArr[2], inputArr[3]);
                        tasks.add(task);
                        if (inputArr[1].equals("1")) {
                            task.markAsDone();
                        }
                        break;
                    case "E":
                        task = new Event(inputArr[2], inputArr[3]);
                        tasks.add(task);
                        if (inputArr[1].equals("1")) {
                            task.markAsDone();
                        }
                        break;
                    default:
                        System.out.println("hiii");
                        break;
                }
            }
        } catch (FileNotFoundException err) {
            printError(err);
        }

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] inputArr = input.split(" ");
        String action = inputArr[0];


        while (!action.equals("bye")) {
            int number;
            Task task;
            String[] params;
            try {
                switch (action) {
                    case "list":
                        printList();
                        break;
                    case "mark":
                        if (inputArr.length > 2 || inputArr.length == 1) {
                            throw new DukeException("The format should be: mark <number>!");
                        }
                        number = Integer.parseInt(inputArr[1]);
                        if (number > tasks.size()) {
                            throw new DukeException("The index is invalid!");
                        }
                        task = tasks.get(number - 1);
                        task.markAsDone();
                        System.out.println(BREAK +
                                "\n" +
                                "     Nice! I've marked this task as done:\n       " +
                                task +
                                "\n" +
                                BREAK);
                        break;
                    case "unmark":
                        if (inputArr.length > 2 || inputArr.length == 1) {
                            throw new DukeException("The format should be: unmark <number>!");
                        }
                        number = Integer.parseInt(inputArr[1]);
                        if (number > tasks.size()) {
                            throw new DukeException("The index is invalid!");
                        }
                        task = tasks.get(number - 1);
                        task.markAsNotDone();
                        System.out.println(BREAK +
                                "\n" +
                                "     Nice! I've marked this task as not done yet:\n       " +
                                task +
                                "\n" +
                                BREAK);
                        break;
                    case "todo":
                        if (input.substring(4).replaceAll("\\s+", "").equals("")) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                        task = new Todo(input.substring(5));
                        addTask(task);
                        break;
                    case "deadline":
                        if (input.substring(8).replaceAll("\\s+", "").equals("")) {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        }
                        if (!input.contains("/by")) {
                            throw new DukeException("The timing of a deadline cannot be omitted.");
                        }
                        params = input.substring(9).split(" /by ");
                        task = new Deadline(params[0], params[1]);
                        addTask(task);
                        break;
                    case "event":
                        if (input.substring(5).replaceAll("\\s+", "").equals("")) {
                            throw new DukeException("The description of an event cannot be empty.");
                        }
                        if (!input.contains("/at")) {
                            throw new DukeException("The timing of an event cannot be omitted.");
                        }
                        params = input.substring(6).split(" /at ");
                        task = new Event(params[0], params[1]);
                        addTask(task);
                        break;
                    case "delete":
                        if (inputArr.length > 2 || inputArr.length == 1) {
                            throw new DukeException("The format should be: delete <number>!");
                        }
                        number = Integer.parseInt(inputArr[1]);
                        if (number > tasks.size()) {
                            throw new DukeException("The index is invalid!");
                        }
                        task = tasks.get(number - 1);
                        tasks.remove(number - 1);
                        System.out.println(BREAK +
                                "\n" +
                                "     Okay! I've removed this task from the list:\n       " +
                                task +
                                "\n" +
                                BREAK +
                                "\n");
                        break;
                    default:
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException err) {
                printError(err);
            }
            input = sc.nextLine();
            inputArr = input.split(" ");
            action = inputArr[0];
        }

        saveTask();
    }


    private static void addTask(Task task) {
        tasks.add(task);
        System.out.println(BREAK +
                "\n" +
                "     Got it. I've added this task:\n       " +
                task);
        System.out.format("     Now you have %d tasks in the list.\n" +
                        BREAK +
                        "\n",
                tasks.size());
    }


    private static void printList() {
        if (tasks.size() == 0) {
            System.out.println(BREAK +
                    "\n" +
                    "     There is no pending task for you." +
                    "\n" +
                    BREAK);
        } else {
            System.out.println(BREAK +
                    "\n" +
                    "     Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.format("     %d.%s\n", i + 1, tasks.get(i));
            }
            System.out.println(BREAK);
        }
    }

    private static void printError(Exception err) {
        System.out.println(BREAK +
                "\n" +
                "     ☹ OOPS!!! " +
                err +
                "\n" +
                BREAK +
                "\n");
    }


    public static void saveTask() {
        try {
            FileWriter fw = new FileWriter(PATH);
            for(int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).toSave());
            }
            fw.close();
        } catch (IOException err) {
            printError(err);
        }
    }

}
