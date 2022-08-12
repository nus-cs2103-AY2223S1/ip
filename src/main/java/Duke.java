import java.util.LinkedList;
import java.util.Scanner;

public class Duke {
    public class Task {
        protected String description;
        protected boolean isDone;

        public Task(String s) {
            this.description = s;
            isDone = false;
        }

        public String mark() {
            this.isDone = true;
            return this.toString();
        }

        public String unmark() {
            this.isDone = false;
            return this.toString();
        }

        @Override
        public String toString() {
            return String.format("[%s] %s", (isDone ? "X" : " "), description);
        }
    }

    private LinkedList<Task> list;
    private static final String LINE = "--------------------------------------------------";

    private void run() {
        list = new LinkedList<>();
        Scanner sc = new Scanner(System.in);
        System.out.printf("%s%n%s%n%s%n%s%n", LINE, "Hello! I'm Cortana", "What can I do for you?", LINE);

        while (true) {
            String input = sc.nextLine().trim();
            String[] inputArr = input.split(" ", 2);
            System.out.println(LINE);

            if (inputArr[0].equals("bye")) {
                System.out.printf("%s%n%s%n","Bye. Hope to see you again soon!", LINE);
                break;
            } else if (inputArr[0].equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= list.size(); i++) {
                    System.out.printf("%d. %s%n", i, list.get(i - 1));
                }
            } else if (inputArr[0].equals("mark")) {
                int idx = Integer.parseInt(inputArr[1]);
                System.out.printf("Nice! I've marked this task as done:%n%s%n", list.get(idx - 1).mark());
            } else if (inputArr[0].equals("unmark")) {
                int idx = Integer.parseInt(inputArr[1]);
                System.out.printf("OK, I've marked this task as not done yet:%n%s%n", list.get(idx - 1).unmark());
            } else {
                list.add(new Task(input));
                System.out.printf("added: %s%n", input);
            }

            System.out.println(LINE);
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
