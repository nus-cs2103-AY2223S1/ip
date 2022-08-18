import java.util.*;

public class Duke {
    private static final String TAB = "    ";
    private static final String LINEBREAK = "___________________________________";
    private static Scanner sc = new Scanner(System.in);
    private static Task[] store = new Task[100];
    private static int index = 0;

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
                    add(s);
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
        store[index] = new Task(s);
        index++;
        String temp = "added: " + s;
        echo(temp);
    }

    public static void list() {
        printTab(LINEBREAK);
        printTab("Here are the tasks in your list:");
        for (int i = 0; i < index; i++) {
            Task t = store[i];
            String temp = String.format("%d. %s", i + 1, t);
            printTab(temp);
        }
        printTab(LINEBREAK);
    }

    public static void mark(String[] arr) {
        Task t = store[Integer.parseInt(arr[1]) - 1];
        printTab("Nice! I've marked this task as done:");
        t.mark();
        printTab("  " + t);
    }

    public static void unMark(String[] arr) {
        Task t = store[Integer.parseInt(arr[1]) - 1];
        printTab("OK, I've marked this task as not done yet:");
        t.unMark();
        printTab("  " + t);
    }

    public static void todo(String[] arr) {
        String s = arr[1];
        Todo t = new Todo(s);
        store[index] = t;
        index++;
        printTab(LINEBREAK);
        printTab("Got it. I've added this task:");
        printTab("  " + t);
        String temp = String.format("Now you have %d tasks in the list.", index);
        printTab(temp);
        printTab(LINEBREAK);
    }

    public static void deadline(String[] arr) {
        String[] temp = arr[1].split(" /");
        String s1 = temp[0];
        String s2 = temp[1].split(" ", 2)[1];
        Deadline t = new Deadline(s1, s2);
        store[index] = t;
        index++;
        printTab(LINEBREAK);
        printTab("Got it. I've added this task:");
        printTab("  " + t);
        String temp1 = String.format("Now you have %d tasks in the list.", index);
        printTab(temp1);
        printTab(LINEBREAK);
    }

    public static void event(String[] arr) {
        String[] temp = arr[1].split(" /");
        String s1 = temp[0];
        String s2 = temp[1].split(" ", 2)[1];
        Event t = new Event(s1, s2);
        store[index] = t;
        index++;
        printTab(LINEBREAK);
        printTab("Got it. I've added this task:");
        printTab("  " + t);
        String temp1 = String.format("Now you have %d tasks in the list.", index);
        printTab(temp1);
        printTab(LINEBREAK);
    }
}
