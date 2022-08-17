import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int count = 0;
    private static String lineBreak = "____________________________________________________________";

    public static void printBot(String s) {
        System.out.println(lineBreak);
        System.out.println(s);
        System.out.println(lineBreak);
        System.out.println();
    }

    public static void addTask(Task t) {
        tasks[count] = t;
        ++count;

        System.out.println(lineBreak);
        System.out.println("Got it. I've added this task:\n"
                           + "  " + t);
        System.out.println("Now you have " + count + " tasks in the list.");
        System.out.println(lineBreak);
        System.out.println();
    }

    public static void listTasks() {
        String lineBreak = "____________________________________________________________";
        System.out.println(lineBreak);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.length; ++i) {
            if (tasks[i] != null) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
        System.out.println(lineBreak);
        System.out.println();
    }

    public static void markTask(Task t, boolean b) {
        t.setMarked(b);
        if (b) {
            printBot("Nice! I've marked this task as done: \n"
                     + "  " + t);
        } else {
            printBot("OK, I've marked this task as not done: \n"
                    + "  " + t);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Nuke");
        System.out.println("What can I do for you?");
        String s;
        while(true) {
            s = scanner.nextLine();
            String[] words = s.split(" ", 2);
            if (words[0].equals("mark") || words[0].equals("unmark")) {
                if (words.length < 2) {
                    printBot("Error: Please specify an item number");
                }
                try {
                    int index = Integer.parseInt(words[1]) - 1;
                    if (index < 0 || index > count) {
                        printBot("Error: Please specify a valid item number");
                    } else {
                        markTask(tasks[index], words[0].equals("mark"));
                    }
                } catch (NumberFormatException e) {
                    printBot("Error: Please specify an item number\n"
                            + "\"" + words[1] + "\"" + " is not an item number");
                }
            } else if (words[0].equals("todo")) {
                addTask(new ToDo(words[1]));
            } else if (words[0].equals("deadline")) {
                String[] temp = words[1].split(" /by ", 2);
                if (temp.length < 2) {
                    printBot("Error: Please specify a deadline");
                } else {
                    addTask(new Deadline(temp[0], temp[1]));
                }
            } else if (words[0].equals("event")) {
                String[] temp = words[1].split(" /at ", 2);
                if (temp.length < 2) {
                    printBot("Error: Please specify an event date");
                } else {
                    addTask(new Event(temp[0], temp[1]));
                }
            } else if (words[0].equals("list")) {
                if (words.length > 1) {
                    printBot("Error: list expects no arguments");
                } else {
                    listTasks();
                }
            } else if (s.equals("bye")){
                printBot("Bye. Hope to see you again soon!");
                return;
            } else {
                printBot("Error: Please enter a valid command:\n"
                         + "mark\n" + "unmark\n" + "list\n" + "todo\n" + "deadline\n" + "event\n");
            }
        }
    }
}
