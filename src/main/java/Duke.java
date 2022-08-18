import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static boolean terminate = false;
    private static ArrayList<String> inputList = new ArrayList<String>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke.greet();

        Scanner in = new Scanner(System.in);
        while (!terminate) {
            String userInput = in.nextLine();
            if (userInput.equals("bye")) {
                Duke.exit();
            } else if (userInput.equals("list")) {
                Duke.displayList();
            } else {
                Duke.addTask(userInput);
            }
        }
    }

    public static void lineFormat() {
        System.out.println("    ____________________________________________________________");
    }

    public static void greet() {
        Duke.lineFormat();
        System.out.println(
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n");
        Duke.lineFormat();
    }

    public static void addTask(String echoedMessage) {
        Duke.lineFormat();
        System.out.println("     added: " + echoedMessage);
        Duke.lineFormat();
        inputList.add(echoedMessage);
    }

    public static void displayList() {
        Duke.lineFormat();
        System.out.println("     Here are the tasks in your list: ");
        for (int i = 1; i <= inputList.size(); i++) {
            System.out.println("     " +
                    String.valueOf(i) + ". " +
                    inputList.get(i - 1));
        }
        Duke.lineFormat();
    }


    public static void exit() {
        Duke.terminate = true;
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }
}
