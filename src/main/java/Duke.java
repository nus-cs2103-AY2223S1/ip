import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<String> textList = new ArrayList<>();

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
        } else {
            storeMessage(m);
            printMessage("added: " + m);
            return 2;
        }
    }

    private static void printMessage(String m) {
        String message = "    ____________________________________________________________\n    "
                + m
                + "\n    ____________________________________________________________\n";
        System.out.println(message);
    }

    private static void storeMessage(String m) {
        Duke.textList.add(m);
    }

    private static String generateList() {
        if (Duke.textList.size() == 0) {
            return "";
        }
        String out = "";
        int count = 1;
        for (String s: Duke.textList) {
            out += "\n    " + count++ + ". " + s;
        }
        return out.substring(5);
    }
}
