import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static boolean isAcceptingInput;
    private static ArrayList<String> tasks;

    private static void startUp() {
        isAcceptingInput = true;
        tasks = new ArrayList<>();
        printStartupMessage();
    }

    private static void printMessage(String msg) {
        String border = "____________________________________________________________\n";
        System.out.println(border + msg + "\n" + border);
    }

    private static void printStartupMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String startupMsg = "Hello! I'm Duke\n" + "What can I do for you?";
        printMessage(startupMsg);
    }

    private static void exit() {
        isAcceptingInput = false;
        String exitMsg = "Bye. Hope to see you again soon!";
        printMessage(exitMsg);
    }

    private static void storeTask(String input) {
        tasks.add(input);
        printMessage("added: " + input);
    }

    private static void listTasks() {
        int pointer = 1;
        String reply = "";
        for (String task : tasks) {
            if (pointer != 1) {
                reply += "\n";
            }
            reply += pointer + ". " + task;
            pointer++;
        }
        printMessage(reply);
    }

    private static void processInput(String input) {
        switch (input) {
            case "bye":
                exit();
                break;
            case "list":
                listTasks();
                break;
            default:
                storeTask(input);
        }
    }

    private static void startDuke() {
        startUp();

        Scanner sc = new Scanner(System.in);
        while (isAcceptingInput) {
            String input = sc.nextLine();
            processInput(input);
        }
    }

    public static void main(String[] args) {
        startDuke();
    }
}
