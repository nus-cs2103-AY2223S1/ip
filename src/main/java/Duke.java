import java.util.Scanner;

public class Duke {
    private static final String LINE_BREAK = "\t" + "-------------------------------------------------";
    private String input;
    private Task[] tasks = new Task[100];
    private int taskIndex = 0;

    private void greet() {
        System.out.println("Hello! I'm Ee Suan!\nWhat can I do for you?");
    }

    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void echo() {
        System.out.println(LINE_BREAK + "\n\t added: " + input + "\n" + LINE_BREAK);
    }

    private void list() {
        System.out.println(LINE_BREAK);
        for (int i = 0; i < taskIndex; i++) {
            System.out.println("\t" + (i + 1) + ". [" + tasks[i].getStatusIcon() + "] " + tasks[i].description + "\n");
        }
        System.out.println(LINE_BREAK);
    }

    private void mark(int i) {
        System.out.println(LINE_BREAK);
        Task curTask = tasks[i - 1];
        curTask.markAsDone();
        System.out.println("\tNice! I've marked this task as done: \n\t  [" + curTask.getStatusIcon() + "] " + curTask.description + "\n" );
        System.out.println(LINE_BREAK);
    }

    private void unmark(int i) {
        System.out.println(LINE_BREAK);
        Task curTask = tasks[i - 1];
        curTask.markAsUndone();
        System.out.println("\tOK, I've marked this task as not done yet: \n\t  [" + curTask.getStatusIcon() + "] " + curTask.description + "\n" );
        System.out.println(LINE_BREAK);
    }

    private void addTask() {
        tasks[taskIndex] = new Task(input);
        taskIndex++;
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        Scanner scan = new Scanner(System.in);
        duke.input = scan.next();
        while (!duke.input.equals("bye")) {
            if (duke.input.equals("list")) {
                duke.list();
            } else if (duke.input.equals("mark")) {
                int index = scan.nextInt();
                duke.mark(index);
                } else if (duke.input.equals("unmark")) {
                int index = scan.nextInt();
                duke.unmark(index);
            } else {
                duke.input += scan.nextLine();
                duke.addTask();
                duke.echo();
            }
            duke.input = scan.next();
        }
        duke.exit();

    }
}
