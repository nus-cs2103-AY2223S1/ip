import java.util.*;

public class Duke {
    private static final String TAB = "    ";
    private static final String LINEBREAK = "______________________________";
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
        for (int i = 0; i < index; i++) {
            Task t = store[i];
            String temp = String.format("%d. %s", i + 1, t);
            printTab(temp);
        }
        printTab(LINEBREAK);
    }

}
