import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm still evolving!\nWhat can I do for you, my highness?");
        Scanner in = new Scanner(System.in);

        Task[] tasks = new Task[100];
        int count = 0;

        while (true) {
            System.out.println("Your highness:");
            String input = in.nextLine();
            String[] elem = input.split(" ");
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= count; i++) {
                    Task task = tasks[i-1];
                    System.out.println(i + ". [" + task.getStatusIcon() + "] " + task.description);
                }
            } else if (elem[0].equals("mark")) {
                int taskNo = Integer.parseInt(elem[1]);
                Task currTask = tasks[taskNo-1];
                currTask.setDone();
                System.out.println("Nice! I've marked this task as done:\n" + "[" + currTask.getStatusIcon() + "] " + currTask.description);
            } else if (elem[0].equals("unmark")) {
                int taskNo = Integer.parseInt(elem[1]);
                Task currTask = tasks[taskNo-1];
                currTask.setUndone();
                System.out.println("OK, I've marked this task as not done yet:\n" + "[" + currTask.getStatusIcon() + "] " + currTask.description);
            } else {
                tasks[count] = new Task(input);
                count++;
                System.out.println("added: " + input);
            }
        }
    }
}
