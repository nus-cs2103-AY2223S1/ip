import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static void printSeparator() {
        System.out.println("    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
    
    private static void printMsg(String msg) {
        printSeparator();
        System.out.println("     " + msg);
        printSeparator();
    }
    
    private static void printMultiMsg(String[] msgs) {
        printSeparator();
        for (String msg : msgs) {
            System.out.println("     " + msg);
        }
        printSeparator();
    }

    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printMultiMsg(new String[]{"Hello my name is Duke", "What can I do for you?"});

        Scanner sc = new Scanner (System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            if (input.length() < 1) continue;

            String[] words = input.split(" ");

            if (words[0].equals("bye")) {
                printMsg("Bye. Hope to see you again soon!");
                break;
            } else if (words[0].equals("list")) {
                String[] taskStrings = new String[tasks.size()];
                for (int i = 0; i < tasks.size(); i++) {
                    taskStrings[i] = (i+1) + ". [" + (tasks.get(i).isDone() ? "X" : " ") + "] " + tasks.get(i);
                }
                printMultiMsg(taskStrings);
            } else if (words[0].equals("mark")) {
                Task task = tasks.get(Integer.parseInt(words[1]) - 1);
                task.markDone();
                printMultiMsg(new String[]{"Nice! I've marked this task as done:", "[X] " + task});
            } else {
                tasks.add(new Task(input, false));
                printMsg("added: " + input);
            }
        }
    }
}
