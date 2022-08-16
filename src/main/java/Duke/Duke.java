package Duke;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.List;

public class Duke {
    String DIV = "________________________________________________________________";
    String DIV2 = "============================================================";
    List<Task> store = new ArrayList<>();
    boolean inProgress = true;

    private String Start(String input) {
        if (input.equals("bye")) {
            inProgress = false;
            return "\tBye Master! It was a pleasure serving you, see you again soon!";
        } else if (input.equals("list")) {
            String list = "\tSir, these are the tasks in your list:";
            for (int i = 0; i < store.size(); i++) {
                int index = i + 1;
                list += "\n\t" + index + ". " + store.get(i);
            }
            return list;
        } else if (input.toLowerCase().startsWith("mark")) {
            int taskIndex = Integer.valueOf(input.substring("mark ".length()));
            return handleMark(taskIndex);
        } else if (input.toLowerCase().startsWith("unmark")) {
            int taskIndex = Integer.valueOf(input.substring("unmark ".length()));
            return handleUnmark(taskIndex);
        }

        else {
            store.add(new Task(input));
            return "\tadded: " + input;
        }
    }

    private String handleMark(int index) {
        Task task = this.store.get(index - 1);
        task.markDone();
        return String.format("\tAlright solid work! You've completed this task: \n \t%s", task);
    }

    private String handleUnmark(int index) {
        Task task = this.store.get(index - 1);
        task.markUndone();
        return String.format("\tHey this is not done yet? Remember to finish it soon: \n \t%s", task);
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println(DIV);
        System.out.println("How may I serve you today, Master?");
        System.out.println("\t" + DIV2 + "\n");
        while (inProgress) {
            String command = sc.nextLine();
            System.out.println("\t" + DIV);
            System.out.println(Start(command));
            System.out.println("\t" + DIV + "\n");
        }
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! This is \n" + logo + "\nat your service!!");
        new Duke().run();
    }
}
