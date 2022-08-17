import java.util.Scanner;

public class Duke {

    private static String printSpacer() {
        return "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    }

    private static boolean isPositiveInt(String s) {
        try {
            int n = Integer.parseInt(s);
            return n > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static Task[] tasks = new Task[100];
    private static int tasksLength = 0;

    private static boolean cont = true;

    private static void printTaskList() {
        if (tasksLength == 0) {
            System.out.println("No tasks yet~");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i <= tasksLength; i++) {
                System.out.println(i + "." + tasks[i - 1].toString());
            }
        }
        System.out.println(printSpacer());
    }

    private static void greeting() {
        System.out.println(printSpacer());
        System.out.println("Hello! This is Duke! What can I do for you today?");
        System.out.println(printSpacer());
    }

    private static void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(printSpacer());
        cont = false;
    }

    private static void invalidCommand() {
        System.out.println("Sorry I don't understand the command...");
        System.out.println(printSpacer());
    }

    private static void addTodo(String s) {
        tasks[tasksLength] = new Todo(s.substring(5));
        tasksLength++;
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[tasksLength - 1].toString());
        System.out.println("Now you have " + tasksLength + " tasks in your list.");
        System.out.println(printSpacer());
    }

    private static void addDeadline(String s) {
        String[] stuff = s.substring(9).split(" /by ");
        tasks[tasksLength] = new Deadline(stuff[0], stuff[1]);
        tasksLength++;
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[tasksLength - 1].toString());
        System.out.println("Now you have " + tasksLength + " tasks in your list.");
        System.out.println(printSpacer());
    }

    private static void addEvent(String s) {
        String[] stuff = s.substring(6).split(" /at ");
        tasks[tasksLength] = new Event(stuff[0], stuff[1]);
        tasksLength++;
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[tasksLength - 1].toString());
        System.out.println("Now you have " + tasksLength + " tasks in your list.");
        System.out.println(printSpacer());
    }

    private static void markTask(String s) {
        int n = Integer.parseInt(s.substring(5));
        if (n > tasksLength) {
            System.out.println("Oops, no such task number!");
            System.out.println(printSpacer());
        } else {
            tasks[n - 1].setDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks[n - 1].toString());
            System.out.println(printSpacer());
        }
    }

    private static void unmarkTask(String s) {
        int n = Integer.parseInt(s.substring(7));
        if (n > tasksLength) {
            System.out.println("Oops, no such task number!");
            System.out.println(printSpacer());
        } else {
            tasks[n - 1].setDone(false);
            System.out.println("Okie, I've marked this task as not done yet:");
            System.out.println(tasks[n - 1].toString());
            System.out.println(printSpacer());
        }
    }

    public static void main(String[] args) {

        greeting();
        Scanner sc = new Scanner(System.in);

        while (cont) {

            String s = sc.nextLine();

            if (s.equals("list")) {
                printTaskList();
            } else if (s.equals("bye")) {
                goodbye();
            } else if (s.startsWith("mark ") && isPositiveInt(s.substring(5))) {
                markTask(s);
            } else if (s.startsWith("unmark ")  && isPositiveInt(s.substring(7))) {
                unmarkTask(s);
            } else if (s.startsWith("todo ")) {
                addTodo(s);
            } else if (s.startsWith("deadline ")) {
                addDeadline(s);
            } else if (s.startsWith("event ")) {
                addEvent(s);
            } else {
                invalidCommand();
            }

        }
    }
}
