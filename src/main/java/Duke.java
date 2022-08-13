import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static boolean isAcceptingInput;
    private static ArrayList<Task> tasks;

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

    private static void addTask(String input) {
        tasks.add(new Task(input));
        printMessage("added: " + input);
    }

    private static void listTasks() {
        int pointer = 1;
        String reply = "Here are the tasks in your list:";
        for (Task task : tasks) {
            reply += "\n" + pointer + "." + task;
            pointer++;
        }
        printMessage(reply);
    }

    private static void markTask(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
        printMessage("Nice! I've marked this task as done:\n" + task);
    }

    private static void unmarkTask(int index) {
        Task task = tasks.get(index);
        task.markAsUndone();
        printMessage("OK, I've marked this task as not done yet:\n" + task);
    }

    private static void processInput(String input) {
        if (input.equals("bye")) {
            exit();
            return;
        } else if (input.equals("list")) {
            listTasks();
            return;
        }

        // May throw exception if input digit is greater than length of tasks. Should fix later.
        String[] str = input.split(" ");
        String cmd = str[0];
        if (cmd.equals("mark")) {
            int taskIndex = Integer.parseInt(str[1]) - 1;
            markTask(taskIndex);
        } else if (cmd.equals("unmark")) {
            int taskIndex = Integer.parseInt(str[1]) - 1;
            unmarkTask(taskIndex);
        } else {
            addTask(input);
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
