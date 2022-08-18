import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        int state = 0;
        while (state >= 0) {
            String m = sc.nextLine();
            state = handleMessage(m);
        }
    }

    private static int handleMessage(String m) {
        if (m.equals("bye")) {
            printMessage("Bye. Hope to see you again soon!");
            return -1;
        } else if (m.equals("list")) {
            String messageList = generateList();
            printMessage(messageList);
            return 1;
        } else if (m.length() > 5 && m.startsWith("mark")) {
            int id = Integer.parseInt(m.substring(5));
            Task t = Duke.taskList.get(id - 1);
            t.done();
            printMessage("Nice! I've marked this task as done:\n    " + t);
            return 2;
        } else if (m.length() > 7 && m.startsWith("unmark")) {
            int id = Integer.parseInt(m.substring(7));
            Task t = Duke.taskList.get(id - 1);
            t.undone();
            printMessage("OK, I've marked this task as not done yet:\n    " + t);
            return 3;
        } else {
            storeMessage(m);
            printMessage("added: " + m);
            return 100;
        }
    }

    private static void printMessage(String m) {
        String message = "    ____________________________________________________________\n    "
                + m
                + "\n    ____________________________________________________________\n";
        System.out.println(message);
    }

    private static void storeMessage(String m) {
        Duke.taskList.add(new Task(m));
    }

    private static String generateList() {
        if (Duke.taskList.size() == 0) {
            return "";
        }
        String out = "";
        int count = 1;
        for (Task t: Duke.taskList) {
            out += "\n    " + count++ + ". " + t;
        }
        return out.substring(5);
    }
}
