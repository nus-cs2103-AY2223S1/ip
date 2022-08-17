import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static class Task {
        protected boolean isDone;
        protected String description;
        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public void mark() {
            this.isDone = true;
        }

        public void unmark() {
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " ");
        }

        public String toString() {
            return this.description;
        }
    }
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Jett");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();
            String[] commands = command.split(" ");
            System.out.println("____________________________________________________________");
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + ".[" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i));
                }
            } else if (commands[0].equals("mark")) {
                System.out.println("Nice! I've marked this task as done:");
                int index = Integer.parseInt(commands[1]) - 1;
                tasks.get(index).mark();
                System.out.println("[" + tasks.get(index).getStatusIcon() + "] " + tasks.get(index));
            } else if (commands[0].equals("unmark")) {
                System.out.println("OK, I've marked this task as not done yet:");
                int index = Integer.parseInt(commands[1]) - 1;
                tasks.get(index).unmark();
                System.out.println("[" + tasks.get(index).getStatusIcon() + "] " + tasks.get(index));
            } else {
                tasks.add(new Task(command));
                System.out.println("added: " + command);
            }
            System.out.println("____________________________________________________________");
        }
    }
}
