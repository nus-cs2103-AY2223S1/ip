import java.util.Scanner;

public class Duke {
    private static final String LINE_BREAK = "\t" + "-----------------------------";
    private String input;
    private String[] tasks = new String[100];
    private int taskIndex = 0;

    private void echo() {
        System.out.println(LINE_BREAK + "\n\t added: " + input + "\n" + LINE_BREAK);
    }

    private void list() {
        for (int i = 0; i < taskIndex; i++) {
            System.out.println(i + 1 + ". " + tasks[i] + "\n");
        }
    }

    private void addTask() {
        tasks[taskIndex] = input;
        taskIndex++;
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        System.out.println("Hello! I'm Ee Suan!\nWhat can I do for you?");
        Scanner scan = new Scanner(System.in);
        duke.input = scan.nextLine();
        while (!duke.input.equals("bye")) {
            if (duke.input.equals("list")) {
                duke.list();
            } else {
                duke.addTask();
                duke.echo();
            }
            duke.input = scan.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}
