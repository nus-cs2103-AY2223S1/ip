import java.util.*;

public class Duke {
    private static final String TAB = "    ";
    private static final String LINEBREAK = "___________________________________";
    private static Scanner sc = new Scanner(System.in);
    private static List<Task> store = new ArrayList<>();

    public static void main(String[] args) {
        greet();
        String s = sc.nextLine();
        String[] arr = s.split(" ", 2);
        command(arr, s);
    }

    private static void printTab(String content) {
        System.out.println(TAB + content);
    }

    public static void command(String[] arr, String s) {
        String comm = arr[0];
        if (comm.equals("bye")) {
            printTab(LINEBREAK);
            printTab("Bye. Hope to see you again soon!");
            printTab(LINEBREAK);
            System.out.println();
        } else {
            try {
                switch (comm) {
                    case "list":
                        list();
                        break;
                    case "mark":
                        mark(arr);
                        break;
                    case "unmark":
                        unMark(arr);
                        break;
                    case "todo":
                        todo(arr);
                        break;
                    case "deadline":
                        deadline(arr);
                        break;
                    case "event":
                        event(arr);
                        break;
                    default:
//                        add(s);
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                printTab(LINEBREAK);
                printTab(e.getMessage());
                printTab(LINEBREAK);
            } catch (ArrayIndexOutOfBoundsException e) {
                printTab(LINEBREAK);
                printTab(e.getMessage());
                printTab(LINEBREAK);
            }
            String newS = sc.nextLine();
            String[] newArr = newS.split(" ", 2);
            command(newArr, newS);
        }
    }

    public static void greet() {
        printTab(LINEBREAK);
        printTab("Hello! I'm Duke");
        printTab("What can I do for you?");
        printTab(LINEBREAK);
        System.out.println();
    }

    public static void echo(String s) {
        printTab(LINEBREAK);
        printTab(s);
        printTab(LINEBREAK);
        System.out.println();
    }

    public static void add(String s) {
        store.add(new Task(s));
        String temp = "added: " + s;
        echo(temp);
    }

    public static void list() {
        printTab(LINEBREAK);
        printTab("Here are the tasks in your list:");
        for (int i = 0; i < store.size(); i++) {
            Task t = store.get(i);
            String temp = String.format("%d. %s", i + 1, t);
            printTab(temp);
        }
        printTab(LINEBREAK);
    }

    public static void mark(String[] arr) {
        Task t = store.get(Integer.parseInt(arr[1]) - 1);
        t.mark();
        printTab(LINEBREAK);
        printTab("Nice! I've marked this task as done:");
        printTab("  " + t);
        printTab(LINEBREAK);
    }

    public static void unMark(String[] arr) {
        Task t = store.get(Integer.parseInt(arr[1]) - 1);
        t.unMark();
        printTab(LINEBREAK);
        printTab("OK, I've marked this task as not done yet:");
        printTab("  " + t);
        printTab(LINEBREAK);
    }

    public static void todo(String[] arr) throws ArrayIndexOutOfBoundsException {
        if (arr.length == 1) {
            throw new ArrayIndexOutOfBoundsException("OOPS!!! The description of a todo cannot be empty.");
        }
        String s = arr[1];
        Todo t = new Todo(s);
        store.add(new Todo(s));
        printTab(LINEBREAK);
        printTab("Got it. I've added this task:");
        printTab("  " + t);
        String temp = String.format("Now you have %d tasks in the list.", store.size());
        printTab(temp);
        printTab(LINEBREAK);
    }

    public static void deadline(String[] arr) throws ArrayIndexOutOfBoundsException {
        if (arr.length == 1) {
            throw new ArrayIndexOutOfBoundsException("OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] temp = arr[1].split(" /");
        String s1 = temp[0];
        String s2 = temp[1].split(" ", 2)[1];
        Deadline t = new Deadline(s1, s2);
        store.add(t);
        printTab(LINEBREAK);
        printTab("Got it. I've added this task:");
        printTab("  " + t);
        String temp1 = String.format("Now you have %d tasks in the list.", store.size());
        printTab(temp1);
        printTab(LINEBREAK);
    }

    public static void event(String[] arr) throws ArrayIndexOutOfBoundsException {
        if (arr.length == 1) {
            throw new ArrayIndexOutOfBoundsException("OOPS!!! The description of a event cannot be empty.");
        }
        String[] temp = arr[1].split(" /");
        String s1 = temp[0];
        String s2 = temp[1].split(" ", 2)[1];
        Event t = new Event(s1, s2);
        store.add(t);
        printTab(LINEBREAK);
        printTab("Got it. I've added this task:");
        printTab("  " + t);
        String temp1 = String.format("Now you have %d tasks in the list.", store.size());
        printTab(temp1);
        printTab(LINEBREAK);
    }
}
