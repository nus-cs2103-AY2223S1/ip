import java.util.Scanner;

public class Duke {
    public static class Task {
        protected boolean isDone;
        protected String description;

        public Task(String desc) {
            this.description = desc;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return isDone ? "X" : " ";
        }

        public String getTask() {
            return this.description;
        }

        public static void markDone(Task t) {
            t.isDone = true;
        }

        public static void markUndone(Task t) {
            t.isDone = false;
        }
    }
    public static void main(String[] args) {
        String logo = " ____            _    \n"
                + "|  _ \\ _   _  __| | ___\n"
                + "| | | | | | |/ _  |/ _ \\\n"
                + "| |_| | |_| | |_| |  __/\n"
                + "|____/ \\__,_|\\__,_|\\___|\n";
        System.out.println("Hello from\n" + logo);
        boolean running = true;
        Task[] history = new Task[100];
        int count = 0;
        Scanner sc = new Scanner(System.in);
        while (running) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(">>> Byebye! See you again soon!");
                running = false;
            } else if (input.equals("list")) {
                for (int i = 0; i < count; i++) {
                    System.out.println(i + 1 + ".[" + history[i].getStatusIcon() + "] "
                            + history[i].getTask());
                }
            } else if (input.startsWith("mark")) {
                int taskNum = Integer.parseInt(input.substring(5)) - 1;
                Task target = history[taskNum];
                Task.markDone(target);
                System.out.println("Successfully marked this task as done: ["
                        + target.getStatusIcon() + "] " + target.getTask());
            } else if (input.startsWith("unmark")) {
                int taskNum = Integer.parseInt(input.substring(7)) - 1;
                Task target = history[taskNum];
                Task.markUndone(target);
                System.out.println("Successfully marked this task as not done yet: ["
                        + target.getStatusIcon() + "] " + target.getTask());
            } else {
                Task t = new Task(input);
                history[count] = t;
                count++;
                System.out.println("added: " + input);
            }
        }
    }
}