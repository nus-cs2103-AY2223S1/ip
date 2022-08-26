import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    private Parser parser;
    private final String LINE_BREAK
            = "____________________________________________________________";

    public UI(){
        this.parser = new Parser();
    }

    public void run(Duke duke) {
        Scanner scanner = new Scanner(System.in);
        print("Hello! I'm Duke\nWhat can I do for you?");
        String s;
        while(true) {
            s = scanner.nextLine();
            if (parser.parse(s)) {
                parser.runCommand(duke);
            } else {
                if (s.equals("bye")) {
                    print("Bye. Hope to see you again soon!");
                    return;
                } else {
                    printError("Please enter a valid command:\n\n" + "mark\n"
                            + "unmark\n" + "list\n" + "todo\n" + "deadline\n" + "event");
                }
            }
        }
    }

    public void print(String s) {
        System.out.println(LINE_BREAK);
        System.out.println(s);
        System.out.println(LINE_BREAK);
        System.out.println();
    }

    public void printError(String errorMessage) {
        System.out.println(LINE_BREAK);
        System.out.println("----Error----\n" + errorMessage);
        System.out.println(LINE_BREAK);
        System.out.println();
    }

    public void printTasks(ArrayList<Task> tasks) {
        System.out.println(LINE_BREAK);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println(LINE_BREAK);
        System.out.println();
    }

    public void printTaskAdded(Task t, int count) {
        System.out.println(LINE_BREAK);
        System.out.println("Got it. I've added this task:\n"
                + "  " + t);
        System.out.println("Now you have " + count + " tasks in the list.");
        System.out.println(LINE_BREAK);
        System.out.println();
    }

    public void printTaskDeleted(Task t, int count) {
        System.out.println(LINE_BREAK);
        System.out.println("Noted. I've removed this task:\n"
                + "  " + t);
        System.out.println("Now you have " + count + " tasks in the list.");
        System.out.println(LINE_BREAK);
        System.out.println();
    }

    public void printTaskMarked(Task t, boolean b) {
        if (b) {
            print("Nice! I've marked this task as done: \n"
                    + "  " + t);
        } else {
            print("OK, I've marked this task as not done: \n"
                    + "  " + t);
        }
    }
}
