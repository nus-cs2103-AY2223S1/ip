import java.util.Scanner;
import java.util.regex.Pattern;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int count = 0;

    public static boolean is(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static void printBot(String s) {
        String lineBreak = "____________________________________________________________";
        System.out.println(lineBreak);
        System.out.println(s);
        System.out.println(lineBreak);
        System.out.println();
    }

    public static void printBot(Task[] tasks) {
        String lineBreak = "____________________________________________________________";
        System.out.println(lineBreak);
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
        String markRegex = "(un)?mark\\s[1-9][0-9]*";
        Pattern markPt = Pattern.compile(markRegex);
        while(true) {
            s = scanner.nextLine();
            if (markPt.matcher(s).matches()) {
                String[] temp = s.split(" ");
                int index = Integer.parseInt(temp[1]) - 1;
                if (index >= count) {
                    printBot("Task does not exist. Please ensure the task number is correct.");
                } else {
                    markTask(tasks[index], temp[0].equals("mark"));
                }
            } else if (s.equals("list")) {
                printBot(tasks);
            } else if (s.equals("bye")) {
                printBot("Bye. Hope to see you again soon!");
                return;
            } else {
                tasks[count] = new Task(s);
                ++count;
                printBot("added: " + s);
            }
        }
    }
}
