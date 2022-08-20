import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void markAsUndone() {
            this.isDone = false;
        }

        public String toString() {
            return "[" + getStatusIcon() + "] " + description;
        }
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I am a Store And Response Bot, otherwise known as Starboard\n" +
                "What can I do for you today?");

        ArrayList<Task> store = new ArrayList<>();

        while (true) {

            if (sc.hasNext("mark")) {
                sc.next();
                int num = Integer.parseInt(sc.next());
                store.get(num - 1).markAsDone();
                System.out.println("Task successfully completed!\n" + store.get(num - 1));
                continue;
            } else if (sc.hasNext("unmark")) {
                sc.next();
                int num = Integer.parseInt(sc.next());
                store.get(num - 1).markAsUndone();
                System.out.println("Task incomplete.\n" + store.get(num - 1));
                continue;
            }

            String next = sc.nextLine();

            if (next.equals("bye")) {
                System.out.println("Bye bye! Hope to see you soon!");
                break;
            } else if (next.equals("list")) {
                for (int i = 0; i < store.size(); i++) {
                    System.out.println(i + 1 + ". " + store.get(i));
                }
            } else if (next.equals("")) {
            } else {
                store.add(new Task(next));
                System.out.println("Added new task: " + next);
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
