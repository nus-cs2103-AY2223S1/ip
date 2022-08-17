import java.util.Scanner;

public class Duke {

    private static final TaskList tasks = new TaskList();

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Dave! What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                run = false;
            } else if (input.equals("list")) {
                System.out.println(tasks);
            } else {
                Task newtask = new Task(input);
                System.out.println(tasks.add(newtask));
            }
        }
    }
}